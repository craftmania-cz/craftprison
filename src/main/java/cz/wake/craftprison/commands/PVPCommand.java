package cz.wake.craftprison.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PVPCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if ((command.getName().equalsIgnoreCase("pvp"))) {
                player.sendMessage("§c§l(!) §cProvadime zmeny na PVP! Brzo opet pujde! :)");
                /*if(player.hasPermission("craftprison.pvp")) {
                    if(player.getInventory().contains(Material.OBSIDIAN)) {
                        player.sendMessage("§c§l(!) §cObsidian si musis natezit v dole, ne si ho tam nosit jak na dovolenou!");
                        return false;
                    }
                    player.teleport(new Location(Bukkit.getWorld("doly"), -1078.5, 118, 1374.5, -89, 0));
                } else {
                    player.sendMessage("§c§l(!) §cK ziskani pristupu do PVP musis mit rank §bBender§c.");
                }*/
            }
        }
        return false;
    }
}
