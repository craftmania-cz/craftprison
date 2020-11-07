package cz.wake.craftprison.npc;

import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.npc.MemoryNPCDataStore;
import net.citizensnpcs.api.npc.NPCDataStore;
import net.citizensnpcs.api.npc.NPCRegistry;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;

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
        InteractiveNPC rank_a = new InteractiveNPC(this.registry, "Rank A", EntityType.VILLAGER);
        rank_a.setLocation(new Location(Bukkit.getWorld("spawn"), -119.5, 41, 135.5, -162, 0));
        rank_a.setHologramTexts("§a§lMine A", "§7Klikni pravým k prodeji");
        rank_a.spawn();
        rank_a.setVillagerProfession(Villager.Profession.CARTOGRAPHER);
        rank_a.setVillagerType(Villager.Type.JUNGLE);
    }

    public void destroyNPCs() {
        npcList.forEach((InteractiveNPC::remove));
    }
}
