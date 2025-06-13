package model.algorithm;

import experience.*;
import model.Data;
import view.Visualizer;

public class Insertion  extends AbstractSortStrategy {

    public Insertion() {
        super();
    }

    @Override
    public Sonde sort(Data array, Visualizer visualizer) {

        this.sonde.reset();
        double debut = System.currentTimeMillis();
        int size = array.getSize();

        for (int i = 1; i < size; i++) {
            int key = array.getValueAt(i); // L'élément à insérer dans la sous-liste triée
            sonde.incrementAccess(); // Incrémenter l'accès pour l'insertion
            int j = i - 1;

            while (j >= 0 && array.getValueAt(j) > key) {

                sonde.incrementAccess();
                sonde.incrementComparaison();

                array.setValueAt(j+1, array.getValueAt(j));
                sonde.incrementAccess(2);

                j--;

                if(visualizer != null ){
                    visualizer.update();
                }
            }

            sonde.incrementComparaison();
            sonde.incrementAccess();

            array.setValueAt(j+1, key);
            sonde.incrementAccess(1);

            if(visualizer != null ){
                visualizer.update();
            }
        }

        double fin = System.currentTimeMillis();
        this.sonde.setTime((int)(fin-debut));

        return Sonde.copy(this.sonde);

    }

    @Override
    public String toString() {
        return "Insertion";
    }

   
}
