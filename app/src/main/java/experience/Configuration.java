package experience;

import java.util.List;

import model.GeneratorType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents a configuration for an experiment, specifying the dataset size,
 * number of arrays, and the generator settings.
 * <p>
 * This class is designed to be deserialized from a JSON configuration file.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Configuration {
    private int size;
    private int arraysNumber;
    private Generateur generator;

    /**
     * Gets the size of the arrays to be generated.
     *
     * @return the size of each array
     */
    public int getSize() {
        return size;
    }

    /**
     * Gets the number of arrays to be generated.
     *
     * @return the number of arrays
     */
    public int getArraysNumber() {
        return arraysNumber;
    }

    /**
     * Gets the generator settings used to create the arrays.
     *
     * @return the generator configuration
     */
    public Generateur getGenerator() {
        return generator;
    }

    /**
     * Returns a string representation of the configuration.
     *
     * @return a formatted string containing configuration details
     */
    @Override
    public String toString() {
        return "Configuration{" +
                "generator='" + generator + '\'' +
                ", size=" + size +
                ", arraysNumber=" + arraysNumber +
                '}';
    }
}

/**
 * Represents a data generator used for creating datasets with specific properties.
 * It defines the type of generator and optional parameters.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class Generateur {
    private GeneratorType type;
    private List<Number> xvalues; // May be null for certain generator types

    /**
     * Gets the type of generator.
     *
     * @return the generator type
     */
    public GeneratorType getType() {
        return type;
    }

    /**
     * Sets the list of numerical parameters for the generator.
     *
     * @param xvalues a list of values, which may vary depending on the generator type
     */
    public void setXvalues(List<Number> xvalues) {
        this.xvalues = xvalues;
    }

    /**
     * Gets the list of numerical parameters associated with the generator.
     *
     * @return a list of values, or null if not applicable
     */
    public List<Number> getXvalues() {
        return xvalues;
    }

    /**
     * Returns a string representation of the generator configuration.
     *
     * @return a formatted string containing generator details
     */
    @Override
    public String toString() {
        return "Generator [type=" + type + ", xvalues=" + xvalues + "]";
    }
}
