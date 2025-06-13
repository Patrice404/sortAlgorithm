package view;

import java.awt.*;
import model.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwapConfiguration extends JFrame implements ActionListener{

    private JTextField sizeTextField;
    private JTextField swapTextField;
    private Button closeButton;
    private Button submitButton;

    public SwapConfiguration() {
        super("Paramètres du nombre d'échanges");

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

        JLabel entropieLabel = new JLabel("Nombre d'échanges");
        entropieLabel.setFont(InterfaceSetting.LIST_FONT);
        this.swapTextField = new JTextField(15);
        this.swapTextField.setFont(InterfaceSetting.LIST_FONT);
        JPanel entropiePanel = new JPanel();
        entropiePanel.add(entropieLabel);
        entropiePanel.add(this.swapTextField);

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
                int nbSwap = Integer.parseInt(this.swapTextField.getText());
                if(taille <2){
                    new Infos(this, "Erreur de configuration", "La valeur de la taille doit être minimum 2", false);
                    return;
                }
                if (nbSwap < 0) {
                    new Infos(this, "Erreur de configuration", "Le nombre d'échange n'est invalide", false);
                    return;
                }
                Generator generator = new SwapGenerator(taille, nbSwap);
                SettingAlgo settingAlgoSonde = new SettingAlgo(generator);
                this.dispose();
            } catch (Exception ex) {
                new Infos(this, "Erreur de configuration", "Une des données entrées est invalide", false);
                return;
            }
        }

    }

}
