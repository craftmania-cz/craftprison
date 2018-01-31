package cz.wake.craftprison.armorstands;

import cz.wake.craftprison.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.List;

public class ArmorStandManager {

    public static List<RankedArmorStand> armorstands = new ArrayList<>();

    public static void removeArmorStands(boolean fixAll) {
        armorstands.forEach(RankedArmorStand::remove);
        if(fixAll){
            Bukkit.getWorld("prison_spawn").getEntities().forEach(Entity::remove);
            Bukkit.getWorld("doly").getEntities().forEach(Entity::remove);
        }
    }

    public void setMetadata(ArmorStand as, String paramString, Object paramObject, Main paramMain) {
        as.setMetadata(paramString, new FixedMetadataValue(paramMain, paramObject));
    }

    public static void initArmorStands(){

        // Rank A
        RankedArmorStand rankA = new RankedArmorStand("A", "prison_spawn", new Location(Bukkit.getWorld("prison_spawn"), -1184.5, 48, -465.5, -158, 0));
        rankA.setColor(67, 67, 108);
        rankA.setHead("bafc75ac-236f-46d3-8eda-8ea20db4319e", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWUyNTc1ZmRlZWMxY2JjZjVjZmU3ZjZhMTkzOGRkYTM4YTZiOWFmMmE0MDVkNWYyNTQxNDk5N2I5MTY4MzEifX19");
        rankA.setHologramTexts("§8Pravym - prodej | Levym - ceny", "§7Zde prodej bloky!", "§e§lMine Tutorial (A)");
        rankA.setItemInHand(Material.WOOD_PICKAXE);
        rankA.spawn();
    }
}
