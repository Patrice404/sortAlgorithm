package model.algorithm;

import experience.Sonde;
import model.Data;
import view.Visualizer;

/**
 * Interface representing a sorting strategy.
 * Implementations of this interface will provide specific sorting algorithms.
 */
public interface SortStrategy {

    /**
     * Sorts the given array using the specified visualizer.
     *
     * @param array the data array to be sorted
     * @param visualizer the visualizer to be used during sorting
     * @return a Sonde object containing information about the sorting process
     */
    public Sonde sort(Data array, Visualizer visualizer);

    /**
     * Returns the Sonde object associated with this sorting strategy.
     *
     * @return the Sonde object
     */
    public Sonde getSonde();

    /**
     * Returns a string representation of the sorting strategy.
     *
     * @return a string representation of the sorting strategy
     */
    @Override
    String toString();
}