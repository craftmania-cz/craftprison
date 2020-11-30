package cz.wake.craftprison.menu;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.craftmania.craftcore.spigot.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryProvider;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

public class TutorialMenu implements InventoryProvider {

    @Override
    public void init(Player p, InventoryContents contents) {
        contents.set(0, 4, ClickableItem.of(new ItemBuilder(Material.OBSIDIAN).setName("§9Jak na Prison 2.0")
                .setLore("§7Seznam kratkych tutorialu", "§7se kterymi ziskas prehled", "§7na nasem Prisonu.").build(), e -> {
        }));

        contents.set(2,1, ClickableItem.of(new ItemBuilder(Material.EMERALD_ORE).setName("§aDoly")
                .setLore("§7Na nasem prisonu najdes", "§7mnoho dolu, pojmenovanych po", "§7pohadkovych postavach.",
                        "§7S kazdym dolem, ziskas i", "§7specialni tag s nazvem dolu.", "", "§eSeznam dolu: §b/ranks").build(), e -> {
        }));

        contents.set(2, 3, ClickableItem.of(new ItemBuilder(Material.LADDER).setName("§cRanky a Prestige?")
                .setLore("§7Kazdy jeden dul = jeden rank.", "§7Kazdy hrac u nas zacina", "§7s obtiznosti §eEasy§7.", "",
                        "§7Po dokonceni zakladniho Prisonu", "§7se ti odemkne prikaz §f/obtiznost", "§7Se kterym si muzes cely",
                        "§7svuj proces vyresetovat", "§7a dat si tezsi obtiznost!", "", "§eZobrazeni ranku: §b/rank", "§eRankup na dalsi uroven: §b/rankup").build(), e -> {
        }));

        contents.set(2, 5, ClickableItem.of(new ItemBuilder(Material.IRON_SWORD).setName("§dPvP Mine")
                .setLore("§7PvP se zde take nachazi,", "§7ale ve specialni podobe dolu.", "§7Kde muzes ziskat vetsi",
                        "§7obnos penez za cenu smrti.", "", "§cOdemknuti od ranku: §fBender", "", "§eTeleport: §b/pvp").hideAllFlags().build(), e -> {
        }));


        contents.set(2, 7, ClickableItem.of(new ItemBuilder(Material.OAK_LOG).setName("§bOstrovy")
                .setLore("§7Kazdy hrac si muze vytvorit", "§71x vlastni ostrov o", "§7rozloze 400x400 blocku.",
                        "", "§cOdemknuti od ranku: §fD", "", "§eVytvoreni ostrova: §b/is").build(), e -> {
        }));

        contents.set(4, 2, ClickableItem.of(new ItemBuilder(Material.DIAMOND_PICKAXE).setName("§eTvuj krumpac")
                .setLore("§7Krumpac si muzes i", "§7vylepsit abys mohl tezit", "§7rychleji a vice efektivne.", "", "§eUpgrade: §b/pickaxe §7nebo §b/krump")
                .addEnchant(Enchantment.DURABILITY, 1)
                .hideAllFlags().build(), e-> {
        }));

        contents.set(4, 4, ClickableItem.of(new ItemBuilder(Material.CLOCK).setName("§3Tagy")
                .setLore("§7Kazdym dosazenym dolem", "§7ziskavas specialni tagy.", "§7Muzes si je nastavit", "§7podle aktualniho dolu", "§7nebo zcela nahodne.")
                .hideAllFlags().build(), e -> {
        }));

        contents.set(4, 6, ClickableItem.of(new ItemBuilder(Material.GHAST_TEAR).setName("§6PrisCoiny")
                .setLore("§7PrisCoiny jsou specialni", "§7virtualni Prison mena", "§7pomoci, ktere muzes upgradnout", "§7svuj krumpac.", "",
                        "§7PrisCoiny ziskavas za", "§7rankupovani, reseni hadanek", "§7nebo promenou za XP.", "",
                        "§eStav PrisCoinu: §b/pcoins", "§eVymena za XP: §b/pshop")
                .hideAllFlags().build(), e -> {
        }));


    }

    @Override
    public void update(Player player, InventoryContents contents) {
    }
}
