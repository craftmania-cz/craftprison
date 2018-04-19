package cz.wake.craftprison.listener;

import cz.wake.craftcore.messages.Title;
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
        p.sendMessage("§cMas plny inventar! Ztracis bloky!");

        // Title warning
        new Title("","§cMas plny inventar!", 0, 30, 5).send(p);

        // Sound waring
        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_PLING, 10.0f, 1.0f);
    }
}
