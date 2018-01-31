package cz.wake.craftprison.listener;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.armorstands.ArmorStandManager;
import cz.wake.craftprison.armorstands.RankedArmorStand;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.Plugin;

import java.util.List;

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
            ArmorStand clickedAs = (ArmorStand) e.getRightClicked();
            if(ArmorStandManager.isRankedArmorStand(clickedAs)){
                if(clickedAs.hasMetadata("rank")){
                    Object obj = getMetadata(clickedAs,"rank", Main.getInstance());
                    String rank = (String)obj;
                    p.performCommand("sellall " + rank);
                }
                e.setCancelled(true);
            }
        }

    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGH)
    public void onLeftClick(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player){
            Player p = (Player)e.getDamager();
            if(e.getEntity() instanceof ArmorStand){
                ArmorStand clickedAs = (ArmorStand) e.getEntity();
                if(ArmorStandManager.isRankedArmorStand(clickedAs)){
                    if(clickedAs.hasMetadata("rank")){
                        Object obj = getMetadata(clickedAs,"rank", Main.getInstance());
                        String rank = (String)obj;
                        p.performCommand("prices " + rank);
                    }
                    e.setCancelled(true);
                }
            }

        }
    }

    private Object getMetadata(Metadatable object, String key, Plugin plugin) {
        List<MetadataValue> values = object.getMetadata(key);
        for (MetadataValue value : values) {
            // Plugins are singleton objects, so using == is safe here
            if (value.getOwningPlugin() == plugin) {
                return value.value();
            }
        }
        return null;
    }
}
