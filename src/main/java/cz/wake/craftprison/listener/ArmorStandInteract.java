package cz.wake.craftprison.listener;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.armorstands.ArmorStandManager;
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
        if (ArmorStandManager.armorstands.equals(e.getRightClicked())) {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onRightClick(PlayerInteractAtEntityEvent e) {
        Player p = e.getPlayer();
        if (e.getRightClicked() instanceof ArmorStand) {
            ArmorStand clickedAs = (ArmorStand) e.getRightClicked();
            if (ArmorStandManager.isRankedArmorStand(clickedAs)) {
                e.setCancelled(true);
                if (clickedAs.hasMetadata("rank") || clickedAs.getLocation().equals(ArmorStandManager.getArmorStandByLocation(clickedAs.getLocation()))) {
                    Object obj = getMetadata(clickedAs, "rank", Main.getInstance());
                    String rank = (String) obj;
                    p.performCommand("sellall " + rank);
                }
            } else if (ArmorStandManager.isStandartArmorStand(clickedAs)) {
                System.out.println("kek-1");
                e.setCancelled(true);
                if (clickedAs.hasMetadata("standart") || clickedAs.getLocation().equals(ArmorStandManager.getStandartArmorStandByLocation(clickedAs.getLocation()))) {
                    System.out.println("kek-2");
                    Object obj = getMetadata(clickedAs, "standart", Main.getInstance());
                    String name = (String) obj;
                    System.out.println("kek-name: " + name);
                    if(name == null) {
                        return;
                    }
                    if(name.equalsIgnoreCase("Tutorial")) {
                        System.out.println("kek-final");
                        p.sendMessage("§b§lSMRDIS");
                    }
                }
            }
        }

    }

    @EventHandler(priority = EventPriority.HIGH)
    public void onLeftClick(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            Player p = (Player) e.getDamager();
            if (e.getEntity() instanceof ArmorStand) {
                ArmorStand clickedAs = (ArmorStand) e.getEntity();
                if (ArmorStandManager.isRankedArmorStand(clickedAs)) {
                    e.setCancelled(true);
                    if (clickedAs.hasMetadata("rank") || clickedAs.getLocation().equals(ArmorStandManager.getArmorStandByLocation(clickedAs.getLocation()))) {
                        Object obj = getMetadata(clickedAs, "rank", Main.getInstance());
                        String rank = (String) obj;
                        p.performCommand("prices " + rank);
                    }
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
