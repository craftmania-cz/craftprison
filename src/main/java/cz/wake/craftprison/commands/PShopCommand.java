package cz.wake.craftprison.commands;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
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
                openPshop(player);
                //player.sendMessage("§c§l(!) §cBrzo spustime....");
            }
        }
        return false;
    }

    //TODO: Rewrite na core inventare (po oprave)

    public void openPshop(final Player p) {
        Inventory inv = Bukkit.createInventory(null, 27, "§0Prison Shop");

        ItemStack expShop = new ItemBuilder(Material.EXPERIENCE_BOTTLE).setName("§bSmena expu")
                .setLore("§7Zde si muzes vymenit svoje", "§7expy za PrisCoiny.", "", "§eKliknutim zobrazis menu").build();

        inv.setItem(13, expShop);

        p.openInventory(inv);
    }

    public void openExpShop(final Player p) {
        Inventory inv = Bukkit.createInventory(null, 45, "§0Smena Exp -> PC");

        if(this.getLevel(p) >= 25) { // 25 LVL = 1 PC
            ItemStack item = new ItemBuilder(Material.EXPERIENCE_BOTTLE).setName("§625 LVL -> 1 PC").build();
            inv.setItem(11, item);
        } else {
            ItemStack no = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDurability((short)14).setName("§cNemas dostatek LVL")
                    .setLore("§725 LVL -> 1 PC").build();
            inv.setItem(11, no);
        }
        if(this.getLevel(p) >= 75) { // 75 LVL = 3 PC
            ItemStack item = new ItemBuilder(Material.EXPERIENCE_BOTTLE).setName("§675 LVL -> 3 PC").build();
            inv.setItem(12, item);
        } else {
            ItemStack no = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDurability((short)14).setName("§cNemas dostatek LVL")
                    .setLore("§775 LVL -> 3 PC").build();
            inv.setItem(12, no);
        }
        if(this.getLevel(p) >= 150) { // 150 LVL = 7 PC
            ItemStack item = new ItemBuilder(Material.EXPERIENCE_BOTTLE).setName("§6150 LVL -> 7 PC").build();
            inv.setItem(13, item);
        } else {
            ItemStack no = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDurability((short)14).setName("§cNemas dostatek LVL")
                    .setLore("§7150 LVL -> 7 PC").build();
            inv.setItem(13, no);
        }
        if(this.getLevel(p) >= 300) { // 300 LVL = 15 PC
            ItemStack item = new ItemBuilder(Material.EXPERIENCE_BOTTLE).setName("§6300 LVL -> 15 PC").build();
            inv.setItem(14, item);
        } else {
            ItemStack no = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDurability((short)14).setName("§cNemas dostatek LVL")
                    .setLore("§7300 LVL -> 15 PC").build();
            inv.setItem(14, no);
        }

        ItemStack zpet = new ItemBuilder(Material.ARROW).setName("§cZpet").build();
        inv.setItem(40,zpet);

        p.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player p = (Player)e.getWhoClicked();
        if (e.getView().getTitle().equals("§0Prison Shop")) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if(e.getSlot() == 13) {
                openExpShop(p);
            }
        }
        if(e.getView().getTitle().equals("§0Smena Exp -> PC")){
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            if (e.getCurrentItem().getType() == Material.AIR) {
                return;
            }
            if(e.getSlot() == 11) {
                if(p.getLevel() >= 25) { // 25 LVL = 1 PC
                    this.takeLevel(p, 25);
                    PrisCoins.giveCoins(p, 1);
                    p.sendMessage("§eVymenil jsi §a25LVL §eza §61 PC.");
                    p.closeInventory();
                } else {
                    p.sendMessage("§c§l(!) §cNemas dostatek LVL k prevodu!");
                }
            }

            if(e.getSlot() == 12) {
                if(this.getLevel(p) >= 75) { // 75 LVL = 3 PC
                    this.takeLevel(p, 75);
                    PrisCoins.giveCoins(p, 2);
                    p.sendMessage("§eVymenil jsi §a50LVL §eza §62 PC.");
                    p.closeInventory();
                } else {
                    p.sendMessage("§c§l(!) §cNemas dostatek LVL k prevodu!");
                }
            }

            if(e.getSlot() == 13) {
                if(this.getLevel(p) >= 150) { // 150 LVL = 7 PC
                    this.takeLevel(p, 150);
                    PrisCoins.giveCoins(p, 5);
                    p.sendMessage("§eVymenil jsi §a150LVL §eza §65 PC.");
                    p.closeInventory();
                } else {
                    p.sendMessage("§c§l(!) §cNemas dostatek LVL k prevodu!");
                }
            }

            if(e.getSlot() == 14) {
                if(this.getLevel(p) >= 300) { // 300 LVL = 15 PC
                    this.takeLevel(p,300);
                    PrisCoins.giveCoins(p, 15);
                    p.sendMessage("§eVymenil jsi §a300LVL §eza §615 PC.");
                    p.closeInventory();
                } else {
                    p.sendMessage("§c§l(!) §cNemas dostatek LVL k prevodu!");
                }
            }
            if(e.getSlot() == 40){
                this.openPshop(p);
            }
        }
    }

    public int getLevel(final Player player) {
        return player.getLevel();
    }

    public void setLevel(final Player player, int n) {
        player.setLevel(n);
    }

    public void addLevel(final Player player, final int n) {
        this.setLevel(player, this.getLevel(player) + n);
    }

    public void takeLevel(final Player player, final int n) {
        this.setLevel(player, this.getLevel(player) - n);
    }

    public int getExpToLevel(final int n) {
        if (n <= 16) {
            return n * n + 6 * n;
        }
        if (n <= 31) {
            return (int)(2.5 * n * n - 40.5 * n + 360.0);
        }
        return (int)(4.5 * n * n - 162.5 * n + 2220.0);
    }

    public int deltaLevelToExp(final int n) {
        if (n <= 16) {
            return 2 * n + 7;
        }
        if (n <= 31) {
            return 5 * n - 38;
        }
        return 9 * n - 158;
    }
}
