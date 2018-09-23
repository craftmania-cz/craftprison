package cz.wake.craftprison.statistics.menu;

import cz.wake.craftcore.utils.items.ItemBuilder;
import cz.wake.craftprison.Main;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.objects.CraftPlayer;
import cz.wake.craftprison.objects.StatsPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

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
            player.sendMessage("Tento ucet neni u nas veden, blazne...");
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

    public static void  openTop(Player p) {
        Inventory inv = Bukkit.createInventory(null, 27, "§0Statistiky: TOP 5");

        List<StatsPlayer> blocksbroken = Main.getInstance().getMySQL().getTopMinedBlocks(5);
        ItemStack blocksBroken = new ItemBuilder(Material.DIAMOND_PICKAXE).setName("§eZnicene blocky").addLore("§7Tvoje pozice: §f#" + Main.getInstance().getMySQL().getTopMinedBlocksPosition(p), "§f",
                "§e#1: §f" + blocksbroken.get(0).getPlayer().getName() + " §7(" + NumberFormat.getNumberInstance(Locale.US).format(blocksbroken.get(0).getMineblocks()) + "§7)",
                "§e#2: §f" + blocksbroken.get(1).getPlayer().getName() + " §7(" + NumberFormat.getNumberInstance(Locale.US).format(blocksbroken.get(1).getMineblocks()) + "§7)",
                "§e#3: §f" + blocksbroken.get(2).getPlayer().getName() + " §7(" + NumberFormat.getNumberInstance(Locale.US).format(blocksbroken.get(2).getMineblocks()) + "§7)",
                "§e#4: §f" + blocksbroken.get(3).getPlayer().getName() + " §7(" + NumberFormat.getNumberInstance(Locale.US).format(blocksbroken.get(3).getMineblocks()) + "§7)",
                "§e#5: §f" + blocksbroken.get(4).getPlayer().getName() + " §7(" + NumberFormat.getNumberInstance(Locale.US).format(blocksbroken.get(4).getMineblocks()) + "§7)").
                hideAllFlags().build();

        List<StatsPlayer> deathss = Main.getInstance().getMySQL().getTopDeaths(5);
        ItemStack deaths = new ItemBuilder(Material.SKULL_ITEM).setName("§eSmrti").addLore("§7Tvoje pozice: §f#" + Main.getInstance().getMySQL().getTopDeathsPosition(p), "§f",
                "§e#1: §f" + deathss.get(0).getPlayer().getName() + " §7(" + NumberFormat.getNumberInstance(Locale.US).format(deathss.get(0).getDeaths()) + "§7)",
                "§e#2: §f" + deathss.get(1).getPlayer().getName() + " §7(" + NumberFormat.getNumberInstance(Locale.US).format(deathss.get(1).getDeaths()) + "§7)",
                "§e#3: §f" + deathss.get(2).getPlayer().getName() + " §7(" + NumberFormat.getNumberInstance(Locale.US).format(deathss.get(2).getDeaths()) + "§7)",
                "§e#4: §f" + deathss.get(3).getPlayer().getName() + " §7(" + NumberFormat.getNumberInstance(Locale.US).format(deathss.get(3).getDeaths()) + "§7)",
                "§e#5: §f" + deathss.get(4).getPlayer().getName() + " §7(" + NumberFormat.getNumberInstance(Locale.US).format(deathss.get(4).getDeaths()) + "§7)").
                hideAllFlags().build();

        List<StatsPlayer> kilss = Main.getInstance().getMySQL().getTopKills(5);
        ItemStack kills = new ItemBuilder(Material.WOOD_SWORD).setName("§eZabiti").addLore("§7Tvoje pozice: §f#" + Main.getInstance().getMySQL().getTopKillsPosition(p), "§f",
                "§e#1: §f" + kilss.get(0).getPlayer().getName() + " §7(" + NumberFormat.getNumberInstance(Locale.US).format(kilss.get(0).getKills()) + "§7)",
                "§e#2: §f" + kilss.get(1).getPlayer().getName() + " §7(" + NumberFormat.getNumberInstance(Locale.US).format(kilss.get(1).getKills()) + "§7)",
                "§e#3: §f" + kilss.get(2).getPlayer().getName() + " §7(" + NumberFormat.getNumberInstance(Locale.US).format(kilss.get(2).getKills()) + "§7)",
                "§e#4: §f" + kilss.get(3).getPlayer().getName() + " §7(" + NumberFormat.getNumberInstance(Locale.US).format(kilss.get(3).getKills()) + "§7)",
                "§e#5: §f" + kilss.get(4).getPlayer().getName() + " §7(" + NumberFormat.getNumberInstance(Locale.US).format(kilss.get(4).getKills()) + "§7)").
                hideAllFlags().build();

        inv.setItem(12, blocksBroken);
        inv.setItem(13, deaths);
        inv.setItem(14, kills);

        p.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!e.getInventory().getTitle().startsWith("§0Statistiky")) {
            return;
        }
        e.setCancelled(true);
    }
}
