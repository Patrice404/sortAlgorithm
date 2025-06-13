package model.algorithm;

import experience.Sonde;
import model.Data;
import view.Visualizer;

public class BubbleSort extends AbstractSortStrategy {
    public BubbleSort() {
        super();
    }

    @Override
    public Sonde sort(Data array, Visualizer visualizer) {
        
        this.sonde.reset();
        double debut = System.currentTimeMillis();
        int size = array.getSize();
        boolean swapped;

        for (int i = 0; i < size - 1; i++) {

            swapped = false;

            for (int j = 0; j < size - 1 - i; j++) {

                this.sonde.incrementComparaison();
                
                // one time for array[j]
                this.sonde.incrementAccess();

                // one time for array[j+1]
                this.sonde.incrementAccess();

                if (array.getValueAt(j) > array.getValueAt(j + 1)) {
                    array.swap(j, j + 1);
                    this.sonde.incrementSwaps();
                    swapped = true;
                    if(visualizer != null ){
                        visualizer.update();
                    }
                }

            }

            if (!swapped) {
                break;
            }
        }

        double fin = System.currentTimeMillis() ;
        this.sonde.setTime((int)(fin-debut));
        return Sonde.copy(this.sonde);
    }

    @Override
    public String toString() {
        return "BubbleSort";
    }
}
