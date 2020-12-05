package cz.wake.craftprison.menu;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.craftmania.craftcore.spigot.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryProvider;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.objects.Rank;
import cz.wake.craftprison.utils.PlayerUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class RanksMenu implements InventoryProvider {

    PrisonManager pm = new PrisonManager();

    @Override
    public void init(Player p, InventoryContents contents) {

        int row = 0;
        int columm = 0;

        for (Rank rank : Rank.values()) {
            if (pm.getPlayerRank(p).isAtLeast(rank)) {

                ArrayList<String> lore = new ArrayList<>();
                lore.add("§7Rank dokoncen.");
                lore.add("");
                lore.add("§bZpristupneno:");
                if (!rank.getReward().equals("")) {
                    lore.add(" §f- " + rank.getReward());
                }
                if (rank.getEnchantToken() != 0) {
                    lore.add(" §f- " + rank.getEnchantToken() + " PrisCoins");
                } else {
                    lore.add(" §f- Nic..."); // Rank A
                }
                lore.add("");
                lore.add("§eKliknutim te portnu na dul");

                contents.set(row, columm, ClickableItem.of(new ItemBuilder(rank.getItem()).setName("§a" + rank.getName())
                        .setLore(lore).build(), e -> {
                    p.performCommand("warp " + rank.getName().toLowerCase());
                    p.closeInventory();
                }));
            } else {
                if (pm.getPlayerRank(p).getNext() == rank) {

                    ArrayList<String> lore = new ArrayList<>();
                    lore.add("§7Cena: §f" + PlayerUtils.formatMoney((double) rank.getPrice()) + "§a$");
                    //lore.add("§7Dokonceno: §f" + ActionBarProgress.getPercent(Main.getEconomy().getBalance(p), (double) rank.getPrice()) + "%");
                    lore.add("");
                    lore.add("§bZiskas:");
                    if (!rank.getReward().equals("")) {
                        lore.add(" §f- " + rank.getReward());
                    }
                    if (rank.getEnchantToken() != 0) {
                        lore.add(" §f- " + rank.getEnchantToken() + " PrisCoins");
                    } else {
                        lore.add(" §f- Nic..."); // Rank A
                    }

                    contents.set(row, columm, ClickableItem.of(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
                            .setName("§e" + rank.getName()).setLore(lore).build(), e -> {
                    }));
                } else {
                    contents.set(row, columm, ClickableItem.of(new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE)
                            .setName("§8§k" + rank.getName())
                            .setLore("§7Musis dokoncit rank", "§7pred timto k odemknuti.").build(), e -> {
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
