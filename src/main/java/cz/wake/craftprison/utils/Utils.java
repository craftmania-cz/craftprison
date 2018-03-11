package cz.wake.craftprison.utils;

public class Utils {

    private static final int[] NUMBER_VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] NUMBER_LETTERS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public static int convertRomanToInt(String roman) {
        for (int i = 0; i < NUMBER_LETTERS.length; i++) {
            if (roman.startsWith(NUMBER_LETTERS[i]))
                return NUMBER_VALUES[i] + convertRomanToInt(roman.replaceFirst(NUMBER_LETTERS[i], ""));
        }
        return 0;
    }

    public static String convertIntToRoman(int num) {
        String roman = "";
        for (int i = 0; i < NUMBER_VALUES.length; i++) {
            while (num >= NUMBER_VALUES[i]) {
                roman += NUMBER_LETTERS[i];
                num -= NUMBER_VALUES[i];
            }
        }
        return roman;
    }

}
