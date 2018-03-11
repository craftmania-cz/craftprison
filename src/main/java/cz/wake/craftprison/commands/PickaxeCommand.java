package cz.wake.craftprison.commands;

import cz.wake.craftprison.modules.pickaxe.PickaxeUpgrade;
import cz.wake.craftprison.utils.PlayerUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PickaxeCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Pouze hraci :feelsDragonEEE:");
            return true;
        }
        Player p = (Player) sender;
        //Pro test
        if (args.length != 0) {
            if (args[0].equalsIgnoreCase("get")) {
                p.getInventory().addItem(PickaxeUpgrade.getFirstPickaxe(p.getName()));
                return true;
            }
        }

        if (!isPickaxe(p.getInventory().getItemInMainHand().getType())) {
            p.sendMessage("§cMusis drzet v ruce krumpac!");
            return true;
        }

        new PickaxeUpgrade(p, p.getInventory().getItemInMainHand());

        return false;
    }


    private boolean isPickaxe(Material m) {
        return m.toString().endsWith("_PICKAXE");
    }
}
