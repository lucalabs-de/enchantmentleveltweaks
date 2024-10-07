package de.lucalabs.enchantmentlevels.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class NumeralsTest {
    @Test
    void testComputeClassicRomanNumeral() {
        var testCases = Map.of(
                1, "I",
                4, "IV",
                55, "LV",
                90, "XC",
                789, "DCCLXXXIX",
                1066, "MLXVI",
                3999, "MMMCMXCIX"
        );

        for (var testCase : testCases.entrySet()) {
            Assertions.assertEquals(testCase.getValue(), Numerals.computeClassicRomanNumeral(testCase.getKey()));
        }
    }
}
