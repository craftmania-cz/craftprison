package cz.wake.craftprison.listener;

import cz.wake.craftprison.Main;
import net.citizensnpcs.api.event.NPCClickEvent;
import net.citizensnpcs.api.event.NPCLeftClickEvent;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.npc.NPC;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class NPCInteractListener implements Listener {

    @EventHandler
    public void onRightClickNPC(NPCRightClickEvent event) {
        NPC unknownNPC = event.getNPC();
        Main.getInstance().getNpcManager().getNpcList().forEach((interactiveNPC -> {
            if (interactiveNPC.getNpc().getName().equals(unknownNPC.getName())) {
                interactiveNPC.getRightClickAction().run(event);
            }
        }));
    }

    @EventHandler
    public void onLeftClickNPC(NPCLeftClickEvent event) {
        NPC unknownNPC = event.getNPC();
        Main.getInstance().getNpcManager().getNpcList().forEach((interactiveNPC -> {
            if (interactiveNPC.getNpc().getName().equals(unknownNPC.getName())) {
                interactiveNPC.getLeftClickAction().run(event);
            }
        }));
    }
}
