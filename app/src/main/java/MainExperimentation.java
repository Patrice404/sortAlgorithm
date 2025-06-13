import experience.*;
public class MainExperimentation {
    public static void main(String[] args) {
        ConfigurationReader reader = new ConfigurationReader("config.json");
        for (Configuration config : reader.getConfigurations()) {
            Experiment experimentation = new Experiment(config);
            experimentation.execute();
        }
    }
}