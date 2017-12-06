package cz.wake.craftprison.armorstands;

import cz.wake.craftprison.Main;
import org.bukkit.entity.ArmorStand;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;

public class ArmorStandManager {

    public static List<IArmorstand> armorstands = new ArrayList<>();

    public static void init() {
        armorstands.add(null);
    }

    public static void spawn() {
        for (IArmorstand i : armorstands) {
            i.spawn();
        }
    }

    public void setMetadata(ArmorStand as, String paramString, Object paramObject, Main paramMain) {
        as.setMetadata(paramString, new FixedMetadataValue(paramMain, paramObject));
    }
}
