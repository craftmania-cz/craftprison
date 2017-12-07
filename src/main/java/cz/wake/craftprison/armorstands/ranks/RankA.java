package cz.wake.craftprison.armorstands.ranks;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.armorstands.IArmorstand;
import cz.wake.craftprison.utils.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

public class RankA implements IArmorstand {

    private Location loc1 = new Location(Bukkit.getWorld("prison_spawn"), -1184.5, 48, -465.5, -158, 0);
    private ArmorStand as, as2, as3, as4;

    @Override
    public void spawn() {
        as = (ArmorStand) Bukkit.getWorld("prison_spawn").spawnEntity(loc1, EntityType.ARMOR_STAND);

        as.setGravity(false);
        as.setVisible(true);
        as.setCanPickupItems(false);
        as.setBasePlate(false);
        as.setArms(true);

        ItemStack head = ItemFactory.createHead("test", "bafc75ac-236f-46d3-8eda-8ea20db4319e", "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWUyNTc1ZmRlZWMxY2JjZjVjZmU3ZjZhMTkzOGRkYTM4YTZiOWFmMmE0MDVkNWYyNTQxNDk5N2I5MTY4MzEifX19");
        as.setHelmet(head);

        ItemStack chestplate = ItemFactory.createColouredLeather(Material.LEATHER_CHESTPLATE, 67, 67, 108);
        as.setChestplate(chestplate);

        ItemStack leggins = ItemFactory.createColouredLeather(Material.LEATHER_LEGGINGS, 67, 67, 108);
        as.setLeggings(leggins);

        ItemStack boots = ItemFactory.createColouredLeather(Material.LEATHER_BOOTS, 67, 67, 108);
        as.setBoots(boots);

        ItemStack item = new ItemStack(Material.WOOD_PICKAXE);
        as.setItemInHand(item);

        hologramSpawn();

        Main.getInstance().getArmorStandManager().setMetadata((ArmorStand) as, "MineA", "MineA", Main.getInstance());
    }

    @Override
    public void hologramSpawn() {
        loc1.add(0, 0.3, 0);

        as2 = (ArmorStand) Bukkit.getWorld("prison_spawn").spawnEntity(loc1, EntityType.ARMOR_STAND);

        as2.setGravity(false);
        as2.setCanPickupItems(false);
        as2.setBasePlate(false);
        as2.setVisible(false);
        as2.setCustomNameVisible(true);

        as2.setCustomName("§8Pravym - prodej | Levym - ceny");

        loc1.add(0, 0.3, 0);

        as3 = (ArmorStand) Bukkit.getWorld("prison_spawn").spawnEntity(loc1, EntityType.ARMOR_STAND);

        as3.setGravity(false);
        as3.setCanPickupItems(false);
        as3.setBasePlate(false);
        as3.setVisible(false);
        as3.setCustomNameVisible(true);

        as3.setCustomName("§7Zde prodej bloky!");

        loc1.add(0, 0.3, 0);

        as4 = (ArmorStand) Bukkit.getWorld("prison_spawn").spawnEntity(loc1, EntityType.ARMOR_STAND);

        as4.setGravity(false);
        as4.setCanPickupItems(false);
        as4.setBasePlate(false);
        as4.setVisible(false);
        as4.setCustomNameVisible(true);

        as4.setCustomName("§e§lMine Tutorial (A)");
    }

    @Override
    public void remove() {
        as.remove();
        as2.remove();
        as3.remove();
        as4.remove();
    }
}
