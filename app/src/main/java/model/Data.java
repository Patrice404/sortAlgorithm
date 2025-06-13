package model;

import java.util.Random;

/**
 * Represents a data structure that manages an array and its associated
 * properties.
 * Provides utility methods for array manipulation, including shuffling,
 * copying,
 * finding the maximum value, and checking if the array is sorted.
 */
public class Data {

    /** The main array managed by this class. */
    private int[] array;

    /** A backup copy of the array, used for resetting. */
    private int[] arrayCopy;

    /** The maximum value in the array. */
    private int maxValue;

    /** The size of the array. */
    private int size;

    /**
     * Constructs a Data instance with the specified array.
     * Initializes an array copy and determines the maximum value.
     *
     * @param array the array to be managed by this instance
     * @throws IllegalArgumentException if the provided array is null or empty
     */
    public Data(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("The array cannot be null or empty.");
        }
        this.array = array;
        this.size = array.length;
        this.arrayCopy = new int[this.size];
        makeCopy();
        this.maxValue = findMaxValue(this.array);
    }

    /**
     * Finds the maximum value in the given array.
     *
     * @param array the array to search
     * @return the maximum value in the array
     * @throws IllegalArgumentException if the array is null or empty
     */
    private int findMaxValue(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("The array is null or empty.");
        }

        int max = array[0]; // Initialize with the first element
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    /**
     * Randomly shuffles the elements of the array.
     */
    public void shuffle() {
        Random rand = new Random();
        for (int i = this.size - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            swap(i, j);
        }
    }

    /**
     * Creates a backup copy of the current array.
     */
    private void makeCopy() {
        System.arraycopy(this.array, 0, this.arrayCopy, 0, this.size);
    }

    /**
     * Checks if the specified index is within the valid range of the array.
     *
     * @param i the index to check
     * @return true if the index is valid, false otherwise
     */
    private boolean isCorrectIndex(int i) {
        return i >= 0 && i < this.size;
    }

    /**
     * Swaps the values at the specified indices in the array.
     *
     * @param i the index of the first value
     * @param j the index of the second value
     * @return true if the swap was successful, false otherwise
     */
    public boolean swap(int i, int j) {
        if (isCorrectIndex(i) && isCorrectIndex(j)) {
            int tmp = this.array[i];
            this.array[i] = this.array[j];
            this.array[j] = tmp;
            return true;
        }
        return false;
    }

    /**
     * Resets the array to its original state using the backup copy.
     */
    public void reset() {
        System.arraycopy(this.arrayCopy, 0, this.array, 0, this.size);
    }

    /**
     * Sets a specific value at the given index in the array.
     *
     * @param index the index to update
     * @param value the new value to set
     * @throws IllegalArgumentException if the index is out of bounds
     */
    public void setValueAt(int index, int value) {
        if (!isCorrectIndex(index)) {
            throw new IllegalArgumentException("The provided index is out of bounds.");
        }
        this.array[index] = value;
    }

    /**
     * Checks if the array is sorted in ascending order.
     *
     * @return true if the array is sorted, false otherwise
     */
    public boolean isSorted() {
        for (int i = 1; i < this.array.length; i++) {
            if (array[i - 1] > array[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the value at the specified index in the array.
     *
     * @param i the index of the value to retrieve
     * @return the value at the specified index
     * @throws IllegalArgumentException if the index is invalid
     */
    public int getValueAt(int i) {
        if (!isCorrectIndex(i)) {
            throw new IllegalArgumentException("The provided index is invalid for the array.");
        }
        return this.array[i];
    }

    /**
     * Gets the current array.
     *
     * @return the array
     */
    public int[] getArray() {
        return array;
    }

    /**
     * Gets the maximum value of the array.
     *
     * @return the maximum value of the array
     */
    public int getMaxValue() {
        return maxValue;
    }

    /**
     * Gets the size of the array.
     *
     * @return the size of the array
     */
    public int getSize() {
        return size;
    }

    /**
     * Returns a string representation of the array.
     *
     * @return a string representing the array's contents
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.size; i++) {
            sb.append(this.array[i]);
            if (i < this.size - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
