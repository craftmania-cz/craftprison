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

        // Rank A
        InteractiveNPC rank_a = new InteractiveNPC(this.registry, "Rank A", EntityType.VILLAGER);
        rank_a.setLocation(new Location(Bukkit.getWorld("spawn"), -119.5, 41, 135.5, -162, 0));
        rank_a.setHologramTexts("§a§lMine A", "§7Klikni pravým k prodeji");
        rank_a.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall info a")));
        rank_a.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall a")));
        rank_a.spawn();
        rank_a.setVillagerProfession(Villager.Profession.CARTOGRAPHER);
        rank_a.setVillagerType(Villager.Type.JUNGLE);

        // Rank B
        InteractiveNPC rank_b = new InteractiveNPC(this.registry, "Rank B", EntityType.VILLAGER);
        rank_b.setLocation(new Location(Bukkit.getWorld("spawn"), -39.5, 41, 197.5, -75, 0));
        rank_b.setHologramTexts("§a§lMine B", "§7klikni pravým k prodeji");
        rank_b.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall info b")));
        rank_b.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall b")));
        rank_b.spawn();
        rank_b.setVillagerProfession(Villager.Profession.CARTOGRAPHER);
        rank_b.setVillagerType(Villager.Type.DESERT);

        // Rank C
        InteractiveNPC rank_c = new InteractiveNPC(this.registry, "Rank C", EntityType.VILLAGER);
        rank_c.setLocation(new Location(Bukkit.getWorld("spawn"), -101.5, 41, 277.5, 14, 0));
        rank_c.setHologramTexts("§a§lMine C", "§7Klikni pravým k prodeji");
        rank_c.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall info c")));
        rank_c.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall c")));
        rank_c.spawn();
        rank_c.setVillagerProfession(Villager.Profession.CARTOGRAPHER);
        rank_c.setVillagerType(Villager.Type.SAVANNA);

        // Rank D
        InteractiveNPC rank_d = new InteractiveNPC(this.registry, "Rank D", EntityType.VILLAGER);
        rank_d.setLocation(new Location(Bukkit.getWorld("spawn"), -181.5, 41, 215.5, 103, 0));
        rank_d.setHologramTexts("§a§lMine D", "§7Klikni pravým k prodeji");
        rank_d.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall info d")));
        rank_d.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall d")));
        rank_d.spawn();
        rank_d.setVillagerProfession(Villager.Profession.CARTOGRAPHER);
        rank_d.setVillagerType(Villager.Type.DESERT);
    }

    public void destroyNPCs() {
        npcList.forEach((InteractiveNPC::remove));
    }
}
