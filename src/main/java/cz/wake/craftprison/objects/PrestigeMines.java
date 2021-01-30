package cz.wake.craftprison.objects;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum PrestigeMines {

    P5(1, "P5", 5, new Location(Bukkit.getWorld("mines"), -7338.5, 92, 1249.5, -90, 0));

    private int weight;
    private String name;
    private int requiredPrestige;
    private Location location;

    PrestigeMines(int weight, String name, int requiredPrestige, Location location) {
        this.weight = weight;
        this.name = name;
        this.requiredPrestige = requiredPrestige;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public int getRequiredPrestige() {
        return requiredPrestige;
    }

    public static PrestigeMines[] getPrestigeMinesList() {
        return PrestigeMines.values();
    }

    public static List<String> getRanksAsList() {
        return Stream.of(PrestigeMines.values()).map(PrestigeMines::getName).collect(Collectors.toList());
    }

    @Nullable
    public static PrestigeMines getByName(String name) {
        for (PrestigeMines prestigeMines : getPrestigeMinesList()) {
            if (prestigeMines.getName().equals(name)) {
                return prestigeMines;
            }
        }
        return null;
    }
}
