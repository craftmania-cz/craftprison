package cz.wake.craftprison.listener;

import cz.craftmania.crafteconomy.events.vault.CraftEconomyPlayerPrePayEvent;
import cz.wake.craftprison.modules.PrisonManager;
import cz.wake.craftprison.objects.CraftPlayer;
import cz.wake.craftprison.utils.Logger;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerPayCorrectListener implements Listener {

    final PrisonManager pm = new PrisonManager();

    @EventHandler
    public void onPay(final CraftEconomyPlayerPrePayEvent event) {
        CraftPlayer sender = pm.getCraftPlayer(event.getSender());
        CraftPlayer reciever = pm.getCraftPlayer(event.getReciever());

        if (sender.getPrestige() != reciever.getPrestige()) {
            sender.getPlayer().sendMessage("§c§l[!] §cPříkaz byl zastaven z důvodu, že hráč nemá stejnou Prestige jako ty.");
            Logger.info("Hráč (" + sender.getPlayer().getName() + ") se pokusil odeslat peníze hráči z jiné Prestige.");
            event.setCancelled(true);
        }
    }
}
