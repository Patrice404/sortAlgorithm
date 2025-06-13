package model.algorithm;

import experience.*;
import model.Data;
import view.Visualizer;

public class MergeSort extends AbstractSortStrategy {
    public MergeSort() {
        super();
    }

    /**
     * Sorts the given data array using the merge sort algorithm and visualizes the
     * process.
     *
     * @param array      the data array to be sorted
     * @param visualizer the visualizer to visualize the sorting process
     * @return a copy of the Sonde object containing sorting statistics
     */
    @Override
    public Sonde sort(Data array, Visualizer visualizer) {

        this.sonde.reset();
        double debut = System.currentTimeMillis() / 1000.0;
        int size = array.getSize();

        mergeSort(array, 0, size - 1, sonde, visualizer);

        double fin = System.currentTimeMillis() / 1000.0;
        this.sonde.setTime((int) (fin - debut));
        return Sonde.copy(this.sonde);

    }

    private void mergeSort(Data array, int left, int right, Sonde sonde, Visualizer visualizer) {
        if (left < right) {

            // Find the middle point
            int mid = left + (right - left) / 2;

            // Sort first and second halves
            mergeSort(array, left, mid, sonde, visualizer);

            mergeSort(array, mid + 1, right, sonde, visualizer);

            // Merge the sorted halves
            merge(array, left, mid, right, sonde, visualizer);

        }

    }

    public static void merge(Data array, int left, int mid, int right, Sonde sonde, Visualizer visualizer) {
        // Calculer la taille des deux sous-tableaux
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Créer des tableaux temporaires pour stocker les deux sous-tableaux
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = array.getValueAt(left + i);
            sonde.incrementAccess();

        }

        for (int j = 0; j < n2; j++) {
            rightArray[j] = array.getValueAt(mid + 1 + j);
            sonde.incrementAccess();

        }

        // fusion
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {

            sonde.incrementComparaison();
            sonde.incrementAccess(2);
            if (leftArray[i] <= rightArray[j]) {

                array.setValueAt(k, leftArray[i]);
                sonde.incrementAccess(2);
                i++;
                if (visualizer != null) {
                    visualizer.update();
                }

            } else {
                array.setValueAt(k, rightArray[j]);
                sonde.incrementAccess(2);
                j++;
                if (visualizer != null) {
                    visualizer.update();
                }
            }

            k++;
        }

        while (i < n1) {
            array.setValueAt(k, leftArray[i]);
            sonde.incrementAccess(2);
            i++;
            k++;
            if (visualizer != null) {
                visualizer.update();
            }
        }

        while (j < n2) {
            array.setValueAt(k, rightArray[j]);
            sonde.incrementAccess(2);
            j++;
            k++;
            if (visualizer != null) {
                visualizer.update();
            }
        }
    }

    @Override
    public String toString() {
        return "MergeSort";
    }
}
