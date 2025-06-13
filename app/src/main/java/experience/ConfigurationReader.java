package experience;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads and parses a JSON configuration file containing multiple sorting experiment configurations.
 * This class uses Jackson to deserialize the JSON into Java objects.
 */
public class ConfigurationReader {

    private String jsonFile;
    private ConfigurationsWrapper wrapper;

    /**
     * Constructs a ConfigurationReader that reads configurations from the specified JSON file.
     *
     * @param jsonFile the path to the JSON file containing the configurations
     */
    public ConfigurationReader(String jsonFile) {
        this.jsonFile = jsonFile;

        ObjectMapper objectMapper = new ObjectMapper();

        // Load the JSON file and parse it into a ConfigurationsWrapper object
        try {
            wrapper = objectMapper.readValue(new File(this.jsonFile), ConfigurationsWrapper.class);
        } catch (IOException e) {
            // Print error details if the file cannot be read or parsed
            e.printStackTrace();
        }
    }

    /**
     * Retrieves the list of configurations from the parsed JSON file.
     * If the generator type is "SWAPS", it adjusts the x-values based on the array size.
     *
     * @return a list of {@link Configuration} objects
     */
    public List<Configuration> getConfigurations() {
        List<Configuration> configurations = new ArrayList<>();

        // Retrieve configurations from the wrapper
        configurations = wrapper.getConfigurations();

        // Process configurations based on their generator type
        for (Configuration config : configurations) {
            Generateur generator = config.getGenerator();
            switch (generator.getType()) {
                case SWAPS:
                    List<Number> xvalues = new ArrayList<>();
                    int size = config.getSize();

                    // Convert percentage-based values to absolute swap counts
                    for (Number percent : generator.getXvalues()) {
                        xvalues.add((int) ((percent.doubleValue() * size) / 200));
                    }

                    generator.setXvalues(xvalues);
                    break;
                
                default:
                    break;
            }
        }

        return configurations;
    }
}
