package model;

import java.util.Random;

/**
 * Generates an array of integers following a Gaussian (normal) distribution.
 */
public class GaussienneGenerator extends AbstractGenerator {

    private int moyenne;
    private int ecartType;

    /**
     * Constructs a Gaussian generator with a given size, mean, and standard deviation.
     *
     * @param size      the number of elements in the generated array
     * @param moyenne   the mean (expected value) of the Gaussian distribution
     * @param ecartType the standard deviation of the Gaussian distribution
     */
    public GaussienneGenerator(int size, int moyenne, int ecartType) {
        super(size);
        this.moyenne = moyenne;
        this.ecartType = Math.max(1, ecartType); // Avoid zero standard deviation
    }

    /**
     * Constructs a Gaussian generator with default parameters (mean = 0, std dev = 1).
     *
     * @param size the number of elements in the generated array
     */
    public GaussienneGenerator(int size) {
        this(size, 0, 1);
    }

    /**
     * Generates an array of integers following a Gaussian distribution.
     *
     * @return an array of normally distributed integers
     */
    @Override
    public int[] generate() {
        int[] data = new int[this.dataSize];
        Random random = new Random();

        for (int i = 0; i < data.length; i++) {
            data[i] = (int) Math.round(random.nextGaussian() * this.ecartType + this.moyenne);
        }

        return data;
    }

    @Override
    public String toString() {
        return "GaussienneGenerator[Size=" + dataSize + ",moyenne=" + moyenne + ",ecartType=" + ecartType + "]";
    }

    @Override
    public Generator copy() {
        return new GaussienneGenerator(dataSize, moyenne, ecartType);
    }

    @Override
    public Number getVariable() {
        throw new UnsupportedOperationException("Unimplemented method 'getVariable'");
    }

    @Override
    public void setVariable(Number entropy) {
        throw new UnsupportedOperationException("Unimplemented method 'setVariable'");
    }
}
