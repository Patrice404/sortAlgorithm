package visualisation;

import java.util.*;
import model.*;
import model.algorithm.*;
import processing.core.PApplet;
import view.Visualizer;

/**
 * The Runner class is responsible for executing sorting algorithms on generated datasets.
 * It can run the algorithms with or without visualization.
 */
public class Runner {

    private List<SortStrategy> sortStrategies;
    private SortAlgorithm sortAlgorithm;
    private Generator generator;

    /**
     * Constructs a Runner instance with a specified data generator and sorting strategies.
     *
     * @param generator      the generator used to create the dataset
     * @param sortStrategies the list of sorting strategies to be executed
     */
    public Runner(Generator generator, List<SortStrategy> sortStrategies) {
        this.sortStrategies = sortStrategies;
        this.generator = generator;
        this.sortAlgorithm = new SortAlgorithm(null, null, null);
    }

    /**
     * Executes the sorting algorithms on the generated dataset.
     * If visualization is enabled, it launches a graphical representation of the sorting process.
     *
     * @param algoSelectedName the list of algorithm names to be executed (not used in the method)
     * @param withVisual       if true, enables real-time visualization of the sorting process
     */
    public void run(List<String> algoSelectedName, boolean withVisual) {

        // Generate dataset
        Data data = new Data(this.generator.generate());

        // Initialize visualizer
        Visualizer visualizer = new Visualizer(data);
        this.sortAlgorithm.setVisualizer(visualizer);

        // Start the visualization thread
        new Thread(() -> PApplet.runSketch(new String[] { "Visualisation" }, visualizer)).start();

        // Execute sorting strategies
        this.sortAlgorithm.setData(data);
        for (SortStrategy strategy : this.sortStrategies) {
            this.sortAlgorithm.setStrategy(strategy);
            data.reset();
            sortAlgorithm.run();
        }
    }
}
