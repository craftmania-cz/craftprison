package cz.wake.craftprison.npc;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.MemoryNPCDataStore;
import net.citizensnpcs.api.npc.NPCDataStore;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

import java.util.HashSet;

public class NPCManager {

    private NPCRegistry registry;
    private static HashSet<InteractiveNPC> npcList = new HashSet();

    public NPCManager() {
        NPCDataStore dataStore = new MemoryNPCDataStore();
        registry = CitizensAPI.createAnonymousNPCRegistry(dataStore);
    }

    public NPCRegistry getRegistry() {
        return this.registry;
    }

    public HashSet<InteractiveNPC> getNpcList() {
        return npcList;
    }

    public void initNPCs() {

        // Test NPC
        InteractiveNPC npc_test = new InteractiveNPC(this.registry, "Waked_", EntityType.PLAYER);
        npc_test.setLocation(new Location(Bukkit.getWorld("spawn"), -109.5, 45, 208.5));
        npc_test.setSkinUUID("1e314ad9-f38e-49fc-b5e2-552d1a8e4dca");
        npc_test.setHologramTexts("§bTestovací hologram", "§7Druhý řádek");
        npc_test.setRightClickAction(ClickAction.run((clickEvent) -> {
            clickEvent.getClicker().sendMessage("RIGHT");
        }));
        npc_test.setLeftClickAction(ClickAction.run((npcClickEvent -> {
            npcClickEvent.getClicker().sendMessage("LEFT");
        })));
        npc_test.spawn();
    }

    public void destroyNPCs() {
        npcList.forEach((InteractiveNPC::remove));
    }
}
