package cz.wake.craftprison.listener;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.objects.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerStatsListener implements Listener {

    private Main main;
    private PrisonManager pm;

    public PlayerStatsListener(Main main) {
        this.main = main;
        this.pm = new PrisonManager();
    }


    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        CraftPlayer cp = pm.getCraftPlayer(event.getPlayer());
        cp.addMinedBlock();
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        CraftPlayer victim = pm.getCraftPlayer(event.getEntity().getPlayer());
        CraftPlayer killer = pm.getCraftPlayer(event.getEntity().getKiller());
        if (victim != null) {
            victim.addDeath();
            if (killer != null) {
                killer.addKill();
            }
        }
    }

}
