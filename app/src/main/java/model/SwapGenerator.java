package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * A generator that creates a nearly sorted array by performing a specified number of swaps.
 */
public class SwapGenerator extends AbstractGenerator {

    private int nbSwaps;
    private long seed;

    /**
     * Constructs a SwapGenerator with a given size, number of swaps, and seed.
     *
     * @param size    the size of the array
     * @param nbSwaps the number of swaps to perform
     * @param seed    the seed for randomness (0 for a randomly generated seed)
     * @throws IllegalArgumentException if the number of swaps is negative
     */
    public SwapGenerator(int size, int nbSwaps, long seed) {
        super(size);
        if (nbSwaps < 0) {
            throw new IllegalArgumentException("The number of swaps must be non-negative");
        }
        this.nbSwaps = nbSwaps;
        this.seed = seed;
    }

    /**
     * Constructs a SwapGenerator with a default seed.
     *
     * @param size    the size of the array
     * @param nbSwaps the number of swaps to perform
     * @throws IllegalArgumentException if the number of swaps is negative
     */
    public SwapGenerator(int size, int nbSwaps) {
        this(size, nbSwaps, 0);
    }

    /**
     * Generates a nearly sorted array by swapping elements a specified number of times.
     *
     * @return an array with the specified level of disorder
     */
    @Override
    public int[] generate() {
        int[] data = new int[this.dataSize];

        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < this.dataSize; i++) {
            data[i] = i;
            indices.add(i);
        }

        Random random = this.seed == 0 ? new Random() : new Random(this.seed);
        int i, j, k;

        for (int x = 0; x < this.nbSwaps; x++) {
            k = random.nextInt(indices.size());
            i = indices.get(k);
            indices.remove(k);
            k = random.nextInt(indices.size());
            j = indices.get(k);
            indices.remove(k);
            int tmp = data[i];
            data[i] = data[j];
            data[j] = tmp;
        }
        return data;
    }

    /**
     * Returns the number of swaps performed.
     *
     * @return the number of swaps
     */
    public int getNbSwaps() {
        return this.nbSwaps;
    }

    /**
     * Sets the number of swaps based on a given entropy value.
     *
     * @param entropy the number of swaps to perform
     * @throws IllegalArgumentException if the number of swaps is negative
     */
    @Override
    public void setVariable(Number entropy) {
        int nbSwaps = entropy.intValue();
        if (nbSwaps < 0) {
            throw new IllegalArgumentException("The number of swaps must be non-negative");
        }
        this.nbSwaps = nbSwaps;
    }

    /**
     * Creates and returns a copy of this SwapGenerator instance.
     *
     * @return a new SwapGenerator instance with the same parameters
     */
    @Override
    public Generator copy() {
        return new SwapGenerator(this.dataSize, this.nbSwaps, this.seed);
    }

    /**
     * Returns the number of swaps as a variable.
     *
     * @return the number of swaps
     */
    @Override
    public Number getVariable() {
        return this.getNbSwaps();
    }

    /**
     * Returns a string representation of this SwapGenerator.
     *
     * @return a string describing the SwapGenerator instance
     */
    @Override
    public String toString() {
        return "SwapGenerator[Size=" + this.dataSize + ",nbSwaps=" + nbSwaps + "]";
    }
}
