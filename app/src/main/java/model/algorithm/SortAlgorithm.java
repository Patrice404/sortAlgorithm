package model.algorithm;

import experience.Result;
import experience.Sonde;
import model.Data;
import view.Visualizer;

/**
 * Represents a sorting algorithm that can be executed with a specific strategy,
 * a dataset, and a visualizer for observing the sorting process.
 */
public class SortAlgorithm {

    private SortStrategy strategy;
    private Data data;
    private Visualizer visualizer;

    /**
     * Constructs a SortAlgorithm instance with the specified data, sorting strategy,
     * and visualizer.
     *
     * @param data       the data to be sorted
     * @param strategy   the sorting strategy to use
     * @param visualizer the visualizer for observing the sorting process
     */
    public SortAlgorithm(Data data, SortStrategy strategy, Visualizer visualizer) {
        this.data = data;
        this.strategy = strategy;
        this.visualizer = visualizer;
    }

    public SortAlgorithm() {
        this.data = null;
        this.strategy = null;
        this.visualizer = null;
    }

    /**
     * Sets the sorting strategy to be used by this algorithm.
     *
     * @param strategy the new sorting strategy
     */
    public void setStrategy(SortStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Executes the sorting process using the current strategy, dataset, and visualizer.
     *
     * @return a {@link Result} object containing information about the sorting process,
     *         such as execution time or comparisons count
     */
    public Sonde run() {
        return this.strategy.sort(this.data, this.visualizer);
    }

    /**
     * Updates the data to be sorted.
     *
     * @param data the new dataset to sort
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * Sets the visualizer to be used for observing the sorting process.
     *
     * @param visualizer the new visualizer
     */
    public void setVisualizer(Visualizer visualizer) {
        this.visualizer = visualizer;
    }
}
