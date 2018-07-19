package cz.wake.craftprison.armorstands;

import cz.wake.craftprison.Main;
import cz.wake.craftprison.utils.ItemFactory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.EulerAngle;

import java.util.ArrayList;
import java.util.Collections;

public class StandartArmorStand {

    private String name;
    private String world;
    private Location location;
    private String uuidHead;
    private String textureDataHead;
    private Material itemInHand;
    private int red, green, blue;
    private ArrayList<String> hologramTexts;
    private ArrayList<ArmorStand> hologramArmorStands;
    private ArmorStand mainArmorStand;

    public StandartArmorStand(String name, Location location) {
        this.name = name;
        this.world = location.getWorld().getName();
        this.location = location;
        this.hologramTexts = new ArrayList<>();
        this.hologramArmorStands = new ArrayList<>();
    }

    public StandartArmorStand setHead(String uuid, String texture) {
        this.uuidHead = uuid;
        this.textureDataHead = texture;
        return this;
    }

    public StandartArmorStand setColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        return this;
    }

    public StandartArmorStand setHologramTexts(String... lines) {
        Collections.addAll(this.hologramTexts, lines);
        return this;
    }

    public StandartArmorStand setItemInHand(Material item) {
        this.itemInHand = item;
        return this;
    }

    public StandartArmorStand setHeadPosition(EulerAngle ea) {
        this.mainArmorStand.setHeadPose(ea);
        return this;
    }

    public StandartArmorStand setBodyPosition(EulerAngle ea) {
        this.mainArmorStand.setBodyPose(ea);
        return this;
    }

    public StandartArmorStand setRightLegPosition(EulerAngle ea) {
        this.mainArmorStand.setRightLegPose(ea);
        return this;
    }

    public StandartArmorStand setLeftLegPosition(EulerAngle ea) {
        this.mainArmorStand.setLeftLegPose(ea);
        return this;
    }

    public StandartArmorStand setRightArmPosition(EulerAngle ea) {
        this.mainArmorStand.setRightArmPose(ea);
        return this;
    }

    public StandartArmorStand setLeftArmPosition(EulerAngle ea) {
        this.mainArmorStand.setLeftLegPose(ea);
        return this;
    }

    public void spawn() {

        // Nacteni chunku
        Bukkit.getWorld(this.world).loadChunk(this.location.getChunk());
        deleteNearbyEntities();

        // Priprava ArmorStandu
        this.mainArmorStand = (ArmorStand) Bukkit.getWorld(this.world).spawnEntity(this.location, EntityType.ARMOR_STAND);
        this.mainArmorStand.setGravity(false);
        this.mainArmorStand.setVisible(true);
        this.mainArmorStand.setCanPickupItems(false);
        this.mainArmorStand.setBasePlate(false);
        this.mainArmorStand.setArms(true);

        // Headka
        ItemStack head = ItemFactory.createHead(this.name, this.uuidHead, this.textureDataHead);
        this.mainArmorStand.setHelmet(head);

        // Armor sloty
        ItemStack chestplate = ItemFactory.createColouredLeather(Material.LEATHER_CHESTPLATE, this.red, this.green, this.blue);
        this.mainArmorStand.setChestplate(chestplate);
        ItemStack leggins = ItemFactory.createColouredLeather(Material.LEATHER_LEGGINGS, this.red, this.green, this.blue);
        this.mainArmorStand.setLeggings(leggins);
        ItemStack boots = ItemFactory.createColouredLeather(Material.LEATHER_BOOTS, this.red, this.green, this.blue);
        this.mainArmorStand.setBoots(boots);
        if (this.itemInHand != null) {
            ItemStack item = new ItemStack(this.itemInHand);
            this.mainArmorStand.setItemInHand(item);
        }

        // Nazvy
        this.hologramTexts.forEach(text -> {
            this.location.add(0, 0.3, 0);
            ArmorStand as = (ArmorStand) Bukkit.getWorld(this.world).spawnEntity(this.location, EntityType.ARMOR_STAND);
            as.setGravity(false);
            as.setCanPickupItems(false);
            as.setBasePlate(false);
            as.setVisible(false);
            as.setCustomNameVisible(true);
            as.setCustomName(text);
            this.hologramArmorStands.add(as);
        });

        // Metadata
        Main.getInstance().getArmorStandManager().setMetadata(this.mainArmorStand, "standart", this.name, Main.getInstance());

        // Pridani do seznamu
        ArmorStandManager.standartArmorstands.add(this);

        // Info
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[CraftPrison] " + ChatColor.WHITE + " Armorstand (standart) " + name + " byl spawnut.");
    }

    private void deleteNearbyEntities() {
        for (Entity ent : this.location.getWorld().getEntities()) {
            if (ent.getLocation().distance(this.location) <= 5.0) {
                ent.remove();
            }
        }
    }

    public void remove() {
        this.mainArmorStand.remove();
        this.hologramArmorStands.forEach(Entity::remove);
        ArmorStandManager.standartArmorstands.remove(this);
    }

    public ArmorStand getMainArmorStand() {
        return this.mainArmorStand;
    }

    public String getName() {
        return name;
    }
}
