package cz.wake.craftprison.menu;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.craftmania.craftcore.spigot.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryProvider;
import cz.wake.craftprison.Main;
import cz.wake.craftprison.modules.ActionBarProgress;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.objects.CraftPlayer;
import cz.wake.craftprison.objects.Rank;
import cz.wake.craftprison.utils.PlayerUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class RanksMenu implements InventoryProvider {

    PrisonManager pm = new PrisonManager();

    @Override
    public void init(Player p, InventoryContents contents) {

        int row = 1;
        int columm = 0;

        contents.fillRow(0, ClickableItem.empty(new ItemBuilder(Material.BLUE_STAINED_GLASS_PANE).setName("§f ").build()));
        contents.fillRow(4, ClickableItem.empty(new ItemBuilder(Material.BLUE_STAINED_GLASS_PANE).setName("§f ").build()));

        for (Rank rank : Rank.values()) {
            if (pm.getPlayerRank(p).isAtLeast(rank)) {

                ArrayList<String> lore = new ArrayList<>();
                lore.add("§7Rank dokončen!");
                lore.add("");
                lore.add("§bZískal jsi:");
                if (rank.getEnchantToken() != 0) {
                    lore.add(" §f- " + rank.getEnchantToken() + "x EnchantToken");
                } else {
                    lore.add(" §f- Nic..."); // Rank A
                }
                lore.add("");
                lore.add("§eKliknutím se portneš na důl");

                contents.set(row, columm, ClickableItem.of(new ItemBuilder(rank.getItem()).setName("§a§l" + rank.getName())
                        .setLore(lore).build(), e -> {
                    p.performCommand("mine " + rank.getName().toLowerCase());
                    p.closeInventory();
                }));
            } else {
                if (pm.getPlayerRank(p).getNext() == rank) {

                    CraftPlayer craftPlayer = pm.getCraftPlayer(p); // 4k, 0.03%

                    ArrayList<String> lore = new ArrayList<>();
                    lore.add("§7Cena: §f" + PlayerUtils.formatMoney((double) craftPlayer.getRank().getNext().getPriceByPrestige(craftPlayer.getPrestige())) + "§6$");
                    lore.add("§7Dokončeno: §f" + ActionBarProgress.getPercent(Main.getInstance().getEconomy().getBalance(p), (double) craftPlayer.getRank().getNext().getPriceByPrestige(craftPlayer.getPrestige())) + "%");
                    lore.add("");
                    lore.add("§bZískáš:");
                    if (rank.getEnchantToken() != 0) {
                        lore.add(" §f- " + rank.getEnchantToken() + "x EnchantToken");
                    } else {
                        lore.add(" §f- Nic..."); // Rank A
                    }

                    contents.set(row, columm, ClickableItem.of(new ItemBuilder(rank.getItem()).setGlowing()
                            .setName("§e§l" + rank.getName()).setLore(lore).build(), e -> {
                    }));
                } else {
                    contents.set(row, columm, ClickableItem.of(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
                            .setName("§8§k" + rank.getName())
                            .setLore("§7Musíš dokončit rank", "§7před tímto k odemknutí.").build(), e -> {
                    }));
                }
            }
            columm++;
            if (columm == 9) {
                row++;
                columm = 0;
            }
        }


    }

    @Override
    public void update(Player player, InventoryContents contents) {
    }
}
