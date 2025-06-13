package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

/**
 * A generator that creates arrays with a specified entropy level using an external Python script.
 */
public class EntropyGenerator extends AbstractGenerator {

    private double targetEntropy;

    /**
     * Constructs an {@code EntropyGenerator} with a specified size and entropy target.
     *
     * @param size          the size of the array to generate
     * @param targetEntropy the desired entropy level (must be in range [0, 1])
     * @throws IllegalArgumentException if entropy is out of range
     */
    public EntropyGenerator(int size, double targetEntropy) {
        super(size);
        if (targetEntropy < 0 || targetEntropy > 1) {
            throw new IllegalArgumentException("Entropy must be in [0, 1]");
        }
        this.targetEntropy = targetEntropy;
    }

    /**
     * Generates an array with a predefined entropy level by calling a Python script.
     *
     * @return an integer array with the specified entropy
     */
    public int[] generate() {
        int[] data = new int[this.dataSize];
        File outputFile = new File("output");
        String pythonCommand = detectPythonCommand();
        String pythonScript = "EntropyGenerator.py";

        ProcessBuilder processBuilder = new ProcessBuilder(pythonCommand, pythonScript, 
                String.valueOf(this.dataSize), String.valueOf(targetEntropy));
        processBuilder.redirectOutput(outputFile);
        processBuilder.redirectError(outputFile);

        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode != 0) {
                System.err.println("Error: Python script execution failed (exit code " + exitCode + "). Check output file.");
                return data; // Return empty data instead of stopping the program
            }

            if (outputFile.exists()) {
                try (BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {
                    String line;
                    int i = 0;
                    while ((line = reader.readLine()) != null && i < this.dataSize) {
                        try {
                            data[i++] = Integer.parseInt(line.trim());
                        } catch (NumberFormatException e) {
                            System.err.println("Invalid output from Python script: " + line);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error running Python script: " + e.getMessage());
        }

        return data;
    }

    /**
     * Calculates the Shannon entropy of a given array.
     *
     * @param array the input array
     * @return the entropy value
     */
    public static double calculateEntropy(int[] array) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : array) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        double totalElements = array.length;
        double entropy = 0.0;

        for (int frequency : frequencyMap.values()) {
            double probability = frequency / totalElements;
            entropy -= probability * (Math.log(probability) / Math.log(2));
        }

        return entropy;
    }

    /**
     * Computes the base-2 logarithm of a number.
     *
     * @param x the number
     * @return log2(x)
     */
    public static double log2(double x) {
        return Math.log(x) / Math.log(2);
    }

    /**
     * Detects the correct Python command available on the system.
     *
     * @return "python" or "python3"
     * @throws RuntimeException if no Python interpreter is found
     */
    private static String detectPythonCommand() {
        try {
            Process process = new ProcessBuilder("python", "--version").start();
            if (process.waitFor() == 0) return "python";
        } catch (Exception ignored) {}

        try {
            Process process = new ProcessBuilder("python3", "--version").start();
            if (process.waitFor() == 0) return "python3";
        } catch (Exception ignored) {}

        throw new RuntimeException("Neither 'python' nor 'python3' was found on the system.");
    }

    @Override
    public String toString() {
        return "EntropyGenerator[Size=" + dataSize + ", targetEntropy=" + targetEntropy + "]";
    }

    @Override
    public void setVariable(Number entropy) {
        double entropyValue = entropy.doubleValue();
        if (entropyValue < 0 || entropyValue > 1) {
            throw new IllegalArgumentException("Entropy must be in [0, 1]");
        }
        this.targetEntropy = entropyValue;
    }

    @Override
    public Generator copy() {
        try {
            return new EntropyGenerator(dataSize, targetEntropy);
        } catch (Exception e) {
            return null; // Safer than returning an incomplete object
        }
    }

    @Override
    public Number getVariable() {
        return this.targetEntropy;
    }
}
