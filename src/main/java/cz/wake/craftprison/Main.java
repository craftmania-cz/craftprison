package cz.wake.craftprison;

import co.aikar.commands.PaperCommandManager;
import com.google.common.collect.ImmutableList;
import cz.craftmania.crafteconomy.utils.VaultUtils;
import cz.wake.craftprison.commands.*;
import cz.wake.craftprison.hooks.PlaceholderRegister;
import cz.wake.craftprison.listener.*;
import cz.wake.craftprison.modules.ActionBarProgress;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.modules.pickaxe.PickaxeUpgradeListener;
import cz.wake.craftprison.npc.NPCManager;
import cz.wake.craftprison.npc.VillagerTypeTrait;
import cz.wake.craftprison.objects.Rank;
import cz.wake.craftprison.sql.SQLManager;
import cz.wake.craftprison.utils.Logger;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.trait.TraitInfo;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public class Main extends JavaPlugin {

    private static Main instance;
    private static Map<String, Integer> active;
    //private VKBackPackHook backpack;
    private SQLManager sql;
    private PlayerStatsListener playerStatsListener;
    private NPCManager npcManager;
    private Economy vaultUtils;
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

        manager.getCommandCompletions().registerCompletion("ranks", c -> Rank.getRanksAsList());

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
        PlaceholderRegister pr = new PlaceholderRegister(this);
        pr.registerPlaceholders();

        // Citizens traits
        CitizensAPI.getTraitFactory().registerTrait(TraitInfo.create(VillagerTypeTrait.class).withName("villagertype"));

        // NPCs prepare & spawn
        this.npcManager = new NPCManager();
        this.npcManager.initNPCs();
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
        //pm.registerEvents(new MiningListener(), this);
        pm.registerEvents(new WGExtendedListener(), this);
        pm.registerEvents(new PlayerListener(this), this);
        //pm.registerEvents(new PlayerStatsListener(this), this);
        //pm.registerEvents(new PickaxeUpgradeListener(), this);
        //pm.registerEvents(new EnchantmentListener(), this);
        //pm.registerEvents(new PShopCommand(), this);
        pm.registerEvents(new ItemDropListener(), this);
        //m.registerEvents(new PlayerDeathListener(), this); //TODO: fix duplikace pri smrti
        //pm.registerEvents(new PickaxeInteractListener(), this);
        pm.registerEvents(new NPCInteractListener(), this);
        pm.registerEvents(new InventoryFullListener(), this);
        pm.registerEvents(new BlockBreakListener(), this);
    }

    private void loadCommands(PaperCommandManager manager) {
        manager.registerCommand(new MineCommand());
        manager.registerCommand(new RanksCommand());
        manager.registerCommand(new RankCommand());
        manager.registerCommand(new RankUpCommand());
        manager.registerCommand(new SellCommand());
    }

    private void loadCommands() {
        //getCommand("pickaxe").setExecutor(new PickaxeCommand());
        //getCommand("pvp").setExecutor(new PVPCommand());
        //getCommand("pshop").setExecutor(new PShopCommand());
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
}
