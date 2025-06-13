package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SettingGenerator extends JFrame implements ActionListener {

    private Button closeButton;
    private Button submiButton;
    private JRadioButton distributiButton;
    private JRadioButton typeButton;
    private JRadioButton entropieButton;
    private JRadioButton swapButton;
    private String generatorType = null;

    public SettingGenerator() {
        super("Choix du programme générateur");

        Container container = this.getContentPane();

        container.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        this.closeButton = new Button("Close", 150, 50);
        this.closeButton.addActionListener(this);
        this.submiButton = new Button("Submit", 150, 50);
        this.submiButton.addActionListener(this);

        this.distributiButton = new JRadioButton("Distribution gaussienne");
        this.distributiButton.setFont(InterfaceSetting.BTN_FONT);

        this.typeButton = new JRadioButton("Type de tableau");
        this.typeButton.setFont(InterfaceSetting.BTN_FONT);

        this.entropieButton = new JRadioButton("Entropie");
        this.entropieButton.setFont(InterfaceSetting.BTN_FONT);

        this.swapButton = new JRadioButton("Swap");
        this.swapButton.setFont(InterfaceSetting.BTN_FONT);

        ButtonGroup group = new ButtonGroup();
        group.add(distributiButton);
        group.add(typeButton);
        group.add(entropieButton);
        group.add(swapButton);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(0, 100, 100));
        panel.add(closeButton);
        panel.add(submiButton);

        container.add(this.distributiButton);
        container.add(this.typeButton);
        container.add(this.entropieButton);
        container.add(this.swapButton);
        container.add(panel);

        this.pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.closeButton)) {
            this.dispose();
        } else {
            if (e.getSource().equals(this.submiButton)) {
                if (this.distributiButton.isSelected()) {
                    this.generatorType = "Distribution";
                } else if (this.typeButton.isSelected()) {
                    this.generatorType = "Type de tableau";
                } else if (this.entropieButton.isSelected()) {
                    this.generatorType = "Entropie";
                } else if (this.swapButton.isSelected()) {
                    this.generatorType = "Swap";
                } else {
                    new Infos(this, "Erreur de configuration", "Vous devez choisir un programme", false);
                    return;
                }
                if (this.generatorType == null) {
                    new Infos(this, "Erreur", "Vous devez choisir un programme", false);
                    return;
                }
                showGeneratorConfigView();
            }
        }
    }

    private void showGeneratorConfigView() {
        if (this.generatorType.equals("Distribution")) {
            new GaussienneConfiguration();
            this.dispose();
        } else if (this.generatorType.equals("Type de tableau")) {
            new ArrayTypeConfiguration();
            this.dispose();
        } else if (this.generatorType.equals("Entropie")) {
            new EntropieConfiguration();
            this.dispose();
        } else if (this.generatorType.equals("Swap")) {
            new SwapConfiguration();
            this.dispose();
        }
    }

}
