package cz.wake.craftprison.menu;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.craftmania.craftcore.spigot.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryProvider;
import cz.craftmania.craftcore.spigot.xseries.XSound;
import cz.wake.craftprison.Main;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.objects.CraftPlayer;
import cz.wake.craftprison.objects.Rank;
import cz.wake.craftprison.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

public class RankupVerifyMenu implements InventoryProvider {

    final PrisonManager pm = new PrisonManager();

    @Override
    public void init(Player player, InventoryContents contents) {

        contents.fillRow(0, ClickableItem.empty(new ItemBuilder(Material.BLUE_STAINED_GLASS_PANE).setName("§f ").build()));
        contents.fillRow(4, ClickableItem.empty(new ItemBuilder(Material.BLUE_STAINED_GLASS_PANE).setName("§f ").build()));

        CraftPlayer craftPlayer = pm.getPlayers().get(player);

        contents.set(2, 2, ClickableItem.of(
                new ItemBuilder(Material.GREEN_CONCRETE_POWDER).setName("§a§lANO")
                        .addLore("§7Kliknutím provedeš", "§7rank up na rank: §f" + pm.getNextRank(player).getName(), "", "§7Bude ti odečteno: §6" + PlayerUtils.formatMoney(craftPlayer.getRank().getNext().getPriceByPrestige(craftPlayer.getPrestige())) + "$", "", "§cTato akce je nevratná!").build(), e -> {
                    Rank actualRank = pm.getPlayerRank(player);
                    if (!(actualRank == Rank.A)) { // V zakladu hrac nema zadny rank pravo
                        PlayerUtils.removePermission(player, actualRank.getPermission());
                    }
                    Rank nextRank = actualRank.getNext();
                    PlayerUtils.addPermission(player, nextRank.getPermission());
                    Main.getInstance().getEconomy().withdrawPlayer(player, (double) nextRank.getPriceByPrestige(craftPlayer.getPrestige()));
                    craftPlayer.setRank(nextRank);
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "tokens add " + player.getName() + " " + nextRank.getEnchantToken());
                    player.sendMessage("§e§l[T] §eBylo ti přidáno " + nextRank.getEnchantToken() + " EnchantTokenů.");
                    PlayerUtils.randomFireworks(player.getLocation());
                    XSound.BLOCK_NOTE_BLOCK_BASS.play(player, 1.0f, 1.0f);
                    PlayerUtils.sendRankUpMessage(player);
                    player.closeInventory();
                }
        ));

        contents.set(2, 6, ClickableItem.of(
                new ItemBuilder(Material.RED_CONCRETE_POWDER).setName("§c§lNE")
                        .addLore("§7Kliknutím zrušíš rank up!").build(), e -> player.closeInventory()
        ));

    }

    @Override
    public void update(Player player, InventoryContents contents) {
    }
}
