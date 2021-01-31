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
        rank_a.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine a")));
        rank_a.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall a")));
        rank_a.spawn();
        rank_a.setVillagerProfession(Villager.Profession.CARTOGRAPHER);
        rank_a.setVillagerType(Villager.Type.JUNGLE);

        // Rank B
        InteractiveNPC rank_b = new InteractiveNPC(this.registry, "Rank B", EntityType.VILLAGER);
        rank_b.setLocation(new Location(Bukkit.getWorld("spawn"), -39.5, 41, 197.5, -75, 0));
        rank_b.setHologramTexts("§a§lMine B", "§7klikni pravým k prodeji");
        rank_b.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine b")));
        rank_b.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall b")));
        rank_b.spawn();
        rank_b.setVillagerProfession(Villager.Profession.CARTOGRAPHER);
        rank_b.setVillagerType(Villager.Type.DESERT);

        // Rank C
        InteractiveNPC rank_c = new InteractiveNPC(this.registry, "Rank C", EntityType.VILLAGER);
        rank_c.setLocation(new Location(Bukkit.getWorld("spawn"), -101.5, 41, 277.5, 14, 0));
        rank_c.setHologramTexts("§a§lMine C", "§7Klikni pravým k prodeji");
        rank_c.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine c")));
        rank_c.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall c")));
        rank_c.spawn();
        rank_c.setVillagerProfession(Villager.Profession.CARTOGRAPHER);
        rank_c.setVillagerType(Villager.Type.SAVANNA);

        // Rank D
        InteractiveNPC rank_d = new InteractiveNPC(this.registry, "Rank D", EntityType.VILLAGER);
        rank_d.setLocation(new Location(Bukkit.getWorld("spawn"), -181.5, 41, 215.5, 103, 0));
        rank_d.setHologramTexts("§a§lMine D", "§7Klikni pravým k prodeji");
        rank_d.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine d")));
        rank_d.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall d")));
        rank_d.spawn();
        rank_d.setVillagerProfession(Villager.Profession.CARTOGRAPHER);
        rank_d.setVillagerType(Villager.Type.DESERT);

        // Rank E
        InteractiveNPC rank_e = new InteractiveNPC(this.registry, "Rank E", EntityType.VILLAGER);
        rank_e.setLocation(new Location(Bukkit.getWorld("mines"), 69.5, 107, -130, 17, 0));
        rank_e.setHologramTexts("§e§lMine E", "§7Klikni pravým k prodeji");
        rank_e.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine e")));
        rank_e.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall e")));
        rank_e.spawn();
        rank_e.setVillagerProfession(Villager.Profession.ARMORER);
        rank_e.setVillagerType(Villager.Type.PLAINS);

        // Rank F
        InteractiveNPC rank_f = new InteractiveNPC(this.registry, "Rank F", EntityType.VILLAGER);
        rank_f.setLocation(new Location(Bukkit.getWorld("mines"), -148.5, 105, -33.5, -90, 0));
        rank_f.setHologramTexts("§e§lMine F", "§7Klikni pravým k prodeji");
        rank_f.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine f")));
        rank_f.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall f")));
        rank_f.spawn();
        rank_f.setVillagerProfession(Villager.Profession.CLERIC);
        rank_f.setVillagerType(Villager.Type.PLAINS);

        // Rank G
        InteractiveNPC rank_g = new InteractiveNPC(this.registry, "Rank G", EntityType.VILLAGER);
        rank_g.setLocation(new Location(Bukkit.getWorld("mines"), -185.5, 113, 263.5, 11, 0));
        rank_g.setHologramTexts("§e§lMine G", "§7Klikni pravým k prodeji");
        rank_g.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine g")));
        rank_g.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall g")));
        rank_g.spawn();
        rank_g.setVillagerProfession(Villager.Profession.CARTOGRAPHER);
        rank_g.setVillagerType(Villager.Type.SNOW);

        // Rank H
        InteractiveNPC rank_h = new InteractiveNPC(this.registry, "Rank H", EntityType.VILLAGER);
        rank_h.setLocation(new Location(Bukkit.getWorld("mines"), -96.5, 118, 627.5, 0, 0));
        rank_h.setHologramTexts("§e§lMine H", "§7Klikni pravým k prodeji");
        rank_h.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine h")));
        rank_h.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall h")));
        rank_h.spawn();
        rank_h.setVillagerProfession(Villager.Profession.ARMORER);
        rank_h.setVillagerType(Villager.Type.TAIGA);

        // Rank I
        InteractiveNPC rank_i = new InteractiveNPC(this.registry, "Rank I", EntityType.VILLAGER);
        rank_i.setLocation(new Location(Bukkit.getWorld("mines"), 20.5, 133, 1088.5, 39, 0));
        rank_i.setHologramTexts("§e§lMine I", "§7Klikni pravým k prodeji");
        rank_i.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine i")));
        rank_i.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall i")));
        rank_i.spawn();
        rank_i.setVillagerProfession(Villager.Profession.CARTOGRAPHER);
        rank_i.setVillagerType(Villager.Type.TAIGA);

        // Rank J
        InteractiveNPC rank_j = new InteractiveNPC(this.registry, "Rank J", EntityType.VILLAGER);
        rank_j.setLocation(new Location(Bukkit.getWorld("mines"), 100.5, 99, 1675.5, -73, 0));
        rank_j.setHologramTexts("§e§lMine J", "§7Klikni pravým k prodeji");
        rank_j.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine j")));
        rank_j.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall j")));
        rank_j.spawn();
        rank_j.setVillagerProfession(Villager.Profession.NITWIT);
        rank_j.setVillagerType(Villager.Type.TAIGA);

        // Rank K
        InteractiveNPC rank_k = new InteractiveNPC(this.registry, "Rank K", EntityType.VILLAGER);
        rank_k.setLocation(new Location(Bukkit.getWorld("mines"), -764.5, 77, 12.5, -105, 0));
        rank_k.setHologramTexts("§b§lMine K", "§7Klikni pravým k prodeji");
        rank_k.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine k")));
        rank_k.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall k")));
        rank_k.spawn();
        rank_k.setVillagerProfession(Villager.Profession.LIBRARIAN);
        rank_k.setVillagerType(Villager.Type.SNOW);

        // Rank L
        InteractiveNPC rank_l = new InteractiveNPC(this.registry, "Rank L", EntityType.VILLAGER);
        rank_l.setLocation(new Location(Bukkit.getWorld("mines"), -1036.5, 133, 165.5, -105, 0));
        rank_l.setHologramTexts("§b§lMine L", "§7Klikni pravým k prodeji");
        rank_l.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine l")));
        rank_l.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall l")));
        rank_l.spawn();
        rank_l.setVillagerProfession(Villager.Profession.LIBRARIAN);
        rank_l.setVillagerType(Villager.Type.PLAINS);

        // Rank M
        InteractiveNPC rank_m = new InteractiveNPC(this.registry, "Rank M", EntityType.VILLAGER);
        rank_m.setLocation(new Location(Bukkit.getWorld("mines"), -1419.5, 110, 287.5, -69, 0));
        rank_m.setHologramTexts("§6§lMine M", "§7Klikni pravým k prodeji");
        rank_m.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine m")));
        rank_m.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall m")));
        rank_m.spawn();
        rank_m.setVillagerProfession(Villager.Profession.MASON);
        rank_m.setVillagerType(Villager.Type.TAIGA);

        // Rank N
        InteractiveNPC rank_n = new InteractiveNPC(this.registry, "Rank N", EntityType.VILLAGER);
        rank_n.setLocation(new Location(Bukkit.getWorld("mines"), -1884.5, 113, 478.5, 156, 0));
        rank_n.setHologramTexts("§6§lMine N", "§7Klikni pravým k prodeji");
        rank_n.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine n")));
        rank_n.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall n")));
        rank_n.spawn();
        rank_n.setVillagerProfession(Villager.Profession.CLERIC);
        rank_n.setVillagerType(Villager.Type.PLAINS);

        // Rank O
        InteractiveNPC rank_o = new InteractiveNPC(this.registry, "Rank O", EntityType.DROWNED);
        rank_o.setLocation(new Location(Bukkit.getWorld("mines"), -2139.5, 114, 542.5, -7, 0));
        rank_o.setHologramTexts("§6§lMine O", "§7Klikni pravým k prodeji");
        rank_o.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine o")));
        rank_o.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall o")));
        rank_o.spawn();

        // Rank P
        InteractiveNPC rank_p = new InteractiveNPC(this.registry, "Rank P", EntityType.VILLAGER);
        rank_p.setLocation(new Location(Bukkit.getWorld("mines"), -2555.5, 114, 698.5, -113, 0));
        rank_p.setHologramTexts("§6§lMine P", "§7Klikni pravým k prodeji");
        rank_p.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine p")));
        rank_p.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall p")));
        rank_p.spawn();
        rank_p.setVillagerProfession(Villager.Profession.LIBRARIAN);
        rank_p.setVillagerType(Villager.Type.DESERT);

        // Rank Q
        InteractiveNPC rank_q = new InteractiveNPC(this.registry, "Rank Q", EntityType.VILLAGER);
        rank_q.setLocation(new Location(Bukkit.getWorld("mines"), -2935.5, 104, 792.5, 167, 0));
        rank_q.setHologramTexts("§6§lMine Q", "§7Klikni pravým k prodeji");
        rank_q.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine q")));
        rank_q.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall q")));
        rank_q.spawn();
        rank_q.setVillagerProfession(Villager.Profession.LEATHERWORKER);
        rank_q.setVillagerType(Villager.Type.DESERT);

        // Rank R
        InteractiveNPC rank_r = new InteractiveNPC(this.registry, "Rank R", EntityType.VILLAGER);
        rank_r.setLocation(new Location(Bukkit.getWorld("mines"), -3466.5, 104, 847.5, -86, 0));
        rank_r.setHologramTexts("§6§lMine R", "§7Klikni pravým k prodeji");
        rank_r.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine r")));
        rank_r.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall r")));
        rank_r.spawn();
        rank_r.setVillagerProfession(Villager.Profession.WEAPONSMITH);
        rank_r.setVillagerType(Villager.Type.SNOW);

        // Rank S
        InteractiveNPC rank_s = new InteractiveNPC(this.registry, "Rank S", EntityType.VILLAGER);
        rank_s.setLocation(new Location(Bukkit.getWorld("mines"), -3530.5, 125, 486.5, -47, 0));
        rank_s.setHologramTexts("§6§lMine S", "§7Klikni pravým k prodeji");
        rank_s.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine s")));
        rank_s.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall s")));
        rank_s.spawn();
        rank_s.setVillagerProfession(Villager.Profession.CLERIC);
        rank_s.setVillagerType(Villager.Type.JUNGLE);

        // Rank T
        InteractiveNPC rank_t = new InteractiveNPC(this.registry, "Rank T", EntityType.VILLAGER);
        rank_t.setLocation(new Location(Bukkit.getWorld("mines"), -3970.5, 114, 572.5, 131, 0));
        rank_t.setHologramTexts("§6§lMine T", "§7Klikni pravým k prodeji");
        rank_t.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine t")));
        rank_t.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall t")));
        rank_t.spawn();
        rank_t.setVillagerProfession(Villager.Profession.CLERIC);
        rank_t.setVillagerType(Villager.Type.PLAINS);

        // Rank U
        InteractiveNPC rank_u = new InteractiveNPC(this.registry, "Rank U", EntityType.VILLAGER);
        rank_u.setLocation(new Location(Bukkit.getWorld("mines"), -4515.5, 145, 758.5, -135, 0));
        rank_u.setHologramTexts("§6§lMine U", "§7Klikni pravým k prodeji");
        rank_u.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine u")));
        rank_u.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall u")));
        rank_u.spawn();
        rank_u.setVillagerProfession(Villager.Profession.FLETCHER);
        rank_u.setVillagerType(Villager.Type.PLAINS);

        // Rank V
        InteractiveNPC rank_v = new InteractiveNPC(this.registry, "Rank V", EntityType.VILLAGER);
        rank_v.setLocation(new Location(Bukkit.getWorld("mines"), -4968.5, 104, 803.5, 14, 0));
        rank_v.setHologramTexts("§6§lMine V", "§7Klikni pravým k prodeji");
        rank_v.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine v")));
        rank_v.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall v")));
        rank_v.spawn();
        rank_v.setVillagerProfession(Villager.Profession.ARMORER);
        rank_v.setVillagerType(Villager.Type.PLAINS);

        // Rank W
        InteractiveNPC rank_w = new InteractiveNPC(this.registry, "Rank W", EntityType.VILLAGER);
        rank_w.setLocation(new Location(Bukkit.getWorld("mines"), -5603.5, 111, 946.5, 0, 0));
        rank_w.setHologramTexts("§6§lMine W", "§7Klikni pravým k prodeji");
        rank_w.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine w")));
        rank_w.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall w")));
        rank_w.spawn();
        rank_w.setVillagerProfession(Villager.Profession.MASON);
        rank_w.setVillagerType(Villager.Type.JUNGLE);

        // Rank X
        InteractiveNPC rank_x = new InteractiveNPC(this.registry, "Rank X", EntityType.VILLAGER);
        rank_x.setLocation(new Location(Bukkit.getWorld("mines"), -6071.5, 133, 1058.5, -112, 0));
        rank_x.setHologramTexts("§b§lMine X", "§7Klikni pravým k prodeji");
        rank_x.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine x")));
        rank_x.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall x")));
        rank_x.spawn();
        rank_x.setVillagerProfession(Villager.Profession.FISHERMAN);
        rank_x.setVillagerType(Villager.Type.SNOW);

        // Rank Y
        InteractiveNPC rank_y = new InteractiveNPC(this.registry, "Rank Y", EntityType.VILLAGER);
        rank_y.setLocation(new Location(Bukkit.getWorld("mines"), -6626.5, 129, 1120.5, -34, 0));
        rank_y.setHologramTexts("§b§lMine Y", "§7Klikni pravým k prodeji");
        rank_y.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine y")));
        rank_y.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall y")));
        rank_y.spawn();
        rank_y.setVillagerProfession(Villager.Profession.SHEPHERD);
        rank_y.setVillagerType(Villager.Type.TAIGA);

        // Rank Z
        InteractiveNPC rank_z = new InteractiveNPC(this.registry, "Rank Z", EntityType.VILLAGER);
        rank_z.setLocation(new Location(Bukkit.getWorld("mines"), -6874.5, 119, 1275.5, 34, 0));
        rank_z.setHologramTexts("§b§lMine Z", "§7Klikni pravým k prodeji");
        rank_z.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine z")));
        rank_z.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall z")));
        rank_z.spawn();
        rank_z.setVillagerProfession(Villager.Profession.NONE);
        rank_z.setVillagerType(Villager.Type.SNOW);

        // Prestige NPC na Rank Z
        InteractiveNPC prestige = new InteractiveNPC(this.registry, "Prestige", EntityType.ILLUSIONER);
        prestige.setLocation(new Location(Bukkit.getWorld("mines"), -6881.5, 119, 1274.5, -33, 0));
        prestige.setHologramTexts("§9§lPrestiges", "§7Zvyš si Prestige a rank!");
        prestige.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("prestige")));
        prestige.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("prestige")));
        prestige.spawn();

        // Prestige P5
        InteractiveNPC prestige_P5 = new InteractiveNPC(this.registry, "Prestige P5", EntityType.VINDICATOR);
        prestige_P5.setLocation(new Location(Bukkit.getWorld("mines"), -7340.5, 92.5, 1249.5, -102, 0));
        prestige_P5.setHologramTexts("§6§lDůl P5", "§7Klikni pravým k prodeji");
        prestige_P5.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine custom P5")));
        prestige_P5.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall mine P5")));
        prestige_P5.spawn();

        InteractiveNPC sand_mine_P8 = new InteractiveNPC(this.registry, "Prestige P8", EntityType.HUSK);
        sand_mine_P8.setLocation(new Location(Bukkit.getWorld("mines"), -8279.5, 98.5, 1111.5, -102, 0));
        sand_mine_P8.setHologramTexts("§e§lPísečný důl P8", "§7Klikni pravým k prodeji");
        sand_mine_P8.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine custom P8")));
        sand_mine_P8.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall mine P8")));
        sand_mine_P8.spawn();

        InteractiveNPC mine_P12 = new InteractiveNPC(this.registry, "Prestige P12", EntityType.EVOKER);
        mine_P12.setLocation(new Location(Bukkit.getWorld("mines"), -8876, 103.5, 1144.5, -117, 0));
        mine_P12.setHologramTexts("§b§lDůl P12", "§7Klikni pravým k prodeji");
        mine_P12.setLeftClickAction(new ClickAction((action) -> action.getClicker().performCommand("keeper mine custom P12")));
        mine_P12.setRightClickAction(new ClickAction((action) -> action.getClicker().performCommand("sellall mine P12")));
        mine_P12.spawn();

    }

    public void destroyNPCs() {
        npcList.forEach((InteractiveNPC::remove));
    }
}
