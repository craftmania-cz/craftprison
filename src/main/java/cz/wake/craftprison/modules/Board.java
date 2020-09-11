package cz.wake.craftprison.modules;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.objects.CraftPlayer;
import cz.wake.craftprison.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.HashMap;

public class Board {

    public static HashMap<Player, Board> boards = new HashMap<>();

    private Player p;
    private CraftPlayer cp;
    private Scoreboard sb;
    private Team t1, t2, t3;

    public Board(Player p) {
        this.p = p;
        boards.put(p, this);

        PrisonManager pm = new PrisonManager();
        cp = pm.getCraftPlayer(p);

        sb = Bukkit.getScoreboardManager().getNewScoreboard();

        Objective obj = sb.registerNewObjective("prison", "dummy");

        obj.setDisplayName("§b§lPRISON");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);

        t1 = sb.registerNewTeam("t1");
        t2 = sb.registerNewTeam("t2");
        t3 = sb.registerNewTeam("t3");

        t1.addEntry("§1");
        t2.addEntry("§2");
        t3.addEntry("§3");

        t1.setPrefix("§7Rank: ");
        t2.setPrefix("§7Penize: ");
        t3.setPrefix("§7PrisCoiny: ");

        obj.getScore(" ").setScore(7);
        obj.getScore("§7Nick: §f" + p.getName()).setScore(6);
        obj.getScore("§1").setScore(5);
        obj.getScore("§2").setScore(4);
        obj.getScore("§3").setScore(3);
        obj.getScore("§r").setScore(2);
        obj.getScore("§8mc.craftmania.cz").setScore(1);

        this.update();

        p.setScoreboard(sb);
    }

    private void update() {
        t1.setSuffix("§f" + cp.getRank().getName());

        //t2.setSuffix("§f" + PlayerUtils.formatMoney(Main.getEconomy().getBalance(p)) + "§a$");

        t3.setSuffix("§f" + cp.getPrisCoins());
    }

    public static void updateAll() {
        boards.values().forEach(Board::update);
    }

    public void unregister() {
        sb.getTeams().forEach(Team::unregister);
        sb.getObjectives().forEach(Objective::unregister);

        sb = null;
    }

}
