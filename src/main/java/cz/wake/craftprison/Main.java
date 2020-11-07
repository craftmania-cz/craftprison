package cz.wake.craftprison;


import cz.wake.craftprison.commands.*;
import cz.wake.craftprison.hooks.PlaceholderRegister;
//import cz.wake.craftprison.hooks.VKBackPackHook;
import cz.wake.craftprison.listener.*;
import cz.wake.craftprison.modules.ActionBarProgress;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.modules.pickaxe.PickaxeUpgradeListener;
import cz.wake.craftprison.npc.NPCManager;
import cz.wake.craftprison.sql.SQLManager;
import cz.wake.craftprison.statistics.Statistics;
import cz.wake.craftprison.statistics.menu.StatisticsMenu;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends JavaPlugin {

    private static Main instance;
    private static Map<String, Integer> active;
    //private VKBackPackHook backpack;
    private final List<Material> tools;
    private final List<Material> ignored;
    private SQLManager sql;
    private Statistics statistics;
    private PlayerStatsListener playerStatsListener;
    private NPCManager npcManager;
    private boolean debug = false;

    static {
        Main.active = new HashMap<>();
    }

    public Main() {
        //this.backpack = null;
        this.tools = Arrays.asList(Material.STONE_AXE, Material.STONE_HOE, Material.STONE_PICKAXE, Material.STONE_SHOVEL, Material.WOODEN_AXE, Material.WOODEN_HOE, Material.WOODEN_PICKAXE, Material.WOODEN_SHOVEL, Material.IRON_AXE, Material.IRON_HOE, Material.IRON_PICKAXE, Material.IRON_SHOVEL, Material.GOLDEN_AXE, Material.GOLDEN_HOE, Material.GOLDEN_PICKAXE, Material.GOLDEN_SHOVEL, Material.DIAMOND_AXE, Material.DIAMOND_HOE, Material.DIAMOND_PICKAXE, Material.DIAMOND_SHOVEL);
        this.ignored = Arrays.asList(Material.BEDROCK, Material.MINECART); //TODO: ?
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

        // Listeners
        loadListeners();
        loadCommands();

        // Config hodnoty
        this.statistics = new Statistics(this);
        this.debug = getConfig().getBoolean("debug");

        // WG regions
        PrisonManager.registerWgMines();

        //Statistiky
        statistics = new Statistics(this);
        playerStatsListener = new PlayerStatsListener(this);

        // Update statistik
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                Main.getInstance().getMySQL().setAllFromCache(player);
            }
        }, 1, 2400);

        // Tasks
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, ActionBarProgress::send, 1L, 40L);

        // Placeholders
        PlaceholderRegister pr = new PlaceholderRegister(this);
        pr.registerPlaceholders();

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
        pm.registerEvents(new MiningListener(), this);
        pm.registerEvents(new WGExtendedListener(), this);
        pm.registerEvents(new PlayerListener(this), this);
        pm.registerEvents(new InventoryFullListener(), this);
        pm.registerEvents(new StatisticsMenu(), this);
        pm.registerEvents(new PlayerStatsListener(this), this);
        pm.registerEvents(new PickaxeUpgradeListener(), this);
        //pm.registerEvents(new EnchantmentListener(), this);
        pm.registerEvents(new PShopCommand(), this);
        pm.registerEvents(new ItemDropListener(), this);
        //m.registerEvents(new PlayerDeathListener(), this); //TODO: fix duplikace pri smrti
        pm.registerEvents(new PickaxeInteractListener(), this);
        pm.registerEvents(new NPCInteractListener(), this);

        if (Bukkit.getPluginManager().isPluginEnabled("AutoSell")) {
            Bukkit.getServer().getPluginManager().registerEvents(new AutoSellListener(this), this);
        } else {
            Bukkit.getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
        }
    }

    private void loadCommands() {
        getCommand("rank").setExecutor(new RankCommand());
        getCommand("rankup").setExecutor(new RankUpCommand());
        getCommand("stats").setExecutor(new StatsCommand());
        getCommand("statstop").setExecutor(new StatsTopCommand());
        getCommand("pcoins").setExecutor(new PCoinsCommand());
        getCommand("pickaxe").setExecutor(new PickaxeCommand());
        getCommand("tutorial").setExecutor(new TutorialCommand());
        getCommand("pvp").setExecutor(new PVPCommand());
        getCommand("mine").setExecutor(new MineCommand());
        getCommand("ranks").setExecutor(new RanksCommand());
        getCommand("pshop").setExecutor(new PShopCommand());
        getCommand("prodat").setExecutor(new SellCommand());
    }

    public static Main getInstance() {
        return instance;
    }

    /*public VKBackPackHook getBackpackHook() {
        return this.backpack;
    }*/

    public List<Material> getIgnored() {
        return this.ignored;
    }

    public List<Material> getTools() {
        return this.tools;
    }

    public boolean isAlerted(final String name) {
        return Main.active != null && !Main.active.isEmpty() && Main.active.containsKey(name);
    }

    public NPCManager getNpcManager() {
        return this.npcManager;
    }

    public int getAlertAmount(final String name) {
        if (this.isAlerted(name)) {
            return Main.active.get(name);
        }
        return 0;
    }

    public void setAlertAmount(final String name, final int i) {
        if (Main.active == null) {
            Main.active = new HashMap<>();
        }
        Main.active.put(name, i);
    }

    public void decreaseAlertAmount(final String name) {
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {
            if (Main.active == null) {
                return;
            }
            if (Main.active.containsKey(name)) {
                final int c = Main.active.get(name);
                if (c == 1) {
                    Main.active.remove(name);
                } else {
                    Main.active.put(name, c - 1);
                }
            }
        }, 20L * 5);
    }

    public SQLManager getMySQL() {
        return sql;
    }

    private void initDatabase() {
        sql = new SQLManager(this);
    }

    public Statistics getStatistics() {
        return statistics;
    }
}
