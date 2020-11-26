package cz.wake.craftprison.modules;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.craftmania.craftcore.spigot.inventory.builder.ClickableItem;
import cz.craftmania.craftcore.spigot.inventory.builder.SmartInventory;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryContents;
import cz.craftmania.craftcore.spigot.inventory.builder.content.InventoryProvider;
import cz.wake.craftprison.Main;
import cz.wake.craftprison.modules.pickaxe.PickaxeUpgrade;
import cz.wake.craftprison.objects.CraftPlayer;
import cz.wake.craftprison.objects.Rank;
import cz.wake.craftprison.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class PrisonManager {

    public static HashMap<Player, CraftPlayer> players = new HashMap<>();
    public static HashSet<String> wgRegions = new HashSet<>();
    private static Rank rank;

    /*
        Pouzivat pouze pri nacitani dat z SQL!
     */
    public static void loadPlayer(final Player p) {

        CraftPlayer cp = null;

        if (!Main.getInstance().getMySQL().hasData(p)) {
            // Vytvoreni default dat
            Main.getInstance().getMySQL().insertDefaultData(p);
            cp = new CraftPlayer(p, Rank.A, 0, 0, 0, 0);

            //Player counter
            int count = Main.getInstance().getConfig().getInt("player-counter") + 1;
            Bukkit.getOnlinePlayers().forEach(op -> op.sendMessage("§e" + p.getName() + " §7se pripojil na Prison (#" + count + ")"));
            Main.getInstance().getConfig().set("player-counter", count);
            Main.getInstance().saveConfig();

            //Krumpac
            p.getInventory().addItem(PickaxeUpgrade.getDefaultPickaxe(p.getName()));
        } else {
            cp = Main.getInstance().getMySQL().getCraftPlayerFromSQL(p);
        }

        // Prevence proti NPE z SQL
        if (cp == null) {
            cp = new CraftPlayer(p, Rank.A, 0, 0, 0, 0);
        }

        players.put(p, cp);
    }

    public static HashMap<Player, CraftPlayer> getCraftPlayersCache() {
        return players;
    }

    public CraftPlayer getCraftPlayer(Player p) {
        for (CraftPlayer cp : players.values()) {
            if (cp.getPlayer() == p) {
                return cp;
            }
        }
        return null;
    }

    public Rank getPlayerRank(Player p) {
        return players.get(p).getRank();
    }

    public String getColoredPlayerRank(Player p) {
        return getPlayerRank(p).getDifficulty().getColor().toString() + getPlayerRank(p).getName();
    }

    public String getColoredNextPlayerRank(Player p) {
        return getPlayerNextRank(p).getDifficulty().getColor().toString() + getPlayerNextRank(p).getName();
    }

    public Rank getPlayerNextRank(Player p) throws NullPointerException {
        return getPlayerRank(p).getNext();
    }

    public long getNextRankPrice(Player p) {
        return getPlayerNextRank(p).getPrice();
    }

    public long getActualRankPrice(Player p) {
        return getPlayerRank(p).getPrice();
    }

    public static HashSet<String> getWgRegions() {
        return wgRegions;
    }

    public static void addWorldGuardRegion(String region) {
        wgRegions.add(region);
    }

    public static void registerWgMines() {
        for (Rank r : Rank.getTypes()) {
            // Register
            wgRegions.add(r.getName().toLowerCase());

            // Info
            Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[CraftPrison] " + ChatColor.WHITE + " Dul: " + r.getName() + " byl registrovan jako dul.");
        }
    }

    public Rank getRankObject() {
        return rank;
    }

    public static class RankupVerifyMenu implements InventoryProvider {

        PrisonManager pm = new PrisonManager();

        public static final SmartInventory RANKUP = SmartInventory.builder()
                .id("rankup").provider(new RankupVerifyMenu())
                .size(3, 9).title("[?] Opravdu chces rankup?").build();


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
                        Main.getInstance().getMySQL().rankupPlayerSQL(p, nextRank);
                        PrisCoins.giveCoins(p, nextRank.getPrisCoins());
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

    public static class RanksMenu implements InventoryProvider {

        PrisonManager pm = new PrisonManager();

        public static final SmartInventory RANKS = SmartInventory.builder()
                .id("ranks").provider(new RanksMenu())
                .size(5, 9).title("Prehled ranku").build();

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
                    if (rank.getPrisCoins() != 0) {
                        lore.add(" §f- " + rank.getPrisCoins() + " PrisCoins");
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
                        if (rank.getPrisCoins() != 0) {
                            lore.add(" §f- " + rank.getPrisCoins() + " PrisCoins");
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

    public static class TutorialMenu implements InventoryProvider {

        public static final SmartInventory TUTORIAL = SmartInventory.builder()
                .id("tutorial").provider(new TutorialMenu())
                .size(6, 9).title("Tutorial").build();

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

    public HashMap<Player, CraftPlayer> getPlayers() {
        return players;
    }
}
