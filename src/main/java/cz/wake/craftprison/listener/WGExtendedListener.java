package cz.wake.craftprison.listener;

import cz.wake.craftcore.events.worldguard.RegionEnterEvent;
import cz.wake.craftcore.events.worldguard.RegionLeaveEvent;
import cz.wake.craftprison.modules.PrisonManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;

public class WGExtendedListener implements Listener {

    @EventHandler
    public void onRegionEnter(RegionEnterEvent e){
        Player p = e.getPlayer();

        if(PrisonManager.getWgRegions().contains(e.getRegion().getId().toLowerCase())){
            if(p.hasPermission("craftprison.mine.fly")){
                p.setAllowFlight(true);
                p.setFlying(true);
            }
            if(p.hasPermission("craftprison.mine.nightvision")){
                p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 12000000, 5));
            }
        }
    }

    @EventHandler
    public void onRegionLeave(RegionLeaveEvent e){
        Player p = e.getPlayer();

        if(PrisonManager.getWgRegions().contains(e.getRegion().getId().toLowerCase())){
            if(p.hasPermission("craftprison.mine.fly")){
                p.setFlying(false);
                p.setAllowFlight(false);
            }
            if(p.hasPermission("craftprison.mine.nightvision")){
                p.removePotionEffect(PotionEffectType.NIGHT_VISION);
            }
        }
    }
}
