package cz.wake.craftprison.modules;

import cz.wake.craftcore.inventory.ClickableItem;
import cz.wake.craftcore.inventory.SmartInventory;
import cz.wake.craftcore.inventory.content.InventoryContents;
import cz.wake.craftcore.inventory.content.InventoryProvider;
import cz.wake.craftcore.inventory.content.Pagination;
import cz.wake.craftcore.messages.Advancement;
import cz.wake.craftcore.messages.handler.AdvancementManager;
import cz.wake.craftcore.utils.items.ItemBuilder;
import cz.wake.craftprison.Main;
import cz.wake.craftprison.modules.pickaxe.PickaxeUpgrade;
import cz.wake.craftprison.objects.CraftPlayer;
import cz.wake.craftprison.objects.Rank;
import cz.wake.craftprison.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;

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
            cp = new CraftPlayer(p, Rank.TUTORIAL_A, 0, 0, 0, 0);

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
            cp = new CraftPlayer(p, Rank.TUTORIAL_A, 0, 0, 0, 0);
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

    public int getNextRankPrice(Player p) {
        return getPlayerNextRank(p).getPrice();
    }

    public int getActualRankPrice(Player p) {
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
                .size(3,9).title("[?] Opravdu chces rankup?").build();


        @Override
        public void init(Player p, InventoryContents contents) {

            contents.set(1,2, ClickableItem.of(
                    new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short)5).setName("§a§lANO")
                    .addLore("§7Kliknutim provedes rankup", "§7na rank: " + pm.getColoredNextPlayerRank(p), "", "§cTato akce je nevratna!").build(), e -> {
                        Rank actualRank = pm.getPlayerRank(p);
                        if (!(actualRank == Rank.TUTORIAL_A)) { // V zakladu hrac nema zadny rank pravo
                            PlayerUtils.removePermission(p, actualRank.getPermission());
                        }
                        Rank nextRank = actualRank.getNext();
                        PlayerUtils.addPermission(p, nextRank.getPermission());
                        PlayerUtils.addPermission(p, "quicksell.shop." + nextRank.getName());
                        PlayerUtils.addPermission(p, "deluxetags.tag." + nextRank.getName().toLowerCase());
                        CraftPlayer cp = pm.getPlayers().get(p);
                        Main.getEconomy().withdrawPlayer(p, (double)nextRank.getPrice());
                        Main.getInstance().getMySQL().rankupPlayerSQL(p, nextRank);
                        PrisCoins.giveCoins(p, nextRank.getPrisCoins());
                        cp.setRank(nextRank);
                        PlayerUtils.randomFireworks(p.getLocation());
                        PlayerUtils.sendRankUpMessage(p);
                        Advancement.builder(new NamespacedKey(Main.getInstance(), "craftprison"))
                                .title("Novy rank: " + pm.getColoredPlayerRank(p)).description("_").icon("minecraft:diamond")
                                .announce(false).hidden(false).toast(true).frame(AdvancementManager.FrameType.GOAL).build()
                                .show(Main.getInstance(), p);
                        for(String perm : nextRank.getCommands()) {
                            if(perm.length() > 1) {
                                PlayerUtils.addPermission(p, perm);
                            }
                        }
                        p.closeInventory();
                    }
            ));

            contents.set(1, 6, ClickableItem.of(
                    new ItemBuilder(Material.STAINED_GLASS_PANE).setDurability((short)14).setName("§c§lNE")
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
                .size(6,9).title("Prehled ranku").build();

        @Override
        public void init(Player p, InventoryContents contents) {

            Pagination pagination = contents.pagination();
            //contents.newIterator()

        }

        @Override
        public void update(Player player, InventoryContents contents){}

    }

    public static class TutorialMenu implements InventoryProvider {

        public static final SmartInventory TUTORIAL = SmartInventory.builder()
                .id("tutorial").provider(new TutorialMenu())
                .size(6,9).title("Tutorial").build();

        @Override
        public void init(Player p, InventoryContents contents) {
            contents.set(0, 4, ClickableItem.of(new ItemBuilder(Material.OBSIDIAN).setName("§9Jak na Prison 2.0")
                .setLore("§7Seznam kratkych tutorialu", "§7se kterymi ziskas prehled", "§7na nasem Prisonu.").build(), e -> {}));

            contents.set(2, 2, ClickableItem.of(new ItemBuilder(Material.EMERALD_ORE).setName("§aDoly")
                .setLore("§7Na nasem prisonu najdes", "§7mnoho dolu, pojmenovanych po", "§7pohadkovych postavach.",
                        "§7S kazdym dolem, ziskas i", "§7specialni tag s nazvem dolu.","", "§eSeznam dolu: §b/ranks").build(), e -> {}));

            contents.set(2, 4, ClickableItem.of(new ItemBuilder(Material.LADDER).setName("§cRanky a Prestige?")
                .setLore("§7Kazdy jeden dul = jeden rank.", "§7Kazdy hrac u nas zacina", "§7s obtiznosti §eEasy§7.", "",
                        "§7Po dokonceni zakladniho Prisonu", "§7se ti odemkne prikaz §f/obtiznost", "§7Se kterym si muzes cely",
                        "§7svuj proces vyresetovat", "§7a dat si tezsi obtiznost!","", "§eZobrazeni ranku: §b/rank", "§eRankup na dalsi uroven: §b/rankup").build(), e -> {}));

            contents.set(2, 6, ClickableItem.of(new ItemBuilder(Material.IRON_SWORD).setName("§dPvP Mine")
                .setLore("§7PvP se zde take nachazi,", "§7ale ve specialni podobe dolu.", "§7Kde muzes ziskat vetsi",
                        "§7obnos penez za cenu smrti.", "", "§cOdemknuti od ranku: §fBender", "", "§eTeleport: §b/pvp").hideAllFlags().build(), e -> {}));


            contents.set(4, 2, ClickableItem.of(new ItemBuilder(Material.LOG).setName("§bOstrovy")
                .setLore("§7Kazdy hrac si muze vytvorit", "§71x vlastni ostrov o", "§7rozloze 400x400 blocku.",
                        "", "§cOdemknuti od ranku: §fD", "", "§eVytvoreni ostrova: §b/is").build(), e -> {}));



        }

        @Override
        public void update(Player player, InventoryContents contents){}
    }

    public HashMap<Player, CraftPlayer> getPlayers() {
        return players;
    }
}
