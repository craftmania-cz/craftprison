package cz.wake.craftprison.menu;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.craftmania.craftcore.spigot.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryProvider;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.objects.CraftPlayer;
import cz.wake.craftprison.objects.Rank;
import cz.wake.craftprison.utils.PlayerUtils;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class RankupVerifyMenu implements InventoryProvider {

    final PrisonManager pm = new PrisonManager();

    @Override
    public void init(Player p, InventoryContents contents) {

        contents.set(1, 2, ClickableItem.of(
                new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§a§lANO")
                        .addLore("§7Kliknutim provedes rankup", "§7na rank: " + pm.getColoredNextPlayerRank(p), "", "§cTato akce je nevratna!").build(), e -> {
                    Rank actualRank = pm.getPlayerRank(p);
                    if (!(actualRank == Rank.A)) { // V zakladu hrac nema zadny rank pravo
                        PlayerUtils.removePermission(p, actualRank.getPermission());
                    }
                    Rank nextRank = actualRank.getNext();
                    PlayerUtils.addPermission(p, nextRank.getPermission());
                    PlayerUtils.addPermission(p, "quicksell.shop." + nextRank.getName());
                    PlayerUtils.addPermission(p, "deluxetags.tag." + nextRank.getName().toLowerCase());
                    PlayerUtils.addPermission(p, "essentials.warps." + nextRank.getName().toLowerCase());
                    CraftPlayer cp = pm.getPlayers().get(p);
                    //Main.getEconomy().withdrawPlayer(p, (double) nextRank.getPrice()); //TODO: Nefunkční vault - přidat CraftEconomy
                    cp.setRank(nextRank);
                    PlayerUtils.randomFireworks(p.getLocation());
                    PlayerUtils.sendRankUpMessage(p);
                        /*Advancement.builder(new NamespacedKey(Main.getInstance(), "craftprison"))
                                .title("Novy rank: " + pm.getColoredPlayerRank(p)).description("_").icon("minecraft:diamond")
                                .announce(false).hidden(false).toast(true).frame(AdvancementManager.FrameType.GOAL).build()
                                .show(Main.getInstance(), p);*/
                    for (String perm : nextRank.getCommands()) {
                        if (perm.length() > 1) {
                            PlayerUtils.addPermission(p, perm);
                        }
                    }
                    p.closeInventory();
                }
        ));

        contents.set(1, 6, ClickableItem.of(
                new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setName("§c§lNE")
                        .addLore("§7Kliknutim zrusis rankup.").build(), e -> p.closeInventory()
        ));

    }

    @Override
    public void update(Player player, InventoryContents contents) {
    }
}
