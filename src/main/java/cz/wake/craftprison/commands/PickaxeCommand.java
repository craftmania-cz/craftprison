package cz.wake.craftprison.commands;

import cz.wake.craftprison.modules.pickaxe.CustomPickaxe;
import cz.wake.craftprison.modules.pickaxe.PickaxeUpgrade;
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
        if (args.length != 0) {
            if (args[0].equalsIgnoreCase("get")) {
                if (!p.hasPermission("craftprison.pickaxe.get")){
                    p.sendMessage("§c§l(!) §cNa toto nemas dostatecna prava!");
                    return false;
                }
                p.getInventory().addItem(PickaxeUpgrade.getFirstPickaxe(p.getName()));
                return true;
            }
        }

        if (!CustomPickaxe.isPickaxe(p.getInventory().getItemInMainHand().getType())) {
            p.sendMessage("§c§l(!) §cMusis drzet v ruce krumpac!");
            return true;
        }

        new PickaxeUpgrade(p, p.getInventory().getItemInMainHand());

        return false;
    }
}
