package cz.wake.craftprison.statistics.listeners;

import cz.wake.craftprison.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerStatsListener implements Listener {

    private Main main;

    public PlayerStatsListener(Main main) {
        this.main = main;
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        if (event.getPlayer().getWorld().getName().equalsIgnoreCase("world")) {
            System.out.println(main.getStatistics().getBlocksBroken(event.getPlayer()));
            main.getStatistics().setBlocksBroken(event.getPlayer(), 1);
            System.out.println(main.getStatistics().getBlocksBroken(event.getPlayer()));
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity().getPlayer();
        Player killer = event.getEntity().getKiller();

        if (victim instanceof Player && victim != null) {
            main.getStatistics().setDeaths(victim, 1);
            if (killer instanceof Player && killer != null) {
                main.getStatistics().setKills(killer, 1);
            }
        }
    }

}
