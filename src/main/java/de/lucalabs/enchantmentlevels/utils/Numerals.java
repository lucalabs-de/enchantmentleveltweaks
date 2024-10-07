package de.lucalabs.enchantmentlevels.utils;

import java.util.Map;

public final class Numerals {
    private static final Map<Integer, String> numerals = Map.of(
            1, "I",
            5, "V",
            10, "X",
            50, "L",
            100, "C",
            500, "D",
            1000, "M"
    );

    private Numerals() {
    }

    public static String computeRomanNumeral(int number) {
        assert number < 4000 : "Largest representable number is 3999";
        // pad array to avoid out-of-bounds indices
        int[] uniqueNumerals = {1, 5, 10, 50, 100, 500, 1000, 1000, 1000};

        StringBuilder result = new StringBuilder();
        int remainder = number;
        int powerOfTen = 0;

        while (remainder > 0 && powerOfTen <= 3) {
            int digit = remainder % 10;
            int numeralIndex = 2 * powerOfTen;
            int value1 = uniqueNumerals[numeralIndex];
            int value2 = uniqueNumerals[numeralIndex + 1];

            switch (digit) {
                case 4:
                    result.insert(0, numerals.get(value2));
                    result.insert(0, numerals.get(value1));
                    break;
                case 9:
                    int value3 = uniqueNumerals[numeralIndex + 2];
                    result.insert(0, numerals.get(value3));
                    result.insert(0, numerals.get(value1));
                    break;
                default:
                    int numRepeats = digit >= 5 ? 1 : 0;
                    result.insert(0, numerals.get(value1).repeat(digit - numRepeats * 5));
                    result.insert(0, numerals.get(value2).repeat(numRepeats));
            }

            remainder /= 10;
            ++powerOfTen;
        }

        return result.toString();
    }
}
