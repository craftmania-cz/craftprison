package cz.wake.craftprison.utils;

import net.minecraft.server.v1_16_R3.NBTTagCompound;
import net.minecraft.server.v1_16_R3.NBTTagList;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.material.MaterialData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ItemFactory {

    public static ItemStack create(Material material, byte data, String displayName, String... lore) {
        try {
            ItemStack itemStack = new MaterialData(material, data).toItemStack(1);
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(displayName);
            if (lore != null) {
                List<String> finalLore = new ArrayList();
                finalLore.addAll(Arrays.asList(lore));
                itemMeta.setLore(finalLore);
            }
            itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            itemMeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            itemStack.setItemMeta(itemMeta);
            return itemStack;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ItemStack create(Material material, byte data, String displayName) {
        return create(material, data, displayName, null);
    }

    public static ItemStack createHead(String name, String uuid, String textureData) {
        try {
            net.minecraft.server.v1_16_R3.ItemStack sHead = CraftItemStack.asNMSCopy(new ItemStack(Material.PLAYER_HEAD, 1));

            NBTTagCompound tag = new NBTTagCompound();
            NBTTagCompound skullOwnerTag = new NBTTagCompound();
            NBTTagCompound displayTag = new NBTTagCompound();
            NBTTagCompound propertiesTag = new NBTTagCompound();

            NBTTagList tagList = new NBTTagList();

            NBTTagCompound valueTag = new NBTTagCompound();
            valueTag.setString("Value", textureData);

            tagList.add(valueTag);

            propertiesTag.set("textures", tagList);

            skullOwnerTag.setString("Id", uuid);
            skullOwnerTag.setString("Name", name);

            skullOwnerTag.set("Properties", propertiesTag);

            displayTag.setString("Name", name);

            tag.set("SkullOwner", skullOwnerTag);

            tag.set("display", displayTag);

            sHead.setTag(tag);
            return CraftItemStack.asBukkitCopy(sHead);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ItemStack createHead(String name, String uuid, String textureData, String displayName, String... lore) {
        try {
            net.minecraft.server.v1_16_R3.ItemStack sHead = CraftItemStack.asNMSCopy(new org.bukkit.inventory.ItemStack(Material.PLAYER_HEAD, 1));

            NBTTagCompound tag = new NBTTagCompound();
            NBTTagCompound skullOwnerTag = new NBTTagCompound();
            NBTTagCompound displayTag = new NBTTagCompound();
            NBTTagCompound propertiesTag = new NBTTagCompound();

            NBTTagList tagList = new NBTTagList();

            NBTTagCompound valueTag = new NBTTagCompound();
            valueTag.setString("Value", textureData);

            tagList.add(valueTag);

            propertiesTag.set("textures", tagList);

            skullOwnerTag.setString("Id", uuid);
            skullOwnerTag.setString("Name", name);

            skullOwnerTag.set("Properties", propertiesTag);

            displayTag.setString("Name", name);

            tag.set("SkullOwner", skullOwnerTag);

            tag.set("display", displayTag);

            sHead.setTag(tag);

            ItemStack item = CraftItemStack.asBukkitCopy(sHead);

            ItemMeta itemMeta = item.getItemMeta();
            itemMeta.setDisplayName(displayName);
            if (lore != null) {
                List<String> finalLore = new ArrayList();
                for (String s : lore)
                    finalLore.add(s);
                itemMeta.setLore(finalLore);
            }
            itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            item.setItemMeta(itemMeta);

            return item;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ItemStack createColouredLeather(Material armourPart, int red, int green, int blue) {
        ItemStack itemStack = new ItemStack(armourPart);
        LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemStack.getItemMeta();
        leatherArmorMeta.setColor(Color.fromRGB(red, green, blue));
        itemStack.setItemMeta(leatherArmorMeta);
        return itemStack;
    }

    public static ItemStack addGlow(ItemStack item) {
        net.minecraft.server.v1_16_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = null;
        if (!nmsStack.hasTag()) {
            tag = new NBTTagCompound();
            nmsStack.setTag(tag);
        }
        if (tag == null) tag = nmsStack.getTag();
        NBTTagList ench = new NBTTagList();
        tag.set("ench", ench);
        nmsStack.setTag(tag);
        return CraftItemStack.asCraftMirror(nmsStack);
    }
}
