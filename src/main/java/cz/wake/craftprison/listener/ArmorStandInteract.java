package cz.wake.craftprison.listener;

import cz.wake.craftprison.armorstands.ArmorStandManager;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class ArmorStandInteract implements Listener {

    @EventHandler
    public void manipulate(PlayerArmorStandManipulateEvent e) {
        if(ArmorStandManager.armorstands.equals(e.getRightClicked())){
            e.setCancelled(true);
        }
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onRightClick(PlayerInteractAtEntityEvent e) {
        Player p = e.getPlayer();
        if (e.getRightClicked() instanceof ArmorStand) {
            if (e.getRightClicked().hasMetadata("MineA")) {
                p.performCommand("sellall A");
            }
        }
        e.setCancelled(true);

    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onLeftClick(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player){
            if(e.getEntity() instanceof ArmorStand){
                Player p = (Player)e.getDamager();
                if (e.getEntity().hasMetadata("MineA")) {
                    p.performCommand("prices A");
                }
                e.setCancelled(true);
            }
        }
    }
}
