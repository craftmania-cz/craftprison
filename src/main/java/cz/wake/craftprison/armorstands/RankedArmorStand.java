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

import java.util.ArrayList;
import java.util.Collections;

public class RankedArmorStand {

    private String name;
    private String world;
    private Location location;
    private String uuidHead;
    private String textureDataHead;
    private ArrayList<String> hologramTexts;
    private ArrayList<ArmorStand> hologramArmorStands;
    private Material itemInHand;
    private int red, green, blue;
    private ArmorStand mainArmorStand;

    public RankedArmorStand(String name, Location location) {
        this.name = name;
        this.world = location.getWorld().getName();
        this.location = location;
        this.hologramTexts = new ArrayList<>();
        this.hologramArmorStands = new ArrayList<>();
    }

    public RankedArmorStand setHead(String uuid, String texture) {
        this.uuidHead = uuid;
        this.textureDataHead = texture;
        return this;
    }

    public RankedArmorStand setHologramTexts(String... lines) {
        Collections.addAll(this.hologramTexts, lines);
        return this;
    }

    public RankedArmorStand setItemInHand(Material item) {
        this.itemInHand = item;
        return this;
    }

    public RankedArmorStand setColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
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

        // Metadata
        Main.getInstance().getArmorStandManager().setMetadata(this.mainArmorStand, "rank", this.name, Main.getInstance());

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

        // Pridani do seznamu
        ArmorStandManager.armorstands.add(this);

        // Info
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[CraftPrison] " + ChatColor.WHITE + " Armorstand " + name + " byl spawnut.");
    }

    public void remove() {
        Bukkit.getWorld(this.world).loadChunk(this.location.getChunk());
        this.hologramArmorStands.forEach(Entity::remove);
        this.mainArmorStand.remove();
        ArmorStandManager.armorstands.remove(this);
    }

    public ArmorStand getMainArmorStand() {
        return this.mainArmorStand;
    }

    public String getName() {
        return name;
    }

    private void deleteNearbyEntities() {
        for (Entity ent : this.location.getWorld().getEntities()) {
            if (ent.getLocation().distance(this.location) <= 10.0) {
                ent.remove();
            }
        }
    }

}
