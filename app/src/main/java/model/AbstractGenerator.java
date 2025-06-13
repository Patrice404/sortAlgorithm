package model;

/**
 * An abstract class that serves as a base for data generators.
 * It ensures that the generated data size is at least 2.
 */
public abstract class AbstractGenerator implements Generator {
    
    /** The size of the data to be generated. */
    protected int dataSize;

    /**
     * Constructs an AbstractGenerator with the specified size.
     * Ensures that the size is at least 2; otherwise, prints an error message.
     *
     * @param size the size of the data to be generated (must be at least 2)
     */
    public AbstractGenerator(int size) {
        if (size >= 2) {
            this.dataSize = size;
        } else {
            System.out.println("The array size must be greater than 1");
        }
    }

    /**
     * Gets the size of the data to be generated.
     *
     * @return the data size
     */
    public int getSize() {
        return this.dataSize;
    }

    /**
     * Sets the size of the data to be generated.
     * Ensures that the size is at least 2; otherwise, prints an error message.
     *
     * @param size the new size of the data (must be at least 2)
     */
    @Override
    public void setSize(int size) {
        if (size >= 2) {
            this.dataSize = size;
        } else {
            System.out.println("The array size must be greater than 1");
        }
    }
}
