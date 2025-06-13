package view;

import java.awt.*;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.*;
import javax.swing.JTextField;

import model.EntropyGenerator;
import model.Generator;

public class EntropieConfiguration extends JFrame implements ActionListener {

    private JTextField sizeTextField;
    private JTextField entropieTextField;
    private Button closeButton;
    private Button submitButton;
    
    public EntropieConfiguration() {
        super("Paramètres d'Entropie");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JLabel sizeLabel = new JLabel("Taille");
        sizeLabel.setFont(InterfaceSetting.LIST_FONT);
        this.sizeTextField = new JTextField(15);
        this.sizeTextField.setFont(InterfaceSetting.LIST_FONT);
        JPanel sizePanel = new JPanel();
        sizePanel.add(sizeLabel);
        sizePanel.add(this.sizeTextField);

        JLabel entropieLabel = new JLabel("Entropie");
        entropieLabel.setFont(InterfaceSetting.LIST_FONT);
        this.entropieTextField = new JTextField(15);
        this.entropieTextField.setFont(InterfaceSetting.LIST_FONT);
        JPanel entropiePanel = new JPanel();
        entropiePanel.add(entropieLabel);
        entropiePanel.add(this.entropieTextField);

        this.closeButton = new Button("Close", 150, 40);
        this.closeButton.addActionListener(this);
        this.submitButton = new Button("Submit", 150, 40);
        this.submitButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(0, 100, 10));
        buttonPanel.add(this.closeButton);
        buttonPanel.add(this.submitButton);

        container.add(sizePanel);
        container.add(entropiePanel);
        container.add(buttonPanel);
        
        this.pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.closeButton) {
            this.dispose();
        } else if (e.getSource() == this.submitButton) {
            try {
                int taille = Integer.parseInt(this.sizeTextField.getText());
                double entropie = Double.parseDouble(this.entropieTextField.getText());
                if(taille <2){
                    new Infos(this, "Erreur de configuration", "La valeur de la taille doit être minimum 2", false);
                    return;
                }
                if (entropie < 0 || entropie > 1) {
                    new Infos(this, "Erreur de configuration", "La valeur de l'entropie est invalide", false);
                    return;
                }
                Generator generator = new EntropyGenerator(taille, entropie);
                SettingAlgo settingAlgoSonde = new SettingAlgo(generator);
                this.dispose();
            } catch (Exception ex) {
                new Infos(this, "Erreur de configuration", "Une des données entrées est invalide", false);
                return;
            }
        }

    }
}
