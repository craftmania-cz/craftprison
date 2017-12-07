package cz.wake.craftprison;

import cz.wake.craftprison.armorstands.ArmorStandManager;
import cz.wake.craftprison.listener.ArmorStandInteract;
import cz.wake.craftprison.listener.MiningListener;
import cz.wake.craftprison.listener.WGExtendedListener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;
    private ArmorStandManager asm = new ArmorStandManager();

    @Override
    public void onEnable(){

        // Instance
        instance = this;

        // Config
        //getConfig().options().copyDefaults(true);
        //saveDefaultConfig();

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
    }

    private void loadCommands() {

    }

    public static Main getInstance() {
        return instance;
    }

    public ArmorStandManager getArmorStandManager() {
        return asm;
    }
}
