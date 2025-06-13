package view;

import processing.core.PApplet;
import model.Data;

/**
 * The Visualizer class graphically displays an array of integers as bars.
 * It uses Processing to create a graphical window and visually represent the data.
 */
public class Visualizer extends PApplet {
    private Data data; // Object containing the data to be visualized
    private int barWidth; // Width of the bars in the visualization
    private int[] array; // Array of values to be displayed

    /**
     * Constructor for the Visualizer class.
     * 
     * @param data The object containing the array of data to visualize.
     */
    public Visualizer(Data data) {
        this.data = data;
    }

    /**
     * Configuration method for the Processing window.
     * Sets the window size based on the array size.
     */
    @Override
    public void settings() {
        // array = data.getArray(); // Retrieve the array
        int preferredBarWidth = 3; // Width of each bar (adjustable)
        int windowWidth = this.data.getSize() * preferredBarWidth; // Total width based on the array
        int windowHeight = 600; // Fixed height or based on data.getMaxValue()
        size(windowWidth, windowHeight);
        size(InterfaceSetting.SCREEN_WIDTH - 300, InterfaceSetting.SCREEN_HEIGHT - 300);
    }

    /**
     * Initialization method called once at the beginning.
     * Configures initial parameters and prevents automatic redrawing.
     */
    @Override
    public void setup() {
        // Calculate bar width to fit the array size
        barWidth = width / this.data.getSize();
        noLoop(); // Draw only when an update is requested
    }

    /**
     * Drawing method called to refresh the display.
     * Displays the array as vertical bars.
     */
    @Override
    public void draw() {
        background(255); // Clear the screen before each drawing
        array = data.getArray(); // Retrieve the latest version of the array
        for (int i = 0; i < array.length; i++) {
            // Draw each bar
            int barHeight = (int) map(array[i], 0, data.getMaxValue(), 0, height);

            fill(11, 99, 0);
            rect(i * barWidth, height - barHeight, barWidth, barHeight);
        }
    }

    /**
     * Updates the display by forcing a redraw of the Processing window.
     * Includes a slight delay to control the refresh rate.
     */
    public void update() {
        redraw(); // Redraw the Processing window
        delay(50);
    }
}
