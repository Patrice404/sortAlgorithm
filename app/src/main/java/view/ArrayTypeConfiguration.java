package view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import model.*;

public class ArrayTypeConfiguration extends JFrame implements ActionListener {

    private JTextField sizeTextField;
    private JComboBox<String> arrayType;
    private Map<String, ArrayType> typesMap;
    private Vector<String> arrayTypeOptions;
    private Button closeButton;
    private Button submiButton;

    public ArrayTypeConfiguration() {
        super("Configuration du tableau");
        setTypes();
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

        JLabel typeLabel = new JLabel("Type");
        typeLabel.setFont(InterfaceSetting.LIST_FONT);
        this.arrayType = new JComboBox<>(this.arrayTypeOptions);
        this.arrayType.setFont(InterfaceSetting.LIST_FONT);
        JPanel typePanel = new JPanel();
        typePanel.add(typeLabel);
        typePanel.add(this.arrayType);

        this.closeButton = new Button("Close", 150, 40);
        this.closeButton.addActionListener(this);
        this.submiButton = new Button("Submit", 150, 40);
        this.submiButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(0, 100, 10));
        buttonPanel.add(this.closeButton);
        buttonPanel.add(this.submiButton);

        container.add(sizePanel);
        container.add(typePanel);
        container.add(buttonPanel);
    }

    private void setTypes() {

        this.typesMap = new HashMap<>();
        this.typesMap.put("90% trié et 10% mélangé", ArrayType.LOW_1);
        this.typesMap.put("25% aléatoire - 50% trié - 25% aléatoire", ArrayType.MEDIUM_1);
        this.typesMap.put("50% trié  - 50% aléatoire", ArrayType.MEDIUM_2);
        this.typesMap.put("50% aléatoire  - 50% trié", ArrayType.MEDIUM_3);
        this.typesMap.put("25% trié - 50% aléatoire  - 25% trié", ArrayType.MEDIUM_4);
        this.typesMap.put("Tableau déjà trié", ArrayType.LOW_2);
        this.typesMap.put("Tableau totalement mélangé", ArrayType.HIGHT_1);
        this.typesMap.put("Tableau inversé", ArrayType.HIGHT_2);

        this.arrayTypeOptions = new Vector<>();

        for (Map.Entry<String, ArrayType> entry : this.typesMap.entrySet()) {
            this.arrayTypeOptions.add(entry.getKey());
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.closeButton)) {
            this.dispose();
        } else {
            if (e.getSource().equals(this.submiButton)) {
                checkEntry();
            }
        }
    }

    private void checkEntry() {
        try {
            int size = Integer.parseInt(this.sizeTextField.getText());
            if (size < 2) {
                new Infos(this, "Erreur de configuration", "La taille doit être supérieur ou égale à 2", false);
                return;
            }
            String type = (String) this.arrayType.getSelectedItem();
            ArrayType arrayType = this.typesMap.get(type);
            ArrayTypeGenerator arrayTypeGenerator =  new ArrayTypeGenerator(size, arrayType);
            SettingAlgo settingAlgoSonde = new SettingAlgo(arrayTypeGenerator);
            this.dispose();
        } catch (NumberFormatException e) {
            System.out.println("La taille doit être un entier positif");
        }
    }
}
