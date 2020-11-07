package cz.wake.craftprison.npc;

import cz.wake.craftprison.Main;
import net.citizensnpcs.api.event.SpawnReason;
import net.citizensnpcs.api.npc.NPC;
import net.citizensnpcs.api.npc.NPCRegistry;
import net.citizensnpcs.trait.VillagerProfession;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

public class InteractiveNPC {

    private NPCRegistry npcRegistry;
    private String name;
    private String world;
    private Location location;
    private String skinUUID;
    private ArrayList<String> hologramTexts;
    private ArrayList<ArmorStand> hologramArmorStands;
    private ItemStack itemInHand;
    private EntityType entityType;
    private ClickAction rightClickAction;
    private ClickAction leftClickAction;
    private NPC npc;

    public InteractiveNPC(@NotNull NPCRegistry registry, @NotNull String name, @NotNull EntityType entityType) {
        this.npcRegistry = registry;
        this.name = name;
        this.entityType = entityType;
        this.hologramTexts = new ArrayList<>();
        this.hologramArmorStands = new ArrayList<>();
    }

    public InteractiveNPC setLocation(@NotNull Location location) {
        this.location = location;
        this.world = location.getWorld().getName();
        return this;
    }

    public InteractiveNPC setSkinUUID(@NotNull String uuid) {
        this.skinUUID = uuid;
        return this;
    }

    public InteractiveNPC setHologramTexts(String... lines) {
        Collections.addAll(this.hologramTexts, lines);
        return this;
    }

    public void setVillagerProfession(Villager.Profession profession) {
        this.npc.getOrAddTrait(VillagerProfession.class).setProfession(profession);
    }

    public void setVillagerType(Villager.Type type) {
        this.npc.getOrAddTrait(VillagerTypeTrait.class).setVillagerType(type);
    }

    public InteractiveNPC setRightClickAction(ClickAction action) {
        this.rightClickAction = action;
        return this;
    }

    public InteractiveNPC setLeftClickAction(ClickAction action) {
        this.leftClickAction = action;
        return this;
    }

    public ClickAction getRightClickAction() {
        return rightClickAction;
    }

    public ClickAction getLeftClickAction() {
        return leftClickAction;
    }

    public boolean spawn() {
        try {
            // Nacteni chunku
            Bukkit.getWorld(this.world).loadChunk(this.location.getChunk());
            deleteNearbyEntities(); // Smazání armorstandů

            if (entityType == EntityType.PLAYER) {
                this.npc = this.npcRegistry.createNPC(this.entityType, UUID.fromString(this.skinUUID), 1, this.name);
            } else {
                this.npc = this.npcRegistry.createNPC(this.entityType, this.name);
            }
            this.npc.spawn(this.location, SpawnReason.PLUGIN);
            this.npc.data().setPersistent(NPC.NAMEPLATE_VISIBLE_METADATA, false); // Skrytí jména

            Collections.reverse(this.hologramTexts);

            this.hologramTexts.forEach(text -> {
                ArmorStand as = (ArmorStand) Bukkit.getWorld(this.world).spawnEntity(this.location, EntityType.ARMOR_STAND);
                as.setGravity(false);
                as.setCanPickupItems(false);
                as.setBasePlate(false);
                as.setVisible(false);
                as.setCustomNameVisible(true);
                as.setCustomName(text);
                this.hologramArmorStands.add(as);
                this.location.add(0, 0.25, 0);
            });

            Main.getInstance().getNpcManager().getNpcList().add(this);

            Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[CraftPrison] " + ChatColor.WHITE + " NPC " + name + " byl spawnut.");
            return true;
        } catch (Exception exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public void remove() {
        Bukkit.getWorld(this.world).loadChunk(this.location.getChunk());
        this.hologramArmorStands.forEach(Entity::remove);
        this.npc.destroy();
        Main.getInstance().getNpcManager().getNpcList().remove(this);
    }

    public NPC getNpc() {
        return this.npc;
    }

    private void deleteNearbyEntities() {
        for (Entity ent : this.location.getWorld().getEntities()) {
            if (ent.getLocation().distance(this.location) <= 5.0) {
                ent.remove();
            }
        }
    }


}
