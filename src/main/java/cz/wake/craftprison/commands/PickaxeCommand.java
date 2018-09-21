package cz.wake.craftprison.commands;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.modules.pickaxe.CustomPickaxe;
import cz.wake.craftprison.modules.pickaxe.PickaxeUpgrade;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;

public class PickaxeCommand implements CommandExecutor {

    private HashMap<Player, Double> _time = new HashMap<>();
    private HashMap<Player, BukkitRunnable> _cdRunnable = new HashMap<>();


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Pouze hraci :feelsDragonEEE:");
            return true;
        }
        Player p = (Player) sender;
        if (args.length != 0) {
            if (args.length > 2) {
                if (args[0].equalsIgnoreCase("rename")) {
                    if (!args[1].matches("([Aa-zZ])\\w+")) {
                        p.sendMessage("§c§l(!) §cPouzivas nepovolene znaky pro prejmenovani krumpace!");
                        return true;
                    }
                    if (!CustomPickaxe.isPickaxe(p.getInventory().getItemInMainHand().getType())) {
                        p.sendMessage("§c§l(!) §cMusis drzet v ruce krumpac!");
                        return true;
                    }
                    StringBuilder str = new StringBuilder();
                    for (int j = 0; j < args.length; j++) {
                        str.append(args[2] + " ");
                    }
                    String name = str.toString();
                    if (str.toString().replace("&", "").length() > 16) {
                        p.sendMessage("§c§l(!) §cTvuj krumpac muze obsahovat maximalne 16 znaku!");
                        return true;
                    }
                    p.getInventory().getItemInMainHand().getItemMeta().setDisplayName(name);
                    return true;
                }
            }
            if (args[0].equalsIgnoreCase("get")) {
                if (this._time.containsKey(p)) {
                    p.sendMessage("§c§l(!) §cMusis pockat jeste " + String.valueOf(arrondi(this._time.get(p), 1)) + " vterin.");
                    return false;
                }
                this._time.put(p, 600D + 0.1D);
                p.getInventory().addItem(PickaxeUpgrade.getDefaultPickaxe(p.getName()));
                this._cdRunnable.put(p, new BukkitRunnable() {
                    @Override
                    public void run() {
                        PickaxeCommand.this._time.put(p, Double.valueOf((PickaxeCommand.this._time.get(p)).doubleValue() - 0.1D));
                        if ((PickaxeCommand.this._time.get(p)).doubleValue() < 0.01D) {
                            PickaxeCommand.this._time.remove(p);
                            PickaxeCommand.this._cdRunnable.remove(p);
                            cancel();
                        }

                    }
                });
                (this._cdRunnable.get(p)).runTaskTimer(Main.getInstance(), 2L, 2L);
                return true;
            }
            if (args[0].equalsIgnoreCase("debug")) {
                if (p.hasPermission("craftprison.admin.pickaxe")) {
                    p.getInventory().addItem(PickaxeUpgrade.getDebugPickaxe(p.getName()));
                    return true;
                }
            }
            if (args[0].equalsIgnoreCase("rename")) {
                p.sendMessage("§c§l(!) §cNa co chces prejmenovat svuj krumpac?");
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

    private static double arrondi(double A, int B) {
        return (int) (A * Math.pow(10.0D, B) + 0.5D) / Math.pow(10.0D, B);
    }

}
