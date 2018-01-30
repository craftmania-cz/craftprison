package cz.wake.craftprison.armorstands;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.armorstands.ranks.RankA;
import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;

public class ArmorStandManager {

    public static List<IArmorstand> armorstands = new ArrayList<>();

    public static void init() {
        armorstands.add(new RankA());
    }

    public static void spawn() {
        armorstands.forEach(IArmorstand::spawn);
    }

    public static void despawn(boolean fixAll) {
        armorstands.forEach(IArmorstand::remove);
        if(fixAll){
            Bukkit.getWorld("prison_spawn").getEntities().forEach(Entity::remove);
            Bukkit.getWorld("doly").getEntities().forEach(Entity::remove);
        }
    }

    //todo: prevence proti despawnu armorstandu (unloadchunks?)

    public void setMetadata(ArmorStand as, String paramString, Object paramObject, Main paramMain) {
        as.setMetadata(paramString, new FixedMetadataValue(paramMain, paramObject));
    }
}
