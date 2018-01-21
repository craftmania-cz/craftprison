package cz.wake.craftprison.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;

public class InventoryFullEvent extends Event {

    private static final HandlerList handlers;
    private String pname;
    private ItemStack wontFit;

    static {
        handlers = new HandlerList();
    }

    public InventoryFullEvent(final Player p, final ItemStack wontFit) {
        this.pname = p.getName();
        this.wontFit = wontFit;
    }

    public String getName() {
        return this.pname;
    }

    public Player getPlayer() throws NullPointerException {
        return Bukkit.getServer().getPlayer(this.pname);
    }

    public HandlerList getHandlers() {
        return InventoryFullEvent.handlers;
    }

    public static HandlerList getHandlerList() {
        return InventoryFullEvent.handlers;
    }

    public ItemStack getItem() {
        return this.wontFit;
    }
}
