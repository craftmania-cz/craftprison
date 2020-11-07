package cz.wake.craftprison.npc;

import net.citizensnpcs.api.exception.NPCLoadException;
import net.citizensnpcs.api.trait.Trait;
import net.citizensnpcs.api.trait.TraitName;
import net.citizensnpcs.api.util.DataKey;
import org.bukkit.entity.Villager;
import org.bukkit.entity.ZombieVillager;

@TraitName("villagertype")
public class VillagerTypeTrait extends Trait {

    private Villager.Type villagerType;

    public VillagerTypeTrait() {
        super("villagertype");
        this.villagerType = Villager.Type.PLAINS;
    }

    public void load(DataKey key) throws NPCLoadException {
        try {
            this.villagerType = Villager.Type.valueOf(key.getString(""));
            if (this.villagerType.name().equals("NORMAL")) {
                this.villagerType = Villager.Type.PLAINS;
            }

        } catch (IllegalArgumentException var3) {
            throw new NPCLoadException("Invalid profession.");
        }
    }

    public void onSpawn() {
        if (this.npc.getEntity() instanceof Villager) {
            ((Villager)this.npc.getEntity()).setVillagerType(this.villagerType);
        } else if (this.npc.getEntity() instanceof ZombieVillager) {
            ((ZombieVillager)this.npc.getEntity()).setVillagerType(this.villagerType);
        }
    }

    public void save(DataKey key) {
        key.setString("", this.villagerType.name());
    }

    public void setVillagerType(Villager.Type type) {
        this.villagerType = type;
        if (this.npc.getEntity() instanceof Villager) {
            ((Villager)this.npc.getEntity()).setVillagerType(this.villagerType);
        } else if (this.npc.getEntity() instanceof ZombieVillager) {
            ((ZombieVillager)this.npc.getEntity()).setVillagerType(this.villagerType);
        }
    }

    public String toString() {
        return "VillagerType{" + this.villagerType + "}";
    }

}
