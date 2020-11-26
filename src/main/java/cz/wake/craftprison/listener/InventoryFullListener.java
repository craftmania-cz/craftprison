package cz.wake.craftprison.listener;

import cz.craftmania.craftcore.spigot.xseries.messages.Titles;
import cz.wake.craftprison.events.InventoryFullEvent;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class InventoryFullListener implements Listener {

    @EventHandler
    public void onFull(final InventoryFullEvent e) {
        final Player p = e.getPlayer();
        if (p == null || e.getItem() == null) {
            return;
        }

        // Chat message
        p.sendMessage("§cMáš plný inventář, ztrácíš bloky!");

        // Title warning
        Titles.sendTitle(p, "", "§cMáš plný inventář!");

        // Sound waring
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BASS, 10.0f, 1.0f);
    }
}
