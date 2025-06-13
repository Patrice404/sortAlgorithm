package visualisation;

import java.util.List;
import model.Generator;
import model.algorithm.SortStrategy;

/**
 * Represents a dataset for sorting visualization and benchmarking.
 * It stores the sorting strategies to be tested and the generator used to create input data.
 */
public class DataSet {
    private List<SortStrategy> sortStrategies;
    private List<String> algoSelectedName;
    private Generator generator;

    /**
     * Constructs a DataSet with a given data generator, number of repetitions, and sorting strategies.
     *
     * @param generator        the generator used to create the dataset
     * @param nbRepetition     the number of times the sorting algorithms should be executed (not used in this class)
     * @param sortStrategies   the list of sorting strategies to be tested
     * @param algoSelectedName the list of names of the selected sorting algorithms
     * @throws IllegalArgumentException if no sorting strategy is provided
     */
    public DataSet(Generator generator, int nbRepetition, List<SortStrategy> sortStrategies,
                   List<String> algoSelectedName) throws IllegalArgumentException {
        if (sortStrategies.isEmpty()) {
            throw new IllegalArgumentException("Vous devez choisir au moins une stratégie de tri");
        }

        this.generator = generator;
        this.sortStrategies = sortStrategies;
        this.algoSelectedName = algoSelectedName;
    }

    /**
     * Executes the selected sorting strategies with or without visualization.
     *
     * @param withVisual if true, enables visualization during execution
     */
    public void play(boolean withVisual) {
        Runner runner = new Runner(this.generator, this.sortStrategies);
        runner.run(this.algoSelectedName, withVisual);
    }
}
