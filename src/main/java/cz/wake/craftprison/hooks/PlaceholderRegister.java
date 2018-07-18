package cz.wake.craftprison.hooks;

import be.maximvdw.placeholderapi.PlaceholderAPI;
import cz.wake.craftprison.Main;
import cz.wake.craftprison.modules.PrisCoins;
import cz.wake.craftprison.modules.PrisonManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class PlaceholderRegister {

    private Main plugin;
    private PrisonManager pm = new PrisonManager();

    public PlaceholderRegister(Main plugin) {
        this.plugin = plugin;
    }

    public void registerPlaceholders() {

        if(Bukkit.getPluginManager().isPluginEnabled("MVdWPlaceholderAPI")) {
            PlaceholderAPI.registerPlaceholder(plugin, "craftprison_rank",
                    e -> {
                        Player p = e.getPlayer();
                        if(p != null) {
                            return pm.getPlayerRank(p).getName();
                        }
                        return "null";
                    });
            PlaceholderAPI.registerPlaceholder(plugin, "craftprison_priscoins",
                    e -> {
                        Player p = e.getPlayer();
                        if(p != null) {
                            return String.valueOf(PrisCoins.getCoins(p));
                        }
                        return "null";
                    });
        } else {
            Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[CraftPrison] " + ChatColor.RED + " Nebyl nalezen MVdWPlaceholderAPI - placeholdery nebudou fungovat.");
        }
    }
}
