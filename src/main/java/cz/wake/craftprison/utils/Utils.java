package cz.wake.craftprison.utils;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static final int[] ROMAN_NUMBER_VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] ROMAN_NUMBER_LETTERS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public static int convertRomanToInt(String roman) {
        for (int i = 0; i < ROMAN_NUMBER_LETTERS.length; i++) {
            if (roman.startsWith(ROMAN_NUMBER_LETTERS[i]))
                return ROMAN_NUMBER_VALUES[i] + convertRomanToInt(roman.replaceFirst(ROMAN_NUMBER_LETTERS[i], ""));
        }
        return 0;
    }

    public static String convertIntToRoman(int num) {
        String roman = "";
        for (int i = 0; i < ROMAN_NUMBER_VALUES.length; i++) {
            while (num >= ROMAN_NUMBER_VALUES[i]) {
                roman += ROMAN_NUMBER_LETTERS[i];
                num -= ROMAN_NUMBER_VALUES[i];
            }
        }
        return roman;
    }

    public static List<Location> generateSphere(Location centerBlock, int radius, boolean hollow) {
        ArrayList<Location> circleBlocks = new ArrayList<Location>();
        int bx = centerBlock.getBlockX();
        int by = centerBlock.getBlockY();
        int bz = centerBlock.getBlockZ();
        for (int x = bx - radius; x <= bx + radius; ++x) {
            for (int y = by - radius; y <= by + radius; ++y) {
                for (int z = bz - radius; z <= bz + radius; ++z) {
                    double distance = (bx - x) * (bx - x) + (bz - z) * (bz - z) + (by - y) * (by - y);
                    if (distance >= (double)(radius * radius) || hollow && distance < (double)((radius - 1) * (radius - 1))) continue;
                    Location l = new Location(centerBlock.getWorld(), (double)x, (double)y, (double)z);
                    circleBlocks.add(l);
                }
            }
        }
        return circleBlocks;
    }
}
