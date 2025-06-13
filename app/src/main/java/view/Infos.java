package view;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * The {@code Infos} class represents a custom dialog box for displaying messages.
 * It provides an informative message along with a close button.
 */
public class Infos extends JDialog implements ActionListener {
    
    /** The close button to dismiss the dialog. */
    private Button closeButton;

    /**
     * Constructs an {@code Infos} dialog with a given message and status indicator.
     * 
     * @param father  The parent {@code JFrame} of this dialog.
     * @param title   The title of the dialog.
     * @param infos   The message to be displayed.
     * @param success If {@code true}, the text color is blue; otherwise, it is red.
     */
    public Infos(JFrame father, String title, String infos, boolean success) {
        super(father, title, true);

        this.setLayout(new BorderLayout(10, 10)); 
        this.setLocationRelativeTo(null);

        // Message panel
        JPanel messagePanel = new JPanel(new BorderLayout());
        messagePanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel infoLabel = new JLabel(infos, JLabel.CENTER); 
        infoLabel.setFont(InterfaceSetting.TEXT_FONT);
        infoLabel.setForeground(success ? Color.BLUE : Color.RED);

        messagePanel.add(infoLabel, BorderLayout.CENTER);
        this.add(messagePanel, BorderLayout.CENTER);

        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.closeButton = new Button("Close", 150, 50);
        this.closeButton.addActionListener(this);
        buttonPanel.add(closeButton);

        this.add(buttonPanel, BorderLayout.SOUTH);

        // Dialog settings
        this.setMinimumSize(new Dimension(300, 300));
        this.pack();
        this.setVisible(true);
    }

    /**
     * Handles button click events.
     * Closes the dialog when the close button is pressed.
     *
     * @param e The event triggered by the button click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.closeButton)) {
            this.dispose();
        }
    }
}
