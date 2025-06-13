package experience;

import java.util.List;

/**
 * A wrapper class for holding a list of sorting experiment configurations.
 * This class is primarily used for deserializing JSON data that contains multiple configurations.
 */
public class ConfigurationsWrapper {
    private List<Configuration> configurations;

    /**
     * Gets the list of configurations.
     *
     * @return a list of {@link Configuration} objects
     */
    public List<Configuration> getConfigurations() {
        return configurations;
    }

    /**
     * Sets the list of configurations.
     *
     * @param configurations a list of {@link Configuration} objects to be stored
     */
    public void setConfigurations(List<Configuration> configurations) {
        this.configurations = configurations;
    }

    /**
     * Returns a string representation of the wrapper object.
     *
     * @return a formatted string containing the list of configurations
     */
    @Override
    public String toString() {
        return "ConfigurationsWrapper{" +
                "configurations=" + configurations +
                '}';
    }
}
