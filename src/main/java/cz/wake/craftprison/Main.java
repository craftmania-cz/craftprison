package cz.wake.craftprison;

import cz.wake.craftprison.armorstands.ArmorStandManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private static Main instance;

    @Override
    public void onEnable(){
        instance = this;

        // ArmorStandy
        ArmorStandManager.init();
        ArmorStandManager.spawn();
    }

    @Override
    public void onDisable(){
        instance = null;
    }

    public static Main getInstance() {
        return instance;
    }
}
