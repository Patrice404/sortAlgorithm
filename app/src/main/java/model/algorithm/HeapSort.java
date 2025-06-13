package model.algorithm;
import experience.*;
import model.Data;
import view.Visualizer;

public class HeapSort extends AbstractSortStrategy {

    public HeapSort( ) {
        super();
    }

    @Override
    public Sonde sort(Data array, Visualizer visualizer) {
        int size = array.getSize();
        double debut = System.currentTimeMillis();

        // Construction du max-heap (tas binaire max)
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(array, size, i, sonde, visualizer);
        }

        for (int i = size - 1; i > 0; i--) {
            array.swap(0, i);
            sonde.incrementSwaps();

            if(visualizer != null ){
                visualizer.update();
            }

            heapify(array, i, 0, sonde, visualizer);
        }

        double fin = System.currentTimeMillis();
        this.sonde.setTime((int)(fin-debut));
       return Sonde.copy(sonde);
    }

    // Fonction pour réajuster le tas (max-heapify)
    private void heapify(Data array, int size, int index, Sonde sonde, Visualizer visualizer) {

        int largest = index;
        int left = 2 * index + 1;   // Index du fils gauche
        int right = 2 * index + 2;  // Index du fils droit

        // Si le fils gauche est plus grand que la racine
        sonde.incrementAccess(2);
        sonde.incrementComparaison();
        if (left < size && array.getValueAt(left) > array.getValueAt(largest)) {
            largest = left;
        }

        // Si le fils droit est plus grand que la racine
        sonde.incrementAccess(2);
        sonde.incrementComparaison();
        if (right < size && array.getValueAt(right) > array.getValueAt(largest)) {
            largest = right;
        }

        // Si la racine n'est pas la plus grande, échanger
        if (largest != index) {
            array.swap(index, largest);
            sonde.incrementSwaps();

            if(visualizer != null ){
                visualizer.update();
            }
            
            heapify(array, size, largest, sonde, visualizer);
        }
    }

    @Override
    public String toString() {
        return "HeapSort";
    }
}

