package cz.wake.craftprison.utils;

import cz.craftmania.craftcore.spigot.builders.items.ItemBuilder;
import net.minecraft.server.v1_12_R1.*;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class AnvilContainer extends ContainerAnvil {

    public AnvilContainer(EntityHuman paramEntityHuman) {
        super(paramEntityHuman.inventory, paramEntityHuman.world, new BlockPosition(0, 0, 0), paramEntityHuman);
    }

    public boolean a(EntityHuman paramEntityHuman) {
        return true;
    }

    public static void openAnvil(Player paramPlayer, ItemStack item) {
        EntityPlayer localEntityPlayer = ((CraftPlayer) paramPlayer).getHandle();
        AnvilContainer localAnvilContainer = new AnvilContainer(localEntityPlayer);
        int i = localEntityPlayer.nextContainerCounter();

        ((CraftPlayer) paramPlayer).getHandle().playerConnection.sendPacket(new PacketPlayOutOpenWindow(i, "minecraft:anvil", new ChatMessage("Repairing", new Object[0]), 0));

        localEntityPlayer.activeContainer = localAnvilContainer;
        localEntityPlayer.activeContainer.windowId = i;
        localEntityPlayer.activeContainer.addSlotListener(localEntityPlayer);
        localEntityPlayer.activeContainer = localAnvilContainer;
        localEntityPlayer.activeContainer.windowId = i;
        localEntityPlayer.activeContainer.checkReachable = false;

        Inventory localInventory = localAnvilContainer.getBukkitView().getTopInventory();
        localInventory.setMaxStackSize(99);
        if (item.hasItemMeta()) {
            item = new ItemBuilder(item).setName(item.getItemMeta().getDisplayName().replace("§", "&")).build();
        }
        localInventory.setItem(0, item);
    }
}
