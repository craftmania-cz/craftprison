package cz.wake.craftprison.hooks;

import com.vk2gpz.vkbackpack.VKBackPack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class VKBackPackHook {

    public boolean wontFit(final Player p, final ItemStack i) {
        return VKBackPack.wontFit(p, i);
    }
}
