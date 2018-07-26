package cz.wake.craftprison.listener;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.modules.PrisCoins;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.objects.CraftPlayer;
import cz.wake.craftprison.utils.PlayerUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
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


    @EventHandler(priority = EventPriority.HIGH)
    public void onBlockBreak(BlockBreakEvent event) { //TODO: Free duplikace (pridat kontrolu zniceni)
        Player p = event.getPlayer();
        CraftPlayer cp = pm.getCraftPlayer(p);
        cp.addMinedBlock();

        // Milniky pro hrace (global na profil)
        // 1,000 bloku = 1 PrisCoins
        if(cp.getMinedBlocks() >= 1000 && !p.hasPermission("craftprison.pickaxe.milestone.1")){
            milestonePickaxe(p, "1,000", "craftprison.pickaxe.milestone.1", 1);
        }
        // 5,000 bloku = 2 PrisCoins
        if(cp.getMinedBlocks() >= 5000 && !p.hasPermission("craftprison.pickaxe.milestone.2")){
            milestonePickaxe(p, "5,000", "craftprison.pickaxe.milestone.2", 2);
        }
        // 10,000 bloku = 4 PrisCoins
        if(cp.getMinedBlocks() >= 10000 && !p.hasPermission("craftprison.pickaxe.milestone.3")){
            milestonePickaxe(p, "10,000", "craftprison.pickaxe.milestone.3", 4);
        }
        // 20,000 bloku = 7 PrisCoins
        if(cp.getMinedBlocks() >= 20000 && !p.hasPermission("craftprison.pickaxe.milestone.4")){
            milestonePickaxe(p, "20,000", "craftprison.pickaxe.milestone.4", 7);
        }
        // 50,000 bloku = 15 PrisCoinu
        if(cp.getMinedBlocks() >= 50000 && !p.hasPermission("craftprison.pickaxe.milestone.5")){
            milestonePickaxe(p, "50,000", "craftprison.pickaxe.milestone.5", 15);
        }
        // 100,000 bloku = 25 PrisCoinu
        if(cp.getMinedBlocks() >= 100000 && !p.hasPermission("craftprison.pickaxe.milestone.6")){
            milestonePickaxe(p, "100,000", "craftprison.pickaxe.milestone.6", 25);
        }
        // 250,000 bloku = 50 PrisCoinu
        if(cp.getMinedBlocks() >= 250000 && !p.hasPermission("craftprison.pickaxe.milestone.7")){
            milestonePickaxe(p, "250,000", "craftprison.pickaxe.milestone.7", 50);
        }
        // 500,000 blocku = 100 PrisCoinu
        if(cp.getMinedBlocks() >= 500000 && !p.hasPermission("craftprison.pickaxe.milestone.8")){
            milestonePickaxe(p, "500,000", "craftprison.pickaxe.milestone.8", 100);
        }
        // 1,000,000 blocku = 1,000 PrisCoinu
        if(cp.getMinedBlocks() >= 1000000 && !p.hasPermission("craftprison.pickaxe.milestone.9")){
            milestonePickaxe(p, "1,000,000", "craftprison.pickaxe.milestone.9", 1000);
        }
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

    private void milestonePickaxe(Player p, String blocks, String permission, int prisCoins){
        PlayerUtils.addPermission(p, permission);
        PrisCoins.giveCoins(p, prisCoins);
        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
        p.sendMessage("");
        p.sendMessage("§eVykopal jsi §b" + blocks + " §ebloku a dostal jsi §f" + prisCoins + " PrisCoins§e.");
        p.sendMessage("");
    }

}
