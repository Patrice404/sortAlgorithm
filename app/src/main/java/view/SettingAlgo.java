package view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;
import javax.swing.ListSelectionModel;

import model.Generator;
import model.algorithm.*;
import model.algorithm.TimSort;
import visualisation.DataSet;

public class SettingAlgo extends JFrame implements ActionListener {

    public final SortStrategy[] strategies = new SortStrategy[] { new Insertion(), new BubbleSort(), new MergeSort(),
            new QuickSort(), new HeapSort(), new TimSort() };

    private JList<String> sortAlgo;
    private Button closeButton;
    private Button submitButton;
    private Generator generator;

    public SettingAlgo(Generator generator) {
        super("Choix des algorithmes");
        this.generator = generator;
        makeInterface();
    }

    public void makeInterface() {
        CustomListCellRenderer cellRenderer = new CustomListCellRenderer();

        Container container = this.getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        JLabel algoLabel = new JLabel("Algorithmes");
        algoLabel.setFont(InterfaceSetting.TEXT_FONT);
        this.sortAlgo = new JList<>(InterfaceSetting.ALGO_NAMES);
        this.sortAlgo.setCellRenderer(cellRenderer);
        this.sortAlgo.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        this.sortAlgo.setVisibleRowCount(2);
        this.sortAlgo.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        this.sortAlgo.setFont(InterfaceSetting.TEXT_FONT);
        JPanel algoPanel = new JPanel();
        algoPanel.setLayout(new FlowLayout(10, 10, 10));
        algoPanel.add(algoLabel);
        algoPanel.add(this.sortAlgo);

        this.closeButton = new Button("Close", 150, 40);
        this.closeButton.addActionListener(this);
        this.submitButton = new Button("Submit", 150, 40);
        this.submitButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(0, 100, 10));
        buttonPanel.add(this.closeButton);
        buttonPanel.add(this.submitButton);

        container.add(algoPanel);
        container.add(buttonPanel);

        this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(this.closeButton)) {
            this.dispose();
        } else {
            if (e.getSource().equals(this.submitButton)) {
               
                int[] selectedAlgo = this.sortAlgo.getSelectedIndices();
                for (int i = 0; i < selectedAlgo.length; i++) {
                    System.out.println(InterfaceSetting.ALGO_NAMES[selectedAlgo[i]]);
                }
              
                List<SortStrategy> sortStrategies = new ArrayList<>();
                for (int i = 0; i < selectedAlgo.length; i++) {
                    sortStrategies.add(this.strategies[selectedAlgo[i]]);
                }
                
                DataSet dataSet = new DataSet(this.generator, 1, sortStrategies, this.sortAlgo.getSelectedValuesList());
                dataSet.play(true);
            }
        }
    }
}
