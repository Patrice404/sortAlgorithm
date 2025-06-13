package view;

import java.awt.*;
import javax.swing.*;

/**
 * The Button class extends JButton to create a custom-styled button
 * with rounded corners and a dynamic background color effect.
 */
public class Button extends JButton {

    /**
     * Constructs a custom button with a specified name, width, and height.
     * 
     * @param name   The text to display on the button.
     * @param width  The preferred width of the button.
     * @param height The preferred height of the button.
     */
    public Button(String name, int width, int height) {
        super(name);
        // Disable default Swing styling for a custom appearance
        setFocusPainted(false);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
        // Apply styling
        this.setFont(new Font("Arial", Font.BOLD, 25));
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.LIGHT_GRAY);
        this.setForeground(Color.BLACK); // Text color
        this.setBorder(BorderFactory.createEmptyBorder()); // No default border
    }

    /**
     * Custom painting method to render a rounded button with dynamic colors.
     * Adjusts the background color based on the button state (pressed, hovered, default).
     * 
     * @param g The Graphics object used for rendering.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Change background color based on button state
        if (getModel().isPressed()) {
            g2.setColor(getBackground().darker());
        } else if (getModel().isRollover()) {
            g2.setColor(new Color(76, 175, 80)); // Green when hovered
        } else {
            g2.setColor(getBackground());
        }

        int arc = 20; // Corner arc for a rounded design
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

        // Draw border
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(3)); // Border thickness
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);

        g2.dispose();

        super.paintComponent(g);
    }
}
