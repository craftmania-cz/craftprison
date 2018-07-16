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
            p.getInventory().addItem(PickaxeUpgrade.getFirstPickaxe(p.getName()));
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
                        PlayerUtils.sendRankUpMessage(p, nextRank);
                        CraftPlayer cp = pm.getPlayers().get(p);
                        Main.getEconomy().withdrawPlayer(p, (double)nextRank.getPrice());
                        Main.getInstance().getMySQL().rankupPlayerSQL(p, nextRank);
                        cp.setRank(nextRank);
                        PlayerUtils.randomFireworks(p.getLocation());
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

    public HashMap<Player, CraftPlayer> getPlayers() {
        return players;
    }
}
