package cz.wake.craftprison.menu;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.craftmania.craftcore.spigot.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryProvider;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HelpMenu implements InventoryProvider {

    @Override
    public void init(Player player, InventoryContents contents) {
        ItemStack etokens = new ItemBuilder(Material.DIAMOND_PICKAXE)
                .setGlowing()
                .setName("§bEnchant tokeny")
                .setLore("", "§7K vylepšení svého krumpáče potřebuješ §eenchant",
                        "§etokeny§7. Tyto tokeny můžeš získat za rankup,",
                        "§7vote party, z piňáty, z questů nebo crates.",
                        "§7Drž krumpáč v ruce a použij §e/enchant§7 pro zobrazení",
                        "§7všech enchantů a jejich cen.",
                        "", PlaceholderAPI.setPlaceholders(player, "§bNyní máš %tokens_balance_format% ET."))
                .hideAllFlags().build();

        ItemStack mine = new ItemBuilder(Material.COBBLESTONE)
                .setName("§aDoly")
                .setLore("", "§7Každý hráč začína s rankem §eA§7. Pomocí příkazu §e/mine",
                        "§7se teleportuješ na svůj aktuální důl. Můžeš ale",
                        "§7použít §e/mine [písmenko]§7 a teleportovat se na",
                        "§7důl, do kterého máš přístup. Příkazem §e/ranks",
                        "§7si zobrazíš podrobnosti o všech rankech.",
                        "", "§bKlikni pro zobrazení podrobností o rankech.")
                .hideAllFlags().build();

        ItemStack prestige = new ItemBuilder(Material.NETHER_STAR)
                .setName("§6Prestiges")
                .setLore("", "§7Po dokončení ranku §eZ§7 si můžeš zvýšit svoji prestige.",
                        "§7Každá prestige zvýši každou cenu rankupu o 10%,",
                        "§7ale taky dostaneš o 5% peněz víc. Za každou novou",
                        "§7prestige získaš odměnu. Více podrobností se dozvíš",
                        "§7v příkazu §e/prestige§7.",
                        "", "§bKlikni pro zobrazení podrobností o prestige.")
                .hideAllFlags().build();

        ItemStack quests = new ItemBuilder(Material.BOOK)
                .setName("§dQuesty")
                .setLore("", "§7Pomocí příkazu §e/quests§7 si zobrazíš §eklasické",
                        "§edenní§7 a §etýdenní questy§7. Za jejich splnění můžeš",
                        "§7získat ET, quest pointy a EXP.",
                        "", "§bKlikni pro zobrazení questů.")
                .hideAllFlags().build();

        ItemStack backpack = new ItemBuilder(Material.CHEST)
                .setName("§9Backpacky")
                .setLore("", "§7Od 2. prestige získaš možnost vlastnit §ebackpack§7.",
                        "§7Backpack ti rozšíří tvůj základní inventář",
                        "§7o několik slotů, do kterých se ti budou ukládat blocky",
                        "§7pokud se ti zaplní inventář.")
                .hideAllFlags().build();

        contents.set(1, 3, ClickableItem.of(mine, event -> {
            player.performCommand("ranks");
        }));

        contents.set(1, 4, ClickableItem.of(prestige, event -> {
            player.performCommand("prestige");
        }));

        contents.set(1, 5, ClickableItem.empty(etokens));

        contents.set(2, 3, ClickableItem.of(quests, event -> {
            player.performCommand("quests");
        }));

        contents.set(2, 5, ClickableItem.empty(backpack));
    }

    @Override
    public void update(Player player, InventoryContents contents) {

    }
}
