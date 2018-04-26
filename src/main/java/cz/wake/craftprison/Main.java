package cz.wake.craftprison;

import com.wasteofplastic.askyblock.ASkyBlockAPI;
import cz.wake.craftprison.armorstands.ArmorStandManager;
import cz.wake.craftprison.commands.*;
import cz.wake.craftprison.hooks.VKBackPackHook;
import cz.wake.craftprison.listener.*;
import cz.wake.craftprison.modules.ActionBarProgress;
import cz.wake.craftprison.modules.Board;
import cz.wake.craftprison.modules.pickaxe.EnchantmentListener;
import cz.wake.craftprison.modules.pickaxe.PickaxeUpgrade;
import cz.wake.craftprison.modules.pickaxe.PickaxeUpgradeListener;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.sql.SQLManager;
import cz.wake.craftprison.statistics.Statistics;
import cz.wake.craftprison.listener.PlayerStatsListener;
import cz.wake.craftprison.statistics.menu.StatisticsMenu;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends JavaPlugin {

    private static Main instance;
    private ArmorStandManager asm = new ArmorStandManager();
    private static Economy economy = null;
    private static Map<String, Integer> active;
    private VKBackPackHook backpack;
    private final List<Material> tools;
    private final List<Material> ignored;
    private SQLManager sql;
    private boolean fixArmorstands = false;
    private Statistics statistics;
    private ASkyBlockAPI aSkyBlockAPI;
    private PlayerStatsListener playerStatsListener;

    static {
        Main.active = new HashMap<>();
    }

    public Main() {
        this.backpack = null;
        this.tools = Arrays.asList(Material.STONE_AXE, Material.STONE_HOE, Material.STONE_PICKAXE, Material.STONE_SPADE, Material.WOOD_AXE, Material.WOOD_HOE, Material.WOOD_PICKAXE, Material.WOOD_SPADE, Material.IRON_AXE, Material.IRON_HOE, Material.IRON_PICKAXE, Material.IRON_SPADE, Material.GOLD_AXE, Material.GOLD_HOE, Material.GOLD_PICKAXE, Material.GOLD_SPADE, Material.DIAMOND_AXE, Material.DIAMOND_HOE, Material.DIAMOND_PICKAXE, Material.DIAMOND_SPADE);
        this.ignored = Arrays.asList(Material.LONG_GRASS, Material.GRASS, Material.WHEAT, Material.SUGAR_CANE_BLOCK, Material.SUGAR_CANE, Material.BED_BLOCK, Material.BED, Material.BOAT, Material.BOOKSHELF, Material.BREWING_STAND, Material.BREWING_STAND_ITEM, Material.CACTUS, Material.CAKE, Material.CAKE_BLOCK, Material.CARPET, Material.CARROT, Material.CARROT_ITEM, Material.CAULDRON, Material.CAULDRON_ITEM, Material.CROPS, Material.COMMAND, Material.COMMAND_MINECART, Material.DAYLIGHT_DETECTOR, Material.DEAD_BUSH, Material.DETECTOR_RAIL, Material.DIODE, Material.DIODE_BLOCK_OFF, Material.DIODE_BLOCK_ON, Material.DOUBLE_PLANT, Material.DISPENSER, Material.DROPPER, Material.ENCHANTMENT_TABLE, Material.ENDER_CHEST, Material.ENDER_PORTAL_FRAME, Material.EXPLOSIVE_MINECART, Material.FLOWER_POT, Material.FLOWER_POT_ITEM, Material.HOPPER, Material.HOPPER_MINECART, Material.ICE, Material.IRON_DOOR_BLOCK, Material.IRON_DOOR, Material.ITEM_FRAME, Material.JUKEBOX, Material.LEAVES, Material.LEAVES_2, Material.LEVER, Material.MELON_BLOCK, Material.MELON_STEM, Material.MINECART, Material.NOTE_BLOCK, Material.PACKED_ICE, Material.PAINTING, Material.PISTON_BASE, Material.PISTON_EXTENSION, Material.PISTON_STICKY_BASE, Material.PISTON_MOVING_PIECE, Material.POISONOUS_POTATO, Material.POTATO, Material.PORTAL, Material.POWERED_MINECART, Material.POWERED_RAIL, Material.RAILS, Material.RED_ROSE, Material.YELLOW_FLOWER, Material.REDSTONE, Material.REDSTONE_COMPARATOR, Material.REDSTONE_WIRE, Material.SAPLING, Material.SEEDS, Material.SIGN, Material.SIGN_POST, Material.SNOW, Material.SNOW_BLOCK, Material.STAINED_GLASS, Material.STAINED_GLASS_PANE, Material.STORAGE_MINECART, Material.TNT, Material.TRAP_DOOR, Material.TORCH, Material.TRAPPED_CHEST, Material.TRIPWIRE, Material.TRIPWIRE_HOOK, Material.VINE, Material.WALL_SIGN, Material.WATER_LILY, Material.WEB);
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

        // Vault Economy
        this.setupEconomy();

        // Listeners
        loadListeners();
        loadCommands();

        // Dodatecne pluginy
        if (Bukkit.getPluginManager().isPluginEnabled("VKBackPack")) {
            this.backpack = new VKBackPackHook();
        }

        // Config hodnoty
        fixArmorstands = getConfig().getBoolean("fix-armorstands");
        statistics = new Statistics(this);

        // ArmorStandy
        ArmorStandManager.initArmorStands();

        // WG regions
        PrisonManager.registerWgMines();

        //ASkyBlock hook
        if (Bukkit.getPluginManager().isPluginEnabled("AutoSell")) {
            this.aSkyBlockAPI = (ASkyBlockAPI) Bukkit.getPluginManager().getPlugin("aSkyBlock");
        } else {
            this.aSkyBlockAPI = null;
        }

        //Statistiky
        statistics = new Statistics(this);
        playerStatsListener = new PlayerStatsListener(this);
        Bukkit.getServer().getPluginManager().registerEvents(new PlayerStatsListener(this), this);

        // Update statistik
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                Main.getInstance().getMySQL().setAllFromCache(player);
            }
        }, 1, 2400);

        // Scoreboard
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, Board::updateAll, 1L, 100L);
        // Tasks
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, ActionBarProgress::send, 1L, 40L);
    }

    @Override
    public void onDisable() {

        // Despawn armorstandu
        ArmorStandManager.removeArmorStands(fixArmorstands);

        // Deaktivace MySQL
        sql.onDisable();

        instance = null;
    }

    private void loadListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ArmorStandInteract(), this);
        pm.registerEvents(new MiningListener(), this);
        pm.registerEvents(new WGExtendedListener(), this);
        pm.registerEvents(new PlayerListener(this), this);
        pm.registerEvents(new InventoryFullListener(), this);
        pm.registerEvents(new StatisticsMenu(), this);

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
        getCommand("pcoins").setExecutor(new PCoinsCommand());
        getCommand("pickaxe").setExecutor(new PickaxeCommand());
    }

    public static Main getInstance() {
        return instance;
    }

    public ArmorStandManager getArmorStandManager() {
        return asm;
    }

    public static Economy getEconomy() {
        return economy;
    }

    private boolean setupEconomy() {
        final RegisteredServiceProvider<Economy> economyProvider = (RegisteredServiceProvider<Economy>) this.getServer().getServicesManager().getRegistration((Class) Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }
        return economy != null;
    }

    public VKBackPackHook getBackpackHook() {
        return this.backpack;
    }

    public List<Material> getIgnored() {
        return this.ignored;
    }

    public List<Material> getTools() {
        return this.tools;
    }

    public boolean isAlerted(final String name) {
        return Main.active != null && !Main.active.isEmpty() && Main.active.containsKey(name);
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

    public ASkyBlockAPI getSkyBlockAPI() {
        return aSkyBlockAPI;
    }
}
