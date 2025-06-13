package experience;

import model.Generator;

/**
 * The {@code Result} class represents the result of an experiment.
 * It contains the name of the algorithm used, the generator, and the sonde.
 * 
 * <p>
 * This class provides methods to retrieve the algorithm name, generator, and
 * sonde.
 * It also overrides the {@code toString} method to provide a string
 * representation of the result.
 * 
 * @see Generator
 * @see Sonde
 */

public class Result {

    private Generator generator;
    private Sonde sonde;
    private String algoName;

    /**
     * Constructs a new Result with the specified algorithm name, sonde, and
     * generator.
     *
     * @param algoName  the name of the algorithm used in the experiment
     * @param sonde     the sonde used in the experiment
     * @param generator the generator used in the experiment
     */
    public Result(String algoName, Sonde sonde, Generator generator) {
        this.algoName = algoName;
        this.generator = generator;
        this.sonde = sonde;
    }

    /**
     * Retrieves the generator used in the experiment.
     *
     * @return the generator used in the experiment
     */
    public Generator getGenerator() {
        return generator;
    }

    /**
     * Retrieves the sonde used in the experiment.
     *
     * @return the sonde used in the experiment
     */
    public Sonde getSonde() {
        return sonde;
    }

    /**
     * Retrieves the name of the algorithm used in the experiment.
     *
     * @return the name of the algorithm used in the experiment
     */
    public String getAlgoName() {
        return algoName;
    }

    /**
     * Returns a string representation of the result.
     * The string representation consists of the algorithm name, generator, and
     * sonde.
     *
     * @return a string representation of the result
     */
    @Override
    public String toString() {
        return this.algoName + " " + this.generator + " " + this.sonde;
    }

}
