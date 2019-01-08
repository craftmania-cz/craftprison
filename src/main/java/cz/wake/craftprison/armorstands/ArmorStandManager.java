package cz.wake.craftprison.armorstands;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import cz.wake.craftprison.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.HashSet;

public class ArmorStandManager {

    public static HashSet<RankedArmorStand> armorstands = new HashSet<>();
    public static HashSet<StandartArmorStand> standartArmorstands = new HashSet<>();

    public static void removeArmorStands(boolean fixAll) {
        if (fixAll) {
            Bukkit.getWorld("prison_spawn").getEntities().forEach(Entity::remove);
            Bukkit.getWorld("doly").getEntities().forEach(Entity::remove);
        }
        armorstands.forEach(RankedArmorStand::remove);
        standartArmorstands.forEach(StandartArmorStand::remove);
    }

    public void setMetadata(ArmorStand as, String type, Object rank, Main paramMain) {
        as.setMetadata(type, new FixedMetadataValue(paramMain, rank));
    }

    public static boolean isRankedArmorStand(ArmorStand as) {
        for (RankedArmorStand rm : armorstands) {
            if (rm.getMainArmorStand().equals(as) || rm.getName().equalsIgnoreCase(as.getName())
                    || rm.getMainArmorStand().hasMetadata("rank")) {
                return true;
            }
        }
        return false;
    }

    public static RankedArmorStand getArmorStandByLocation(Location location) {
        for (RankedArmorStand r : armorstands) {
            if (r.getMainArmorStand().getLocation().equals(location)) {
                return r;
            }
        }
        return null;
    }

    public static boolean isStandartArmorStand(ArmorStand as) {
        for (StandartArmorStand rm : standartArmorstands) {
            if (rm.getMainArmorStand().equals(as) || rm.getName().equalsIgnoreCase(as.getName())
                    || rm.getMainArmorStand().hasMetadata("standart")) {
                return true;
            }
        }
        return false;
    }

    public static StandartArmorStand getStandartArmorStandByLocation(Location location) {
        for (StandartArmorStand r : standartArmorstands) {
            if (r.getMainArmorStand().getLocation().equals(location)) {
                return r;
            }
        }
        return null;
    }

    public static void initRankedArmorStands() {

        // Rank A
        RankedArmorStand rankA = new RankedArmorStand("A", new Location(Bukkit.getWorld("prison_spawn"), -1184.5, 48, -465.5, -158, 0));
        rankA.setColor(67, 67, 108);
        rankA.setHead("bafc75ac-236f-46d3-8eda-8ea20db4319e", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWUyNTc1ZmRlZWMxY2JjZjVjZmU3ZjZhMTkzOGRkYTM4YTZiOWFmMmE0MDVkNWYyNTQxNDk5N2I5MTY4MzEifX19");
        rankA.setHologramTexts("§8Pravym - prodej | Levym - ceny", "§7Zde prodej bloky!", "§e§lMine Tutorial (A)");
        rankA.setItemInHand(new ItemStack(Material.WOOD_PICKAXE));
        rankA.spawn();

        // Rank B
        RankedArmorStand rankB = new RankedArmorStand("B", new Location(Bukkit.getWorld("prison_spawn"), -1105.5, 48, -401.5, -70, 0));
        rankB.setColor(176, 23, 31);
        rankB.setHead("c22ccc77-09b1-4c59-985b-fd76e056262b", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjFlNDY1ZjNiYjk4NWQwYjdjOTc1ODIzN2YzYWVjZjJlNTE4OTgwNGU4OTU1ODUzYTVkNjQyOTQ0M2VkNyJ9fX0=");
        rankB.setHologramTexts("§7Zde prodej bloky!", "§c§lMine Tutorial (B)");
        rankB.setItemInHand(new ItemStack(Material.IRON_PICKAXE));
        rankB.spawn();

        // Rank C
        RankedArmorStand rankC = new RankedArmorStand("C", new Location(Bukkit.getWorld("prison_spawn"), -1168.5, 48, -322.5, 25, 0));
        rankC.setColor(0, 199, 140);
        rankC.setHead("f0ca1d57-6be4-4d86-b4ca-fd1288dac5bb", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjRjZmQzOTYxNTY4MjJlYWFkNzhjOWMzZmFkYjM0M2I3ZjE1MzM4MTI1NTc2ZmVmZmJlMTVkNGJlOWYifX19");
        rankC.setHologramTexts("§8Pravym - prodej | Levym - ceny", "§7Zde prodej bloky!", "§a§lMine Tutorial (C)");
        rankC.setItemInHand(new ItemStack(Material.SEEDS));
        rankC.spawn();

        // Rank D
        RankedArmorStand rankD = new RankedArmorStand("D", new Location(Bukkit.getWorld("prison_spawn"), -1247.5, 48, -385.5, 105, 0));
        rankD.setColor(16, 78, 139);
        rankD.setHead("173dbcb5-4410-4106-9ab7-00c1a9b04892", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTBlNDFmMzg2ZWE4MTNkYWU3MWE0M2U3YTRhOTE0NTIzYzJjNzNmZjk4NjFlMjYwYWE4OGFlNzY3NzVlIn19fQ==");
        rankD.setHologramTexts("§8Pravym - prodej | Levym - ceny", "§7Zde prodej bloky!", "§9§lMine Tutorial (D)");
        rankD.setItemInHand(new ItemStack(Material.LAPIS_ORE));
        rankD.spawn();

        // Rank Goofy
        RankedArmorStand goofy = new RankedArmorStand("Goofy", new Location(Bukkit.getWorld("doly"), -533.5, 109, 3243.5, -66, 0));
        goofy.setColor(41, 36, 33);
        goofy.setHead("9d59f402-196e-491c-8eef-b5b7c0efd782", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjVlMDQ0NDQyYmZkNTk1NmUzNTQxZTIzM2YyNTFhOGE3NTIyYmFmNjE4M2ZlNTZjZGI3NzNhMzViMWZhODYwIn19fQ==");
        goofy.setHologramTexts("§7Zde prodej bloky!", "§a§lGoofy Mine");
        goofy.setItemInHand(new ItemStack(Material.IRON_INGOT));
        goofy.spawn();

        // Rank Bender
        RankedArmorStand bender = new RankedArmorStand("Bender", new Location(Bukkit.getWorld("doly"), -1085.5, 104, 1749.5, -100, 0));
        bender.setColor(173, 216, 230);
        bender.setHead("e5f8b552-18cf-4e19-837d-0ebfc17e7ad3", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjE1ZmUyNDdkOWM2MWE2YzEzNzU0NGRlN2U2MjIwZjg0NzU5ZTMzMzM1YjBiNTUxODMyZmExZjhhMjYyYzIzYSJ9fX0=");
        bender.setHologramTexts("§7Zde prodej bloky!", "§8§lBender Mine");
        bender.setItemInHand(new ItemStack(Material.FLINT));
        bender.spawn();

        // Rank Magikarp
        RankedArmorStand magikarp = new RankedArmorStand("Magikarp", new Location(Bukkit.getWorld("doly"), -1474.5, 98, 638.5, 158, 0));
        magikarp.setColor(255, 69, 0);
        magikarp.setHead("41dac880-eb6f-422e-9dc0-f7dec0e12933", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMmY1OGZiN2NiZjlmOGRjZmMzYmM5ZDYxYzdjYjViMjI5YmY0OWRiMTEwMTMzNmZmZGMyZDA4N2MwYjk0MTYyIn19fQ==");
        magikarp.setHologramTexts("§7Zde prodej bloky!", "§c§lMagikarp Mine");
        magikarp.setItemInHand(new ItemStack(Material.RED_ROSE));
        magikarp.spawn();

        // Rank Zoidberg
        RankedArmorStand zoidberg = new RankedArmorStand("Zoidberg", new Location(Bukkit.getWorld("doly"), -1477.5, 160, 226.5, 25, 0));
        zoidberg.setColor(220, 20, 60);
        zoidberg.setHead("9e052b46-699a-40ed-9444-cb312ca2b1d3", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODIzOGMxMTRiMjdjYTlmZmQ2ZTc3NTRmZWM1ODJjN2UzNjk5MjgyODNiMmQ3ZmNlMTQ5ZWFhMzEyYmQyIn19fQ==");
        zoidberg.setHologramTexts("§7Zde prodej bloky!", "§4§lZoidberg Mine");
        zoidberg.setItemInHand(new ItemStack(Material.RAW_FISH));
        zoidberg.spawn();

        // Rank Maxwell
        RankedArmorStand maxwell = new RankedArmorStand("Maxwell", new Location(Bukkit.getWorld("doly"), -244.5, 137, 1860.5, -70, 0));
        maxwell.setColor(231, 99, 34);
        maxwell.setHead("1335bd82-9c2f-471c-aa00-26c78d9a78e2", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjg1NjJjM2FhMzRmNzdmZDBiZTUxOWJmOWM5NjAzZTliZTVhYjg4N2VmODMxZDlmOGQ1YjNkMzk3Y2I3Yjg4In19fQ==");
        maxwell.setHologramTexts("§7Zde prodavej bloky!", "§c§lMaxwell Mine");
        maxwell.setItemInHand(new ItemStack(Material.BOOK_AND_QUILL));
        maxwell.spawn();

        // Rank Dr.Strange
        RankedArmorStand strange = new RankedArmorStand("Strange", new Location(Bukkit.getWorld("doly"), -236.5, 151, 2223.5, 75, 0));
        strange.setColor(17, 43, 111);
        strange.setHead("524f8ddd-5019-4202-9868-27011e05cfbd", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTY2MTlmYjcwYTdmOGJhYWJiMzQ0ZDE3MjgzNWI2OTdmM2VlMTQ3YmI3MTA5OWY1YWJkMDE2NTI3YjIifX19");
        strange.setHologramTexts("§7Zde prodavej bloky!", "§9§lDr.Strange Mine");
        strange.spawn();

        // Rank Shrek
        RankedArmorStand shrek = new RankedArmorStand("Shrek", new Location(Bukkit.getWorld("doly"), -220.5, 120, 1583.5, 22, 0));
        shrek.setColor(179, 238, 58);
        shrek.setHead("b2ded8ad-ec80-4341-adc4-ace71123a815", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDg2OGRkNTBiY2I3M2M0N2M0YWFlZTc1YzdlYjNjNzA5Nzg4NWQ0YTlkZDM0YTU3YzhjYTQ4ZGUzYjc2NTk4YSJ9fX0=");
        shrek.setHologramTexts("§7Zde prodavej bloky!", "§a§lShrek Mine");
        shrek.spawn();

        // Rank Fiona
        RankedArmorStand fiona = new RankedArmorStand("Fiona", new Location(Bukkit.getWorld("doly"), -219.5, 116, 1300.5, 80, 0));
        fiona.setHead("477710f7-98af-4759-8fa7-a16eeafc2454", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmI1NTRjNjgwYzc4ZTM1ZTJkMjc1MmYzY2UwMTFlYzg1NTQxZDQxNTY1MGNlNjJlZGQxYTFkMWRjMzVlNGM1In19fQ==");
        fiona.setColor(179, 238, 58);
        fiona.setHologramTexts("§7Zde prodavej bloky!", "§a§lFiona Mine", "§6§l?");
        fiona.spawn();

        // Rank Deadpool
        RankedArmorStand deadpool = new RankedArmorStand("Deadpool", new Location(Bukkit.getWorld("doly"), -262.5, 117, 1010.5, -105, 0));
        deadpool.setHead("a2abdd8a-6c9b-47ca-8bb7-b5dab6c681ee", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTk5NTk5NTIxNGEzN2FiYjE3NDhjODFiNmE3Mzc3ZDEyMzUyNDhhODJhNTY4ZWY1N2IyNzUzNGFlZTkyZTdkMyJ9fX0=");
        deadpool.setColor(205, 85, 85);
        deadpool.setHologramTexts("§7Zde prodavej bloky!", "§c§lDeadpool Mine");
        deadpool.spawn();

        // Rank Gandalf
        RankedArmorStand gandalf = new RankedArmorStand("Gandalf", new Location(Bukkit.getWorld("doly"), -230, 114, 634.5, -160, 0));
        gandalf.setHead("68d3e81a-11ff-46e9-a1a1-378e59b8db6c", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGVmMmM2ZDI4NWUwNTBmYjcyYTU2ODk5MjU4YTFkMTMzZjJhYzI3ODcwNDNlYWJiNDY5ZDU2ZTkzZTBkZDU1In19fQ==");
        gandalf.setColor(255, 240, 255);
        gandalf.setHologramTexts("§7Zde prodavej bloky!", "§f§lGandalf Mine");
        gandalf.spawn();

        // Rank Astronaut
        RankedArmorStand astronaut = new RankedArmorStand("Astronaut", new Location(Bukkit.getWorld("doly"), -207.5, 110, 290.5, -100, 0));
        astronaut.setHead("89534842-85d7-44a2-a2f8-0650e455ab58", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWNmOGZiZDc2NTg2OTIwYzUyNzM1MTk5Mjc4NjJmZGMxMTE3MDVhMTg1MWQ0ZDFhYWM0NTBiY2ZkMmIzYSJ9fX0=");
        astronaut.setColor(248, 248, 255);
        astronaut.setHologramTexts("§7Zde prodavej bloky!", "§f§lAstronaut Mine");
        astronaut.spawn();

        // Rank Mummy
        RankedArmorStand mummy = new RankedArmorStand("Mummy", new Location(Bukkit.getWorld("doly"), -191.5, 108, 20.5, 35, 0));
        mummy.setHead("8f7c0c5b-720f-4944-8481-b0f7931f303f", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U5MWU5NTgyMmZlOThjYzVhNTY1OGU4MjRiMWI4Y2YxNGQ0ZGU5MmYwZTFhZjI0ODE1MzcyNDM1YzllYWI2In19fQ==");
        mummy.setColor(205, 200, 177);
        mummy.setHologramTexts("§7Zde prodavej bloky!", "§e§lMummy Mine");
        mummy.spawn();

        // Rank Crash
        RankedArmorStand crash = new RankedArmorStand("Crash", new Location(Bukkit.getWorld("doly"), -297.5, 114, -385.5, -100, 0));
        crash.setHead("4d770916-c2cd-47ac-962f-afc86a505a18", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTdiMjExZjNkZGJlYWZhNWQzOGU1N2I0MWQzZTI3ZDQ4YmI2MWRmMzI3Mjc1MDcwYzY3ZGZiNDRjNWMzNWRmIn19fQ==");
        crash.setColor(255, 185, 15);
        crash.setHologramTexts("§7Zde prodavej bloky!", "§6§lCrash Bandicoot Mine");
        crash.spawn();

        // Rank Obelix
        RankedArmorStand obelix = new RankedArmorStand("Obelix", new Location(Bukkit.getWorld("doly"), 262.5, 115, -393.5, 80, 0));
        obelix.setHead("8f2f43a8-2c2c-4a65-81a9-f7b5a49d876f", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWRjMTQ0M2QyYmI1MzU2YjliYjFkZjkyM2U2YTM5ZjJmNDQ2ZmU4OGU5NmQ0OGJlNTlhMGQ4ZDkxMzRjOWIyOSJ9fX0=");
        obelix.setColor(135, 206, 255);
        obelix.setHologramTexts("§7Zde prodavej bloky!", "§3§lObelix Mine");
        obelix.spawn();

        // Rank Elsa
        RankedArmorStand elsa = new RankedArmorStand("Elsa", new Location(Bukkit.getWorld("doly"), 237.5, 107, -703.5, -12, 0));
        elsa.setHead("6abe277f-30ea-42e5-a591-8bbb92bbd569", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDgyOWVmMTlmMWY5ZThiZjY4MjQ0ZGNlZjk0Njc1ZDBmMjExMTgyNzA2YTk3YjVhZGMyY2U1ZGQ5OSJ9fX0=");
        elsa.setColor(99, 184, 255);
        elsa.setHologramTexts("§7Zde prodavej bloky!", "§b§lElsa Mine");
        elsa.spawn();

        // Rank Angel
        RankedArmorStand angel = new RankedArmorStand("Angel", new Location(Bukkit.getWorld("doly"), 273.5, 112, -1029.5, 20, 0));
        angel.setHead("b0e9b61c-2f33-4e40-b31e-80980dbe86f3", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTVjOGEyYzA3ODNlOTY1NzE4NmFkYmFkYTk5OTBhY2U1MGU0ZGQyYWY1MWIzYzJkY2U1Mzg4NGVmZjY2MyJ9fX0=");
        angel.setColor(255, 255, 255);
        angel.setHologramTexts("§7Zde prodavej bloky!", "§f§lAngel Mine");
        angel.spawn();

        // Rank Carl The Cupcake
        RankedArmorStand carl = new RankedArmorStand("Carl", new Location(Bukkit.getWorld("doly"), 616.5, 121, -913.5, -60, 0));
        carl.setHead("af9eb78c-af8d-40ca-b8ae-06f4fb02a080", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjc1ZmM5NmY5NjdkMzU1MDIwMGI3ZjgyZmEyYzM0NTRjZGFmZGFjNzZjNTVlNTE1ZWUyYWFiNDI3ZjYzIn19fQ==");
        carl.setColor(238, 0, 238);
        carl.setHologramTexts("§7Zde prodavej bloky!", "§d§lCarl The Cupcake Mine");
        carl.spawn();

        // Rank Thanos
        RankedArmorStand thanos = new RankedArmorStand("Thanos", new Location(Bukkit.getWorld("doly"), 994.5, 105, -920.5, -100, 0));
        thanos.setHead("7055d982-299a-4315-9f01-f4d830506fe5", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWYzNDQ2ZTgzMGQzOTUzN2UyYjIyM2VhZmYyZjFjZWQ5NTc4OTY0MTEzYzQ3ODI0YzgwZDJjZTU5YmYyMTI4In19fQ==");
        thanos.setColor(25, 25, 112);
        thanos.setHologramTexts("§7Zde prodavej bloky!", "§9§lThanos Mine");
        thanos.spawn();

        // Rank Groot
        RankedArmorStand groot = new RankedArmorStand("Groot", new Location(Bukkit.getWorld("doly"), 1359.5, 111, -909, 111, 0));
        groot.setHead("c7a7be8d-950a-43dc-9c53-ab9f5d13d4a1", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTZlODVlMzJiNzVlOTcyZTEwYjI1NmQ1NDk2NGUxOWYxYThiMzk0MDk1YWQ5ZjMzMWI4ODg0MDlkZTIifX19");
        groot.setColor(139, 90, 0);
        groot.setHologramTexts("§7Zde prodavej bloky!", "§6§lGroot Mine");
        groot.spawn();

        // Rank Jones
        RankedArmorStand jones = new RankedArmorStand("Jones", new Location(Bukkit.getWorld("doly"), 1026.5, 102, -1292.5, 155, 0));
        jones.setHead("c4bbf2cd-1feb-42ae-b87d-c1032ff475ad", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTJiMmI2NzBkYTFlMWRiNDZkMmE5YjMyYTFmZTZjNzhiZDdlYjEzNTA3ZGQwYjg5OGYyZTdkOTFiZThmMTFkIn19fQ==");
        jones.setColor(139, 90, 0);
        jones.setHologramTexts("§7Zde prodavej bloky!", "§6§lIndian Jones Mine");
        jones.spawn();

        // Rank Captain Toad
        RankedArmorStand captain = new RankedArmorStand("Captain", new Location(Bukkit.getWorld("doly"), 1348.5, 108, -1484.5, -105, 0));
        captain.setHead("4e7d94b5-f604-4aa6-982d-afc74a90385b", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWYzYzE0ZDJjZjE2NDA1ZGFmODMyOTJlYTRlOThlNzUxOTNiNjQyNTc2MjM0MTMxMmZhYTg4OTMzNTk5ZTMwIn19fQ==");
        captain.setColor(255, 48, 48);
        captain.setHologramTexts("§7Zde prodavej bloky!", "§c§lCaptain Toad Mine");
        captain.spawn();

        // Rank Optimus Prime
        RankedArmorStand optimus = new RankedArmorStand("Optimus", new Location(Bukkit.getWorld("doly"), 1735.5, 111, -1204.5, -9, 0));
        optimus.setHead("29591afa-76e4-4078-8845-102ba227f9b1", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzlkNTIzNjcyODNmOWZhOGJkMGI4MTBmN2RmOTYzODRiNDIzY2Q4ZDZiZmFhMzBiNmEwNWQ5MzFkZjNjZDkifX19");
        optimus.setColor(0, 0, 205);
        optimus.setHologramTexts("§7Zde prodavej bloky!", "§2§lOptimus Prime Mine");
        optimus.spawn();

        // Rank Pink Panther
        RankedArmorStand panther = new RankedArmorStand("Panther", new Location(Bukkit.getWorld("doly"), 2003.5, 109, -1130.5, 166, 0));
        panther.setHead("9186d07a-a8ef-4c8f-94a2-694363f10d6d", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2Q5MWQ5ZThjMDk1NmU0M2FmZmFmMWU1NmQ5NTUzNzU1NjQ0Y2ZmMDVlM2FlZDhiYjU4NjhhOTBhYjNjYTUifX19");
        panther.setColor(255, 130, 171);
        panther.setHologramTexts("§7Zde prodavej bloky!", "§d§lPink Panther Mine");
        panther.spawn();

        // Rank Doctor Who
        RankedArmorStand who = new RankedArmorStand("Doctor", new Location(Bukkit.getWorld("doly"), 2001.5, 123, -743.5, 142, 0));
        who.setHead("70901fe2-90b9-4f6a-a462-44b19199425b", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTkxZTg2YzdlNDM4YjhmMjUyYzlkYzFkNDIzYjliMmZiZjc3ZmRjY2JiMmU4YzRiZTMzMzQwYmNhYWIxZiJ9fX0=");
        who.setColor(46, 139, 87);
        who.setHologramTexts("§7Zde prodavej bloky!", "§2§lDoctor Who Mine");
        who.spawn();

        // Rank Kratos
        RankedArmorStand kratos = new RankedArmorStand("Kratos", new Location(Bukkit.getWorld("doly"), 1568.5, 112, -746.5, -17, 0));
        kratos.setHead("17ffb09e-8d4b-42a9-96ab-54c9bc51e8f5", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDIwODczNDIyYTFhNjhkNGRjZjAzOGJhZjY2MDAzMTUyY2UzNTgzNzBhMzM2YjdlMTY2OGY3NDg5YTcxMjVkZiJ9fX0=");
        kratos.setColor(139, 69, 0);
        ItemStack leviathan = new ItemBuilder(Material.IRON_AXE).setGlowing().build();
        kratos.setItemInHand(leviathan);
        kratos.setHologramTexts("§7Zde prodavej bloky!", "§c§lKratos Mine");
        kratos.spawn();

        // Rank Link
        RankedArmorStand link = new RankedArmorStand("Link", new Location(Bukkit.getWorld("doly"), 1092.5, 120, -686.5, 77, 0));
        link.setHead("8ab3681c-a8e1-4293-9d02-eb6bde81f64c", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTI0ZWYyNjBiYTJlMDhhZWM0ODA3M2JkZDg5YTJhYjdjNGJlN2E0Y2YwY2ZkYTMzNTExMjU4NWNlYWMwODJlYiJ9fX0=");
        link.setColor(84, 139, 84);
        link.setHologramTexts("§7Zde prodavej bloky!", "§a§lLink Mine");
        link.spawn();

        // Rank Kenny
        RankedArmorStand kenny = new RankedArmorStand("Kenny", new Location(Bukkit.getWorld("doly"), 369.5, 111, -1418.5, -108, 0));
        kenny.setHead("ecc58244-d73c-439f-90b7-f53a051e5ce6", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTJkZWJmNDdkN2IxOWQzODc2OWU4MDhjOTJiZDNkNDdiMjY2MDM2MjM1NTEwOTA1ODRjYTUxMDcwMjc4NTllIn19fQ==");
        kenny.setColor(255, 128, 0);
        kenny.setHologramTexts("§7Zde prodavej bloky!", "§6§lKenny Mine");
        kenny.spawn();

        // Rank Wither
        RankedArmorStand wither = new RankedArmorStand("Wither", new Location(Bukkit.getWorld("doly"), 1189.5, 110, -2333.5, 79, 0));
        wither.setHead("94237405-9567-48ce-ae0d-30a744f47ddc", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjhjMDE2NWU5YjJkYmQ3OGRhYzkxMjc3ZTk3ZDlhMDI2NDhmMzA1OWUxMjZhNTk0MWE4NGQwNTQyOWNlIn19fQ==");
        wither.setColor(94, 94, 94);
        wither.setHologramTexts("§7Zde prodavej bloky!", "§8§lWither Mine");
        wither.spawn(); // xD

        // Rank Eggman
        RankedArmorStand eggman = new RankedArmorStand("Eggman", new Location(Bukkit.getWorld("doly"), 1153.5, 128, -2737.5, -22, 0));
        eggman.setHead("242c895b-79be-4e1e-a08b-ff542cd2bf48", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmZjYzhjNTY1NjJhNzE4NTIwMDU2ZDNiYzRhZTEzYTliM2MxN2JiY2ViOGFiNTc2ZTc0YWZhYWU3MmZkMTIwOSJ9fX0=");
        eggman.setColor(113, 113, 198);
        eggman.setHologramTexts("§7Zde prodavej bloky!", "§9§lEggman Mine");
        eggman.spawn();

        // Rank Doomfist
        RankedArmorStand doomfist = new RankedArmorStand("Doomfist", new Location(Bukkit.getWorld("doly"), 753.5, 111, -2692.5, -180, 0));
        doomfist.setHead("7a32e11b-3e93-495d-aa8d-0aefffdcff22", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTJmZWNlNGZmNWI3OTRjMDA0YTM1NTA1NGEwMzNlNzFiNjI1NTQwYWQyYTQ2YmJkM2M4NGQ5NzFhM2E3MTI0MiJ9fX0=");
        doomfist.setColor(139, 90, 44);
        doomfist.setHologramTexts("§7Zde prodavej bloky!", "§6§lDoomfist Mine");
        doomfist.spawn();

        // Rank Orc
        RankedArmorStand orc = new RankedArmorStand("Orc", new Location(Bukkit.getWorld("doly"), 280.5, 111, -2739.5, -89, 0));
        orc.setHead("7f1b7808-ecdc-4ef0-b8a9-09849c5f9380", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzY2MGU4ZmM5N2NiYTU2NjE2ZTllMTQ0NTRmNjhiYWNkMzJkYzQ1MWZiOWIyMmYzZmJmNzc3ZDc3NGVkZDkwMCJ9fX0=");
        orc.setColor(48, 128, 20);
        orc.setHologramTexts("§7Zde prodavej bloky!", "§a§lOrc Mine");
        orc.spawn();

        // Rank Stormtrooper
        RankedArmorStand trooper = new RankedArmorStand("Stormtrooper", new Location(Bukkit.getWorld("doly"), -264.5, 88, -2818.5, 168, 0));
        trooper.setHead("25ab49e9-3692-4753-b64f-b4ffce60df81", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzYzN2Y4ZGNhNjlhNjRiZWYxZTZjYThmN2ZlM2ZhYmRiNWI4ZTFhZjMzNzJhZWViY2Y3MGM2ZmNiYmY5In19fQ==");
        trooper.setColor(248, 248, 255);
        trooper.setHologramTexts("§7Zde prodavej bloky!", "§c§lStormtrooper Mine");
        trooper.spawn();

        // Rank Dwarf
        RankedArmorStand dwarf = new RankedArmorStand("Dwarf", new Location(Bukkit.getWorld("doly"), -244.5, 55, -3464.5, -90, 0));
        dwarf.setHead("eb88fea2-01a2-4f01-939d-699ef6fc7e98", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjA0ODFlNTJkYWYwYjUzNjM0NjljMGYyY2M5YzhiYTIyYzZiYjgyNWUxMjYxNzhlOTkyNGI0Mjg0OTk3NTYifX19");
        dwarf.setColor(13, 96, 3);
        dwarf.setHologramTexts("§7Zde prodavej bloky!", "§2§lDwarf Mine");
        dwarf.spawn();

        // Rank Prismariner
        RankedArmorStand prismariner = new RankedArmorStand("Prismariner", new Location(Bukkit.getWorld("doly"), -276.5, 113, -3977.5, -11, 0));
        prismariner.setHead("d5dd68ba-a997-4109-a54a-11ce224b425c", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWFlY2NjODE0MTUzZWM3ZWZmMjY2Nzc2YzU2MjNlZDNjMGUwNGVkNTMyNTQ0MjE5ZmU4ZTA4MTkwYjIxNTk2ZSJ9fX0=");
        prismariner.setColor(38, 147, 128);
        prismariner.setHologramTexts("§7Zde prodavej bloky!", "§a§lPrismariner Mine");
        prismariner.spawn();

        // Rank Mr.Pig
        RankedArmorStand mrpig = new RankedArmorStand("MrPig", new Location(Bukkit.getWorld("doly"), -669.5, 116, -3930, -155, 0));
        mrpig.setHead("5afbc945-142e-4d97-a8e2-83a7dd87291b", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTdmNDg2ODYyNWQ1NjIxYzRhYTNhOTRjYjRlOTlhNmI2MmE5NjhjZDczMGE0ZjJmMTQ3OTM0ZWNmN2UyNjViIn19fQ==");
        mrpig.setColor(249, 160, 160);
        mrpig.setHologramTexts("§7Zde prodavej bloky!", "§d§lMr.Pig Mine");
        mrpig.spawn();

        // Rank Wizard
        RankedArmorStand wizard = new RankedArmorStand("Wizard", new Location(Bukkit.getWorld("doly"), -654.5, 122, -4441.5, -127, 0));
        wizard.setHead("b862f3c8-774b-4e91-96f3-22e833ab742e", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjE2NjI0ZDIwZjc1MGE3NWYxOGIxZTM3YWE2MDJiOTIwMjcwMTBkNjYxZTAzMTRkNDJiY2YyZDVhYzg4YmRmOCJ9fX0=");
        wizard.setColor(153, 132, 80);
        wizard.setHologramTexts("§7Zde prodavej bloky!", "§a§lWizzard Mine");
        wizard.spawn();

        // Rank Cleopatra
        RankedArmorStand cleopatra = new RankedArmorStand("Cleopatra", new Location(Bukkit.getWorld("doly"), -312.5, 110, -4176.5, -160, 0));
        cleopatra.setHead("33bede2d-2686-4b4d-b8c3-86c14b629e56", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOWUxYjZhNDAxMjA2MDg0MjEyNjMyYWYxYzU1YTA4Y2QwMzY5NjU1OGI1YzI3Y2RhNDEzMjU5YTczZTk5NCJ9fX0=");
        cleopatra.setColor(238, 230, 133);
        cleopatra.setHologramTexts("§7Zde prodavej bloky!", "§e§lCleopatra Mine");
        cleopatra.spawn();

        // Rank Indian
        RankedArmorStand indian = new RankedArmorStand("Indian", new Location(Bukkit.getWorld("doly"), -835.5, 112, -4188.5, -62, 0));
        indian.setHead("b90754fe-f7a0-45d4-a566-9af322b1575c", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZmU4ZmMyMmViOGE5OTRmNjY5ZmI2NGZmOGM1YmIxNTM4NzRmNDcxYTE1OWMzNGY4OTE2YzdhZGVhOTk4ZmYifX19");
        indian.setColor(238, 238, 224);
        indian.setHologramTexts("§7Zde prodavej bloky!", "§f§lIndian Mine");
        indian.setItemInHand(new ItemStack(Material.STICK));
        indian.spawn();
    }

    public static void initStandartArmorStand() {

        // Tutorial
        StandartArmorStand tutorial = new StandartArmorStand("Tutorial", new Location(Bukkit.getWorld("prison_spawn"), -1181.5, 52, -388.5, -135, 0));
        tutorial.setHead("bea9ae4a-9e16-4ed1-b8ac-bec02556b473", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGJlMmJhZjQwZmQ4NWViNTczZmU1YjJlNWI2Yzg4MTdjZjUwZjg4M2Q5NTc2OTQxNTgwN2FiMDcyODhhNDdjZCJ9fX0=");
        tutorial.setColor(61, 89, 171);
        tutorial.setHologramTexts("§9§lTutorial");
        tutorial.spawn();

        // Pvp
        StandartArmorStand pvp = new StandartArmorStand("PVP", new Location(Bukkit.getWorld("doly"), -1078.5, 118, 1378, -108, 0));
        pvp.setHead("c781ca84-ee4a-41f9-9fbe-40b8239473b8", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzk5ZDE1OGI2ODVlNzJlMjQ1NzJjOWU3YTA2YWE1NmY0Yzk4ZWNmMzIyNDUzYzVlYmQxMzAxY2QyZDQ2ZDk0YSJ9fX0=");
        pvp.setColor(0, 201, 87);
        pvp.setHologramTexts("§7Prodej obsidian bloku", "§a§lPvP Mine");
        pvp.spawn();
    }
}
