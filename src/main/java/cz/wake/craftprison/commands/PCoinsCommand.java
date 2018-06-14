package cz.wake.craftprison.commands;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.modules.PrisCoins;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PCoinsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;

        if (args.length == 0) {
            p.sendMessage("§eAktualne mas §7" + PrisCoins.getCoins(p) + " §ePrisCoinu!");
            return true;
        }
        if (!p.hasPermission("craftprison.priscoins.admin")) {
            p.sendMessage("§cNa toto nemas dostatecna prava!");
            return true;
        }
        String arg = args[0].toLowerCase();
        switch (arg) {
            case "give":
                if (args.length > 3 || args.length < 3) {
                    p.sendMessage("§cSpatne argumenty!");
                    break;
                }
                try {
                    int value = Integer.valueOf(args[2]);
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        p.sendMessage("§cHrac musi byt online");
                        break;
                    }
                    PrisCoins.giveCoins(target, value);
                    p.sendMessage("§eHraci §7" + target.getName() + " §ebylo pridano §7" + value + " §ePrisCoinu");
                } catch (NumberFormatException e) {
                    p.sendMessage("§cMusis zadat cislo!");
                }
                break;
            case "take":
                if (args.length > 3 || args.length < 3) {
                    p.sendMessage("§cSpatne argumenty!");
                    break;
                }
                try {
                    int value = Integer.valueOf(args[2]);
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        p.sendMessage("§cHrac musi byt online");
                        break;
                    }
                    PrisCoins.takeCoins(target, value);
                    p.sendMessage("§eHraci §7" + target.getName() + " §ebylo odebrano §7" + value + " §ePrisCoinu");
                } catch (NumberFormatException e) {
                    p.sendMessage("§cMusis zadat cislo!");
                }
                break;
            case "set":
                if (args.length > 3 || args.length < 3) {
                    p.sendMessage("§cSpatne argumenty!");
                    break;
                }
                try {
                    if (!Main.getInstance().getMySQL().hasData(args[1])) {
                        p.sendMessage("§cTento hrac neexistuje");
                        break;
                    }
                    int value = Integer.valueOf(args[2]);
                    Player target = Bukkit.getPlayer(args[1]);
                    if (target == null) {
                        p.sendMessage("§cHrac musi byt online");
                        break;
                    }
                    PrisCoins.setCoins(target, value);
                    p.sendMessage("§eHraci §7" + args[1] + " §ebyl stav uctu nastaven na §7" + value + " §ePrisCoinu");
                } catch (NumberFormatException e) {
                    p.sendMessage("§cMusis zadat cislo!");
                }
                break;
            default:
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    p.sendMessage("§cHrac musi byt online");
                    break;
                }
                p.sendMessage("§eHrac " + args[0] + " §ema §7" + PrisCoins.getCoins(target) + " §ePrisCoinu!");
        }
        return false;
    }

}
