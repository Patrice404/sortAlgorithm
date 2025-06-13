package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class SwapGeneratorTest {

    @Test
    void testGenerateWithZeroSwaps() {
        SwapGenerator generator = new SwapGenerator(10, 0, 12345);
        int[] result = generator.generate();
        int[] expected = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertArrayEquals(expected, result, "Array should remain sorted when no swaps are performed");
    }

    @Test
    void testGenerateWithSwaps() {
        SwapGenerator generator = new SwapGenerator(10, 5, 12345);
        int[] result = generator.generate();
        assertEquals(10, result.length, "Generated array should have the correct size");
        assertTrue(Arrays.stream(result).distinct().count() == 10, "Generated array should contain unique elements");
    }

    @Test
    void testGenerateWithRandomSeed() {
        SwapGenerator generator1 = new SwapGenerator(10, 5, 12345);
        SwapGenerator generator2 = new SwapGenerator(10, 5, 12345);
        int[] result1 = generator1.generate();
        int[] result2 = generator2.generate();
        assertArrayEquals(result1, result2, "Arrays generated with the same seed should be identical");

        SwapGenerator generator3 = new SwapGenerator(10, 5, 0);
        int[] result3 = generator3.generate();
        assertNotEquals(Arrays.toString(result1), Arrays.toString(result3), "Arrays generated with different seeds should not be identical");
    }

    @Test
    void testSetVariable() {
        SwapGenerator generator = new SwapGenerator(10, 5, 12345);
        generator.setVariable(3);
        assertEquals(3, generator.getNbSwaps(), "Number of swaps should be updated correctly");
    }

    @Test
    void testCopy() {
        SwapGenerator generator = new SwapGenerator(10, 5, 12345);
        SwapGenerator copy = (SwapGenerator) generator.copy();
        assertEquals(generator.getNbSwaps(), copy.getNbSwaps(), "Copied generator should have the same number of swaps");
        assertEquals(generator.generate().length, copy.generate().length, "Copied generator should generate arrays of the same size");
    }

    @Test
    void testInvalidNbSwaps() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SwapGenerator(10, -1, 12345);
        });
        assertEquals("The number of swaps must be non-negative", exception.getMessage(), "Exception message should match");
    }

    @Test
    void testToString() {
        SwapGenerator generator = new SwapGenerator(10, 5, 12345);
        String expected = "SwapGenerator[Size=10,nbSwaps=5]";
        assertEquals(expected, generator.toString(), "toString method should return the correct representation");
    }
}