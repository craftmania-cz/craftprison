package cz.wake.craftprison;

import at.pcgamingfreaks.Minepacks.Bukkit.API.Backpack;
import at.pcgamingfreaks.Minepacks.Bukkit.API.MinepacksPlugin;
import co.aikar.commands.PaperCommandManager;
import cz.craftmania.crafteconomy.utils.VaultUtils;
import cz.wake.craftprison.commands.*;
import cz.wake.craftprison.hooks.PlaceholderRegistry;
import cz.wake.craftprison.listener.*;
import cz.wake.craftprison.modules.ActionBarProgress;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.npc.NPCManager;
import cz.wake.craftprison.npc.VillagerTypeTrait;
import cz.wake.craftprison.objects.PrestigeMines;
import cz.wake.craftprison.objects.Rank;
import cz.wake.craftprison.sql.SQLManager;
import cz.wake.craftprison.utils.Logger;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.trait.TraitInfo;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main extends JavaPlugin {

    private static Main instance;
    private static Map<String, Integer> active;
    //private VKBackPackHook backpack;
    private SQLManager sql;
    private PlayerStatsListener playerStatsListener;
    private NPCManager npcManager;
    private Economy vaultUtils;
    private MinepacksPlugin minepacksPlugin;
    private boolean debug = false;

    // Commands manager
    private PaperCommandManager manager;

    static {
        Main.active = new HashMap<>();
    }

    @Override
    public void onEnable() {

        // Instance
        instance = this;

        // Config
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        // HikariCP
        initDatabase();

        // CraftEconomy - Vault
        this.vaultUtils = new VaultUtils();

        // Aikar command manager
        manager = new PaperCommandManager(this);
        manager.enableUnstableAPI("help");

        List<String> mineCommandPrediction = Stream.concat(Rank.getRanksAsList().stream(), PrestigeMines.getRanksAsList().stream()).collect(Collectors.toList());
        manager.getCommandCompletions().registerCompletion("ranks", c -> mineCommandPrediction);

        // Register příkazů
        Logger.info("Probíhá registrace příkazů pomocí Aikar commands!");
        loadCommands(manager);

        // Plugin listeners
        loadListeners();

        // Config hodnoty
        this.debug = getConfig().getBoolean("debug");

        // WG regions
        PrisonManager.registerWgMines();

        // Update statistik
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                //Main.getInstance().getMySQL().setAllFromCache(player);
            }
        }, 1, 2400);

        // Tasks
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, ActionBarProgress::send, 1L, 40L);

        // Placeholders
        new PlaceholderRegistry().register();

        // Citizens traits
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(VillagerTypeTrait.class).withName("villagertype"));

        // NPCs prepare & spawn
        this.npcManager = new NPCManager();
        this.npcManager.initNPCs();

        // Backpack Integrace
        if (getServer().getPluginManager().isPluginEnabled("Minepacks")) {
            this.minepacksPlugin = (MinepacksPlugin)Bukkit.getPluginManager().getPlugin("Minepacks");
        }
    }

    @Override
    public void onDisable() {

        // Despawn NPC
        this.getNpcManager().destroyNPCs();

        // Deaktivace MySQL
        sql.onDisable();

        instance = null;
    }

    private void loadListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new WGExtendedListener(), this);
        pm.registerEvents(new PlayerListener(this), this);
        //pm.registerEvents(new PlayerStatsListener(this), this);
        pm.registerEvents(new ItemDropListener(), this);
        pm.registerEvents(new PickaxeInteractListener(), this);
        pm.registerEvents(new NPCInteractListener(), this);
        pm.registerEvents(new InventoryFullListener(), this);
        pm.registerEvents(new BlockBreakListener(), this);
        pm.registerEvents(new PlayerPayCorrectListener(), this);
    }

    private void loadCommands(PaperCommandManager manager) {
        manager.registerCommand(new MineCommand());
        manager.registerCommand(new RanksCommand());
        manager.registerCommand(new RankCommand());
        manager.registerCommand(new RankUpCommand());
        manager.registerCommand(new PickaxeCommand());
        manager.registerCommand(new TokenGiveCommand());
        manager.registerCommand(new PrestigeCommand());
        manager.registerCommand(new HelpMenuCommand());
    }

    public static Main getInstance() {
        return instance;
    }

    public NPCManager getNpcManager() {
        return this.npcManager;
    }

    public SQLManager getMySQL() {
        return sql;
    }

    private void initDatabase() {
        sql = new SQLManager(this);
    }

    public Economy getEconomy() {
        return vaultUtils;
    }

    public MinepacksPlugin getMinepacksPlugin() {
        return minepacksPlugin;
    }

    public Backpack getPlayerBackpackInventory(Player player) {
        Backpack bp = Main.getInstance().getMinepacksPlugin().getBackpackCachedOnly(player);
        if (bp == null) return null;
        return bp;
    }
}
