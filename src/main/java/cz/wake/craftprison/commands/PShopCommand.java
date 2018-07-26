package cz.wake.craftprison.commands;

import cz.wake.craftcore.utils.items.ItemBuilder;
import cz.wake.craftprison.modules.PrisCoins;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class PShopCommand implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if ((command.getName().equalsIgnoreCase("pshop"))) {
                //openPshop(player);
                player.sendMessage("§c§l(!) §cBrzo spustime....");
            }
        }
        return false;
    }

    //TODO: Rewrite na core inventare (po oprave)

    public void openPshop(final Player p) {
        Inventory inv = Bukkit.createInventory(null, 27, "§0Prison Shop");

        ItemStack expShop = new ItemBuilder(Material.EXP_BOTTLE).setName("§bSmena expu")
                .setLore("§7Zde si muzes vymenit svoje", "§7expy za PrisCoiny.", "", "§eKliknutim zobrazis menu").build();

        inv.setItem(11, expShop);

        p.openInventory(inv);
    }

    public void openExpShop(final Player p) {
        Inventory inv = Bukkit.createInventory(null, 45, "§0Smena Exp -> PC");

        if(p.getLevel() >= 25) { // 25 LVL = 1 PC
            ItemStack item = new ItemBuilder(Material.EXP_BOTTLE).setName("§625 LVL -> 1 PC").build();
            inv.setItem(11, item);
        } else {
            ItemStack no = new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short)14).setName("§cNemas dostatek LVL")
                    .setLore("§725 LVL -> 1 PC").build();
            inv.setItem(11, no);
        }
        if(p.getLevel() >= 50) { // 50 LVL = 2 PC
            ItemStack item = new ItemBuilder(Material.EXP_BOTTLE).setName("§650 LVL -> 2 PC").build();
            inv.setItem(12, item);
        } else {
            ItemStack no = new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short)14).setName("§cNemas dostatek LVL")
                    .setLore("§750 LVL -> 2 PC").build();
            inv.setItem(12, no);
        }
        if(p.getLevel() >= 100) { // 100 LVL = 6 PC
            ItemStack item = new ItemBuilder(Material.EXP_BOTTLE).setName("§6100 LVL -> 6 PC").build();
            inv.setItem(13, item);
        } else {
            ItemStack no = new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short)14).setName("§cNemas dostatek LVL")
                    .setLore("§7100 LVL -> 6 PC").build();
            inv.setItem(13, no);
        }
        if(p.getLevel() >= 200) { // 200 LVL = 15 PC
            ItemStack item = new ItemBuilder(Material.EXP_BOTTLE).setName("§6200 LVL -> 15 PC").build();
            inv.setItem(14, item);
        } else {
            ItemStack no = new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short)14).setName("§cNemas dostatek LVL")
                    .setLore("§7200 LVL -> 15 PC").build();
            inv.setItem(14, no);
        }

        p.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        if (e.getInventory().getTitle().equals("§0Prison Shop")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if(e.getSlot() == 11) {
                openExpShop(p);
            }
        }
        if(e.getInventory().getTitle().equals("§0Smena Exp -> PC")){
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if(e.getSlot() == 11) {
                if(p.getLevel() >= 25) { // 25 LVL = 1 PC
                    p.setExhaustion(p.getTotalExperience() - 560);
                    PrisCoins.giveCoins(p, 1);
                    p.sendMessage("§eVymenil jsi §a25LVL §eza §61 PC.");
                    p.closeInventory();
                } else {
                    p.sendMessage("§c§l(!) §cNemas dostatek LVL k prevodu!");
                }
            }

            if(e.getSlot() == 12) {
                if(p.getLevel() >= 50) { // 50 LVL = 2 PC
                    p.setExp(p.getLevel() - 3965);
                    PrisCoins.giveCoins(p, 2);
                    p.sendMessage("§eVymenil jsi §a50LVL §eza §62 PC.");
                    p.closeInventory();
                } else {
                    p.sendMessage("§c§l(!) §cNemas dostatek LVL k prevodu!");
                }
            }

            if(e.getSlot() == 13) {
                if(p.getLevel() >= 100) { // 100 LVL = 6 PC
                    p.setExhaustion(p.getTotalExperience() - 29315);
                    PrisCoins.giveCoins(p, 5);
                    p.sendMessage("§eVymenil jsi §a100LVL §eza §66 PC.");
                    p.closeInventory();
                } else {
                    p.sendMessage("§c§l(!) §cNemas dostatek LVL k prevodu!");
                }
            }

            if(e.getSlot() == 14) {
                if(p.getLevel() >= 200) { // 200 LVL = 15 PC
                    p.setExhaustion(p.getTotalExperience() - 155015);
                    PrisCoins.giveCoins(p, 15);
                    p.sendMessage("§eVymenil jsi §a200LVL §eza §615 PC.");
                    p.closeInventory();
                } else {
                    p.sendMessage("§c§l(!) §cNemas dostatek LVL k prevodu!");
                }
            }
        }
    }
}
