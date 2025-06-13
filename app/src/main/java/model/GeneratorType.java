package model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Objects;

/**
 * Enum representing different types of data generators.
 * This is used to specify the method of generating datasets for sorting algorithms.
 */
public enum GeneratorType {
    SHANNON_ENTROPY("SHANNON_ENTROPY"),
    EQUIPROBABILITY("EQUIPROBABILITY"),
    RANDOM("RANDOM"),
    GAUSSIAN("GAUSSIAN"),
    SWAPS("SWAPS");

    private final String value;

    /**
     * Constructor for GeneratorType enum.
     *
     * @param value the string representation of the generator type
     */
    GeneratorType(String value) {
        this.value = value;
    }

    /**
     * Returns the string representation of the generator type.
     * This method is used during JSON serialization.
     *
     * @return the string value associated with the enum constant
     */
    @JsonValue
    public String getValue() {
        return value;
    }

    /**
     * Converts a string value to the corresponding GeneratorType.
     * This method is used during JSON deserialization.
     *
     * @param value the string representation of the generator type
     * @return the matching GeneratorType enum constant
     * @throws IllegalArgumentException if the value does not match any known type
     */
    @JsonCreator
    public static GeneratorType fromString(String value) {
        Objects.requireNonNull(value, "Generator type cannot be null");
        for (GeneratorType type : GeneratorType.values()) {
            if (type.value.equalsIgnoreCase(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown generator type: " + value);
    }
}
