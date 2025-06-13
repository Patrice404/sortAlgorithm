package experience;

import java.util.ArrayList;
import java.util.List;

import model.*;
import model.algorithm.*;

/**
 * The Experiment class is responsible for running sorting experiments using different sorting strategies.
 * It generates data based on a given configuration, executes the sorting algorithms, and collects the results.
 * The results are then used to build graphs for analysis.
 */
public class Experiment {

    /**
     * Array of names for the different probes used in the experiment.
     */
    private static final String[] SONDE_NAME = new String[] { "Comparaisons", "Acces", "Echanges"};

    /**
     * Array of sorting strategies to be tested in the experiment.
     */
        public final SortStrategy[] strategies = new SortStrategy[] { new Insertion(), new BubbleSort(), new MergeSort(),
            new QuickSort(), new HeapSort(), new TimSort() };

    /**
     * Configuration object containing the settings for the experiment.
     */
    private Configuration configuration;

    /**
     * Generator object used to generate data for the experiment.
     */
    private Generator generator;

    /**
     * SortAlgorithm object used to execute the sorting strategies.
     */
    private SortAlgorithm sortAlgorithm;

    /**
     * List to store the average results of the experiments.
     */
    private List<Result> resultsMoyenne;

    /**
     * String representing the x-axis label for the graph.
     */
    private String abscisse;

    private String generatorName;

    /**
     * Constructor for the Experiment class.
     * Initializes the generator and checks for a valid configuration.
     *
     * @param configuration The configuration object containing the settings for the experiment.
     * @throws IllegalArgumentException if the configuration is invalid.
     */
    public Experiment(Configuration configuration) throws IllegalArgumentException {
        this.configuration = configuration;
        initGenerator();
        if (this.generator == null) {
            throw new IllegalArgumentException("Invalide configuration");
        }
        this.sortAlgorithm = new SortAlgorithm();
        this.resultsMoyenne = new ArrayList<>();
    }

    /**
     * Initializes the data generator based on the configuration.
     */
    private void initGenerator() {
        switch (configuration.getGenerator().getType()) {
            case SWAPS:
                generator = new SwapGenerator(3, 0);
                this.abscisse = "Pourcentage_Echanges";
                this.generatorName = "SwapGenerator";
                break;
            case SHANNON_ENTROPY:
                try {
                    generator = new EntropyGenerator(3, 0);
                    this.abscisse = "Entropie";
                    this.generatorName = "EntropyGenerator";
                    break;
                } catch (Exception e) {
                    System.out.println("Type de générateur inconnu");
                }
            default:
                generator = null;
                break;
        }
    }

    /**
     * Executes the sorting experiments and collects the results.
     * The results are averaged and stored for graph construction.
     */
    public void execute() {
        List<Result> results = new ArrayList<>();
        Data data = null;
        generator.setSize(configuration.getSize());
        // We vary the generation variable (size, number of swaps, entropy)
        for (Number abscisse : this.configuration.getGenerator().getXvalues()) {
        //    System.out.println("Xvalue = " + abscisse); 
            generator.setVariable(abscisse);
            for (SortStrategy strategy : strategies) {
                results.clear();
                sortAlgorithm.setStrategy(strategy);

                // Repeat the experiment multiple times for each strategy
                for (int i = 0; i < configuration.getArraysNumber(); i++) {
                    data = new Data(generator.generate());
                    sortAlgorithm.setData(data);
                    Sonde sonde = sortAlgorithm.run();
                    Result r = new Result(strategy.toString(), sonde, generator.copy());
                    results.add(r);
                }
                resultsMoyenne.add(calculMoyenne(results));
            }
        }
        builtGraph();
    }

    /**
     * Builds the graph based on the collected results.
     * The graph shows the performance of different sorting strategies.
     */
    public void builtGraph() {
        List<Number> yValues = new ArrayList<>();

        for (String sondeName : SONDE_NAME) {
            GraphConstructor graphConstructor = new GraphConstructor(this.generatorName, this.generator.getSize(), this.abscisse, sondeName);

            for (SortStrategy strategy : strategies) {
                yValues.clear();
                for (Number xvalue : this.configuration.getGenerator().getXvalues()) {
                    // System.out.println("xvalue -- " + xvalue);
                    for (Result result : this.resultsMoyenne) {
                        if (result.getAlgoName().equals(strategy.toString()) && (result.getGenerator().getVariable().equals(xvalue) || xvalue.doubleValue() ==0.0 && result.getGenerator().getVariable().doubleValue() ==0.0) ) {
                            switch (sondeName) {
                                case "Comparaisons":
                                    yValues.add(result.getSonde().getNbComparaisons());
                                    // System.out.println("Comparaisons = " + "xvalue = " + xvalue);
                                    break;
                                case "Acces":
                                    yValues.add(result.getSonde().getNbAccess());
                                    //System.out.println("Acces = " + "xvalue = " + xvalue);
                                    break;
                                case "Echanges":
                                    yValues.add(result.getSonde().getNbEchanges());
                                    //System.out.println("Echanges = " + "xvalue = " + xvalue);
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }

                graphConstructor.addPlot(this.configuration.getGenerator().getXvalues(), yValues, strategy.toString());
            }
            graphConstructor.generatePlot();
        }
    }

    /**
     * Calculates the average result from a list of results.
     *
     * @param results The list of results to average.
     * @return The average result.
     */
    private Result calculMoyenne(List<Result> results) {
        long size = results.size();
        long nbComp = 0;
        long nbSwap = 0;
        long time = 0;
        long access = 0;
        for (Result result : results) {
            nbComp += result.getSonde().getNbComparaisons();
            nbSwap += result.getSonde().getNbEchanges();
            time += result.getSonde().getTime();
            access += result.getSonde().getNbAccess();
        }
        return new Result(results.get(0).getAlgoName(),
                new Sonde(nbComp / size, access / size, nbSwap / size, time / size), results.get(0).getGenerator());
    }
}
