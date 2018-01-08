package cz.wake.craftprison;

import cz.wake.craftprison.armorstands.ArmorStandManager;
import cz.wake.craftprison.commands.RankCommand;
import cz.wake.craftprison.commands.RankUpCommand;
import cz.wake.craftprison.listener.ArmorStandInteract;
import cz.wake.craftprison.listener.MiningListener;
import cz.wake.craftprison.listener.PlayerListener;
import cz.wake.craftprison.listener.WGExtendedListener;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;
    private ArmorStandManager asm = new ArmorStandManager();
    private static Economy economy = null;

    @Override
    public void onEnable(){

        // Instance
        instance = this;

        // Config
        //getConfig().options().copyDefaults(true);
        //saveDefaultConfig();

        this.setupEconomy();

        // Listeners
        loadListeners();
        loadCommands();

        // ArmorStandy
        ArmorStandManager.init();
        ArmorStandManager.spawn();
    }

    @Override
    public void onDisable(){

        ArmorStandManager.despawn();

        instance = null;
    }

    private void loadListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ArmorStandInteract(), this);
        pm.registerEvents(new MiningListener(), this);
        pm.registerEvents(new WGExtendedListener(), this);
        pm.registerEvents(new PlayerListener(), this);
    }

    private void loadCommands() {
        getCommand("rank").setExecutor(new RankCommand());
        getCommand("rankup").setExecutor(new RankUpCommand());
    }

    public static Main getInstance() {
        return instance;
    }

    public ArmorStandManager getArmorStandManager() {
        return asm;
    }

    public static Economy getEconomy(){
        return economy;
    }

    private boolean setupEconomy() {
        final RegisteredServiceProvider<Economy> economyProvider = (RegisteredServiceProvider<Economy>)this.getServer().getServicesManager().getRegistration((Class)Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }
        return economy != null;
    }
}
