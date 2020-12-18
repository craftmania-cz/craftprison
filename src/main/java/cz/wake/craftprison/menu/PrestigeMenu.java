package cz.wake.craftprison.menu;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.craftmania.craftcore.spigot.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryProvider;
import cz.craftmania.crafteconomy.utils.VaultUtils;
import cz.wake.craftprison.Main;
import cz.wake.craftprison.modules.ActionBarProgress;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.objects.CraftPlayer;
import cz.wake.craftprison.objects.Prestige;
import cz.wake.craftprison.objects.Rank;
import cz.wake.craftprison.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PrestigeMenu implements InventoryProvider {

    PrisonManager pm = new PrisonManager();

    @Override
    public void init(Player player, InventoryContents contents) {

        int row = 1;
        int columm = 0;

        contents.fillRow(0, ClickableItem.empty(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§f ").build()));
        contents.fillRow(4, ClickableItem.empty(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§f ").build()));

        contents.set(0, 4, ClickableItem.empty(new ItemBuilder(Material.FEATHER).setName("§ePrestige odměny").setLore("§7Postupováním napříč Prisonu", "§7zvyšováním Prestige si odemykáš odměny.", "", "§7Zároveň se s každou Prestige", "§7ztíží tvůj postup napříč", "§7dalšími ranky a prestiges.", "", "§7Toto menu obsahuje pouze", "§7seznam odměn pro Prestiges.").build()));

        CraftPlayer craftPlayer = pm.getCraftPlayer(player);

        for (Prestige prestige : Prestige.values()) {
            if (prestige.getWeight() < craftPlayer.getPrestige()) {
                ArrayList<String> lore = new ArrayList<>();
                lore.add("§7Prestige dokončena.");
                if (prestige.getDescription() != null) {
                    lore.add("");
                    lore.add("§bZískal jsi:");
                    lore.add(" §f- §7" + prestige.getDescription());
                } else {
                    lore.add(" §f- Žádná odměna");
                }
                contents.set(row, columm, ClickableItem.empty(new ItemBuilder(Material.EMERALD_BLOCK).setName("§a§l" + prestige.getWeight()).setLore(lore).build()));
            } else if (prestige.getWeight() == craftPlayer.getPrestige()) {
                ArrayList<String> lore = new ArrayList<>();
                lore.add("§7Aktuální prestige.");
                if (prestige.getDescription() != null) {
                    lore.add("");
                    lore.add("§bZískal jsi:");
                    lore.add(" §f- §7" + prestige.getDescription());
                } else {
                    lore.add(" §f- Žádná odměna");
                }
                contents.set(row, columm, ClickableItem.empty(new ItemBuilder(Material.GOLD_BLOCK).setName("§e§l" + prestige.getWeight()).setLore(lore).build()));
            } else if (prestige.getWeight() == (craftPlayer.getPrestige() + 1)) {
                ArrayList<String> lore = new ArrayList<>();
                lore.add("§cNedokončená prestige.");
                lore.add("§7Cena: §f" + PlayerUtils.formatMoney(craftPlayer.getRank().getPriceByPrestige(craftPlayer.getPrestige()) * 1.5)); // Rank Z * 1.5
                lore.add("§7Dokončeno: §f" + ActionBarProgress.getPercent(Main.getInstance().getEconomy().getBalance(player), craftPlayer.getRank().getPriceByPrestige(craftPlayer.getPrestige()) * 1.5) + "%");
                if (prestige.getDescription() != null) {
                    lore.add("");
                    lore.add("§aDostaneš:");
                    lore.add(" §f- §7" + prestige.getDescription());
                } else {
                    lore.add(" §f- Žádná odměna");
                }
                contents.set(row, columm, ClickableItem.empty(new ItemBuilder(Material.DIAMOND_BLOCK).setGlowing().setName("§b§l" + prestige.getWeight()).setLore(lore).build()));
            } else {
                contents.set(row, columm, ClickableItem.of(new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE)
                        .setName("§8§l" + prestige.getWeight())
                        .setLore("§7Musíš dokončit předchozí prestige", "§7aby jsi mohl tuto prestige zobrazit.").build(), e -> {
                }));
            }
            columm++;
            if (columm == 9) {
                row++;
                columm = 0;
            }
        }

        if (craftPlayer.getRank() == Rank.getLast()) {
            contents.set(4, 4, ClickableItem.of(new ItemBuilder(Material.GREEN_CONCRETE_POWDER).setName("§aNavýšit Prestige")
                    .setLore("§7Kliknutím zvyšíš svojí Prestige", "§7na úroveň: " + (craftPlayer.getPrestige() + 1), "",
                            "§7Cena: §f" + PlayerUtils.formatMoney(craftPlayer.getRank().getPriceByPrestige(craftPlayer.getPrestige()) * 1.5) ,
                            "§7Dokončeno: §f" + ActionBarProgress.getPercent(Main.getInstance().getEconomy().getBalance(player), craftPlayer.getRank().getPriceByPrestige(craftPlayer.getPrestige()) * 1.5) + "%" ,
                            "", "§cKliknutím se ti vyresetují", "§cpeníze na 0$ a Rank A!", "", "§eKlikni pro zvýšení Prestige").build(), (click) -> {
                long moneyToTake = (long) (craftPlayer.getRank().getPriceByPrestige(craftPlayer.getPrestige()) * 1.5);

                if (Main.getInstance().getEconomy().getBalance(player) < moneyToTake) {
                    player.sendMessage("§c§l[!] §cNemáš dostatek peněz na provedení Prestige Up!");
                    return;
                }

                Prestige newPrestige = Prestige.getByWeight(craftPlayer.getPrestige() + 1);

                if (newPrestige != null) {
                    for (String permission : newPrestige.getPermission()) {
                        PlayerUtils.addPermission(player, permission);
                    }
                }

                // Setup
                craftPlayer.setPrestige(craftPlayer.getPrestige() + 1);
                craftPlayer.setRank(Rank.A);
                Main.getInstance().getEconomy().withdrawPlayer(player, Main.getInstance().getEconomy().getBalance(player));
                player.sendMessage("§b§l[Prestiges] §eÚspěšně jsi navýšil svojí Prestige na §c§l" + craftPlayer.getPrestige());
                player.teleport(Rank.A.getLocation());

                Bukkit.getOnlinePlayers().forEach((player1 -> {
                    player1.sendMessage("§b§l[Prestiges] §eHráč §a§l" + player.getName() + " §enavýšil svojí Prestige na úroveň §c§l" + craftPlayer.getPrestige());
                }));

            }));
        } else { // Nemá rank Z
            contents.set(4, 4, ClickableItem.empty(new ItemBuilder(Material.BARRIER).setName("§cNelze provést Prestige Up").setLore("§7Nedosáhl jsi ranku Z").build()));
        }

        //contents.set(4, 4, ClickableItem.of(new ItemBuilder(Material.)))

    }


    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
