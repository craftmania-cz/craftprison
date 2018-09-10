package cz.wake.craftprison.statistics.menu;

import cz.wake.craftcore.utils.items.ItemBuilder;
import cz.wake.craftprison.Main;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.objects.CraftPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class StatisticsMenu implements Listener {

    public static void open(Player p) {
        Inventory inv = Bukkit.createInventory(null, 27, "§0Statistiky");

        PrisonManager pm = new PrisonManager();
        CraftPlayer cp = pm.getCraftPlayer(p);

        ItemStack blocksBroken = new ItemBuilder(Material.DIAMOND_PICKAXE).setName("§eZnicene blocky").addLore("§7" + cp.getMinedBlocks()).
                hideAllFlags().build();

        ItemStack deaths = new ItemBuilder(Material.SKULL_ITEM).setName("§eSmrti").addLore("§7" + cp.getDeaths()).
                hideAllFlags().build();

        ItemStack kills = new ItemBuilder(Material.WOOD_SWORD).setName("§eZabiti").addLore("§7" + cp.getKills()).
                hideAllFlags().build();

        inv.setItem(12, blocksBroken);
        inv.setItem(13, deaths);
        inv.setItem(14, kills);

        p.openInventory(inv);
    }

    //TODO: Podpora offline UUID
    public static void openOffline(Player player, String offlinePlayer) {
        if (!Main.getInstance().getMySQL().hasDataByName(offlinePlayer)) {
            player.sendMessage("Není u nás veden tento účet! blázne ...");
            return;
        }

        Inventory inv = Bukkit.createInventory(null, 27, "§0Statistiky (" + offlinePlayer + ")");

        ItemStack blocksBroken = new ItemBuilder(Material.DIAMOND_PICKAXE).setName("§eZnicene blocky").addLore("§7" + Main.getInstance().getMySQL().getMinedBlocks(offlinePlayer)).
                hideAllFlags().build();

        ItemStack deaths = new ItemBuilder(Material.SKULL_ITEM).setName("§eSmrti").addLore("§7" + Main.getInstance().getMySQL().getDeaths(offlinePlayer)).
                hideAllFlags().build();

        ItemStack kills = new ItemBuilder(Material.WOOD_SWORD).setName("§eZabiti").addLore("§7" + Main.getInstance().getMySQL().getKills(offlinePlayer)).
                hideAllFlags().build();

        inv.setItem(12, blocksBroken);
        inv.setItem(13, deaths);
        inv.setItem(14, kills);

        player.openInventory(inv);

    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!e.getInventory().getTitle().startsWith("§0Statistiky")) {
            return;
        }
        e.setCancelled(true);
    }
}
