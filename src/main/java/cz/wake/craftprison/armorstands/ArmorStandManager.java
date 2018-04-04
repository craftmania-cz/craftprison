package cz.wake.craftprison.armorstands;

import cz.wake.craftprison.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class ArmorStandManager {

    public static HashSet<RankedArmorStand> armorstands = new HashSet<>();

    public static void removeArmorStands(boolean fixAll) {
        if(fixAll){
            Bukkit.getWorld("prison_spawn").getEntities().forEach(Entity::remove);
            Bukkit.getWorld("doly").getEntities().forEach(Entity::remove);
        }
        armorstands.forEach(RankedArmorStand::remove);
    }

    public void setMetadata(ArmorStand as, String type, Object rank, Main paramMain) {
        as.setMetadata(type, new FixedMetadataValue(paramMain, rank));
    }

    public static boolean isRankedArmorStand(ArmorStand as){
        for(RankedArmorStand rm : armorstands){
            if(rm.getMainArmorStand() == as){
                return true;
            }
        }
        return false;
    }

    public static void initArmorStands(){

        // Rank A
        RankedArmorStand rankA = new RankedArmorStand("A", "prison_spawn", new Location(Bukkit.getWorld("prison_spawn"), -1184.5, 48, -465.5, -158, 0));
        rankA.setColor(67, 67, 108);
        rankA.setHead("bafc75ac-236f-46d3-8eda-8ea20db4319e", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWUyNTc1ZmRlZWMxY2JjZjVjZmU3ZjZhMTkzOGRkYTM4YTZiOWFmMmE0MDVkNWYyNTQxNDk5N2I5MTY4MzEifX19");
        rankA.setHologramTexts("§8Pravym - prodej | Levym - ceny", "§7Zde prodej bloky!", "§e§lMine Tutorial (A)");
        rankA.setItemInHand(Material.WOOD_PICKAXE);
        rankA.spawn();

        // Rank B
        RankedArmorStand rankB = new RankedArmorStand("B", "prison_spawn", new Location(Bukkit.getWorld("prison_spawn"), -1105.5, 48, -401.5, -70, 0));
        rankB.setColor(176, 23, 31);
        rankB.setHead("c22ccc77-09b1-4c59-985b-fd76e056262b", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjFlNDY1ZjNiYjk4NWQwYjdjOTc1ODIzN2YzYWVjZjJlNTE4OTgwNGU4OTU1ODUzYTVkNjQyOTQ0M2VkNyJ9fX0=");
        rankB.setHologramTexts("§8Pravym - prodej | Levym - ceny", "§7Zde prodej bloky!", "§c§lMine Tutorial (B)");
        rankB.setItemInHand(Material.IRON_PICKAXE);
        rankB.spawn();

        // Rank C
        RankedArmorStand rankC = new RankedArmorStand("C", "prison_spawn", new Location(Bukkit.getWorld("prison_spawn"), -1168.5, 48, -322.5, 25, 0));
        rankC.setColor(0, 199, 140);
        rankC.setHead("f0ca1d57-6be4-4d86-b4ca-fd1288dac5bb", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjRjZmQzOTYxNTY4MjJlYWFkNzhjOWMzZmFkYjM0M2I3ZjE1MzM4MTI1NTc2ZmVmZmJlMTVkNGJlOWYifX19");
        rankC.setHologramTexts("§8Pravym - prodej | Levym - ceny", "§7Zde prodej bloky!", "§a§lMine Tutorial (C)");
        rankC.setItemInHand(Material.SEEDS);
        rankC.spawn();

        // Rank D
        RankedArmorStand rankD = new RankedArmorStand("D", "prison_spawn", new Location(Bukkit.getWorld("prison_spawn"), -1247.5, 48, -385.5, 105, 0));
        rankD.setColor(16, 78, 139);
        rankD.setHead("173dbcb5-4410-4106-9ab7-00c1a9b04892", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTBlNDFmMzg2ZWE4MTNkYWU3MWE0M2U3YTRhOTE0NTIzYzJjNzNmZjk4NjFlMjYwYWE4OGFlNzY3NzVlIn19fQ==");
        rankD.setHologramTexts("§8Pravym - prodej | Levym - ceny", "§7Zde prodej bloky!", "§9§lMine Tutorial (D)");
        rankD.setItemInHand(Material.LAPIS_ORE);
        rankD.spawn();

        // Rank Goofy
        RankedArmorStand goofy = new RankedArmorStand("Goofy", "doly", new Location(Bukkit.getWorld("doly"), -533.5, 109, 3243.5, -66, 0));
        goofy.setColor(41, 36, 33);
        goofy.setHead("9d59f402-196e-491c-8eef-b5b7c0efd782", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjVlMDQ0NDQyYmZkNTk1NmUzNTQxZTIzM2YyNTFhOGE3NTIyYmFmNjE4M2ZlNTZjZGI3NzNhMzViMWZhODYwIn19fQ==");
        goofy.setHologramTexts("§7Zde prodej bloky!", "§a§lGoofy Mine");
        goofy.setItemInHand(Material.IRON_INGOT);
        goofy.spawn();

        // Rank Bender
        RankedArmorStand bender = new RankedArmorStand("Bender", "doly", new Location(Bukkit.getWorld("doly"), -1085.5, 104, 1749.5, -100, 0));
        bender.setColor(173, 216, 230);
        bender.setHead("e5f8b552-18cf-4e19-837d-0ebfc17e7ad3", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjE1ZmUyNDdkOWM2MWE2YzEzNzU0NGRlN2U2MjIwZjg0NzU5ZTMzMzM1YjBiNTUxODMyZmExZjhhMjYyYzIzYSJ9fX0=");
        bender.setHologramTexts("§7Zde prodej bloky!", "§8§lBender Mine");
        bender.setItemInHand(Material.FLINT);
        bender.spawn();

        // Rank Magikarp
        RankedArmorStand magikarp = new RankedArmorStand("Magikarp", "doly", new Location(Bukkit.getWorld("doly"), -1474.5, 98, 638.5, 158, 0));
        magikarp.setColor(255,69,0);
        magikarp.setHead("41dac880-eb6f-422e-9dc0-f7dec0e12933", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmY1OGZiN2NiZjlmOGRjZmMzYmM5ZDYxYzdjYjViMjI5YmY0OWRiMTEwMTMzNmZmZGMyZDA4N2MwYjk0MTYyIn19fQ==");
        magikarp.setHologramTexts("§7Zde prodej bloky!", "§c§lMagikarp Mine");
        magikarp.setItemInHand(Material.RED_ROSE);
        magikarp.spawn();

        // Rank Zoidberg
        RankedArmorStand zoidberg = new RankedArmorStand("Zoidberg", "doly", new Location(Bukkit.getWorld("doly"), -1477.5, 160, 226.5, 25, 0));
        zoidberg.setColor(220,20,60);
        zoidberg.setHead("9e052b46-699a-40ed-9444-cb312ca2b1d3", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODIzOGMxMTRiMjdjYTlmZmQ2ZTc3NTRmZWM1ODJjN2UzNjk5MjgyODNiMmQ3ZmNlMTQ5ZWFhMzEyYmQyIn19fQ==");
        zoidberg.setHologramTexts("§7Zde prodej bloky!", "§4§lZoidberg Mine");
        zoidberg.setItemInHand(Material.RAW_FISH);
        zoidberg.spawn();

        armorstands.forEach(ar -> System.out.println(ar.getName()));
    }
}
