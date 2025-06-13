package model;

import java.util.Arrays;
import java.util.Random;

/**
 * Generates arrays of different predefined types with varying levels of
 * disorder.
 * This class extends {@link AbstractGenerator} and implements the
 * {@link Generator} interface.
 */
public class ArrayTypeGenerator extends AbstractGenerator {

    /** The type of array to generate, defining its level of disorder. */
    private ArrayType type;

    /**
     * Constructs an {@code ArrayTypeGenerator} with a specified size and array
     * type.
     *
     * @param size the size of the array to generate (must be at least 2)
     * @param type the type of array defining its disorder level
     */
    public ArrayTypeGenerator(int size, ArrayType type) {
        super(size);
        this.type = type;
    }

    /**
     * Generates an array based on the specified type with different levels of
     * ordering.
     *
     * @return an array of integers with a predefined level of disorder
     */
    @Override
    public int[] generate() {
        Random random = new Random();
        int maxValue = (10 * this.dataSize + 1);
        int[] data = new int[this.dataSize];

        // Generate random data
        for (int i = 0; i < this.dataSize; i++) {
            data[i] = random.nextInt(maxValue + 1);
        }

        // Apply ordering based on the array type
        switch (this.type) {
            case LOW_1: {
                // 90% sorted, 10% random
                int size1 = (this.dataSize * 90) / 100;
                Arrays.sort(data, 0, size1);
                break;
            }

            case LOW_2: {
                // Fully sorted
                Arrays.sort(data);
                break;
            }

            case MEDIUM_1: {
                // 25% random - 50% sorted - 25% random
                int size1 = (this.dataSize * 25) / 100;
                int size2 = this.dataSize - 2 * size1;
                Arrays.sort(data, size1, size1 + size2);
                break;
            }

            case MEDIUM_2: {
                // 50% sorted - 50% random
                int size1 = (this.dataSize * 50) / 100;
                Arrays.sort(data, 0, size1);
                break;
            }

            case MEDIUM_3: {
                // 50% random - 50% sorted
                int size1 = (this.dataSize * 50) / 100;
                Arrays.sort(data, size1, this.dataSize);
                break;
            }

            case MEDIUM_4: {
                // 25% sorted - 50% random - 25% sorted
                int size1 = (this.dataSize * 25) / 100;
                int size2 = this.dataSize - 2 * size1;
                Arrays.sort(data, 0, size1);
                Arrays.sort(data, size1 + size2, this.dataSize);
                break;
            }

            case HIGHT_1: {
                // Completely random array (no sorting applied)
                break;
            }

            case HIGHT_2: {
                // Fully sorted in descending order
                Arrays.sort(data);
                int size = this.dataSize;

                // Reverse the sorted array
                for (int i = 0; i < size / 2; i++) {
                    int temp = data[i];
                    data[i] = data[size - 1 - i];
                    data[size - 1 - i] = temp;
                }
                break;
            }

            default:
                break;
        }

        return data;
    }

    /**
     * Returns a string representation of the generator, including its type and
     * array size.
     *
     * @return a string describing this generator
     */
    @Override
    public String toString() {
        return "ArrayTypeGenerator[type=" + type + ",Size=" + this.dataSize + "]";
    }

    /**
     * Sets a variable parameter for the generator.
     * This method is not implemented for this class.
     *
     * @param entropy the parameter value to set (unused)
     * @throws UnsupportedOperationException as this method is not implemented
     */
    @Override
    public void setVariable(Number entropy) {
        throw new UnsupportedOperationException("Unimplemented method 'setVariable'");
    }

    /**
     * Creates a copy of this generator.
     *
     * @return a new {@code ArrayTypeGenerator} with the same size and type
     */
    @Override
    public Generator copy() {
        return new ArrayTypeGenerator(this.dataSize, this.type);
    }

    /**
     * Gets the variable parameter of the generator.
     * This method is not implemented for this class.
     *
     * @return nothing, as this method is not implemented
     * @throws UnsupportedOperationException as this method is not implemented
     */
    @Override
    public Number getVariable() {
        throw new UnsupportedOperationException("Unimplemented method 'getVariable'");
    }

}
