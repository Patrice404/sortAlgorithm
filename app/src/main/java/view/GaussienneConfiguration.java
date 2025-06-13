package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.*;

public class GaussienneConfiguration extends JFrame implements ActionListener {

    private JTextField sizeTextField;
    private JTextField moyenneTextField;
    private JTextField ecartTypeTextField;
    private Button closeButton;
    private Button submiButton;

    public GaussienneConfiguration() {
        super("Configuration de la distribution");
        setContainer();
        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void setContainer() {
        Container container = this.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JLabel sizeLabel = new JLabel("Taille");
        sizeLabel.setFont(InterfaceSetting.LIST_FONT);
        this.sizeTextField = new JTextField(15);
        this.sizeTextField.setFont(InterfaceSetting.LIST_FONT);
        JPanel sizePanel = new JPanel();
        sizePanel.add(sizeLabel);
        sizePanel.add(this.sizeTextField);

        JLabel moyenneLabel = new JLabel("Moyenne");
        moyenneLabel.setFont(InterfaceSetting.LIST_FONT);
        this.moyenneTextField = new JTextField(15);
        this.moyenneTextField.setFont(InterfaceSetting.LIST_FONT);
        JPanel moyennePanel = new JPanel();
        moyennePanel.add(moyenneLabel);
        moyennePanel.add(this.moyenneTextField);

        JLabel ecartTypeLabel = new JLabel("Ecart type");
        ecartTypeLabel.setFont(InterfaceSetting.LIST_FONT);
        this.ecartTypeTextField = new JTextField(15);
        this.ecartTypeTextField.setFont(InterfaceSetting.LIST_FONT);
        JPanel ecartTypePanel = new JPanel();
        ecartTypePanel.add(ecartTypeLabel);
        ecartTypePanel.add(this.ecartTypeTextField);

        this.closeButton = new Button("Close", 150, 40);
        this.closeButton.addActionListener(this);
        this.submiButton = new Button("Submit", 150, 40);
        this.submiButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(0, 100, 10));
        buttonPanel.add(this.closeButton);
        buttonPanel.add(this.submiButton);

        container.add(sizePanel);
        container.add(moyennePanel);
        container.add(ecartTypePanel);
        container.add(buttonPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.closeButton) {
            this.dispose();
        } else if (e.getSource() == this.submiButton) {
            try {
                int size = Integer.parseInt(this.sizeTextField.getText());
                int moyenne = Integer.parseInt(this.moyenneTextField.getText());
                int ecartType = Integer.parseInt(this.ecartTypeTextField.getText());
                try {
                    Generator generator = new GaussienneGenerator(size, moyenne, ecartType);
                    SettingAlgo settingAlgoSonde = new SettingAlgo(generator);
                    this.dispose();
                } catch (Exception ex) {
                    new Infos(this, "Erreur de configuration", ex.getMessage(), false);
                return;
                }

            } catch (NumberFormatException ex) {
                new Infos(this, "Erreur de configuration", ex.getMessage(), false);
                return;
            }
        }
    }
}
