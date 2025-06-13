package view;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class CustomListCellRenderer implements ListCellRenderer<String> {
    @Override
    public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
        // Crée un label pour afficher l'élément
        JLabel label = new JLabel(value);
        label.setFont(InterfaceSetting.LIST_FONT);
        label.setOpaque(true); // Permet de peindre l'arrière-plan

        // Applique des couleurs selon l'état de sélection
        if (isSelected) {
            label.setBackground(list.getSelectionBackground());
            label.setForeground(list.getSelectionForeground());
        } else {
            label.setBackground(list.getBackground());
            label.setForeground(list.getForeground());
        }
        
        // Ajoute une bordure externe et interne
        Border outerBorder = BorderFactory.createLineBorder(Color.BLACK, 2); // Bordure externe
        Border innerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);   // Bordure interne (espacement)
        label.setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        return label;
    }

}