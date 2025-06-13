package view;

import java.awt.*;

/**
 * The InterfaceSetting class defines global settings for the graphical user interface.
 * It includes screen dimensions, font styles, and predefined algorithm and probe options.
 */
public class InterfaceSetting {

    /**
     * The screen size of the user's display.
     */
    private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     * The width of the screen in pixels.
     */
    public static int SCREEN_WIDTH = screenSize.width;

    /**
     * The height of the screen in pixels.
     */
    public static int SCREEN_HEIGHT = screenSize.height;

    /**
     * The default font for buttons.
     */
    public static Font BTN_FONT = new Font("Arial", Font.BOLD, 25);

    /**
     * The default font for titles.
     */
    public static Font TITLE_FONT = new Font("Arial", Font.BOLD, 30);

    /**
     * The default font for general text.
     */
    public static Font TEXT_FONT = new Font("Arial", Font.BOLD, 20);

    /**
     * The default font for lists.
     */
    public static Font LIST_FONT = new Font("Arial", Font.BOLD, 15);

    /**
     * The names of the sorting algorithms available for selection.
     */
    public static String[] ALGO_NAMES = new String[] {
        "Insertion", "BubbleSort", "MergeSort", "QuickSort", "HeapSort", "TimSort"
    };

    /**
     * The different probe options available for analysis.
     */
    public static String[] SONDE_OPTIONS = new String[] {
        "Comparaison", "Echange", "Temps", "Acces"
    };
}
