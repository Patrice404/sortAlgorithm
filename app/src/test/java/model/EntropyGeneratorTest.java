package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.jupiter.api.Test;

public class EntropyGeneratorTest {
     @Test
    void testGenerate() {
        Random random = new Random();
        double targetEntropy = random.nextDouble();
        int size = random.nextInt(1_000_000) + 1;

        EntropyGenerator generator = new EntropyGenerator(size, targetEntropy);

        try {
            int[] data = generator.generate();
            double entropy = EntropyGenerator.calculateEntropy(data) / EntropyGenerator.log2(size);
            assertTrue(Math.abs(targetEntropy-entropy) < 5 * 1e-2, String.valueOf(Math.abs(targetEntropy-entropy)));

        } catch (Exception e) {
            assertFalse(true, e.getMessage());
        }
        
    }

    
}
