package model.algorithm;

import experience.*;
import model.Data;
import view.Visualizer;

public class QuickSort  extends AbstractSortStrategy  {
    public QuickSort() {
        super();
    }

    @Override
    public Sonde sort(Data array,Visualizer visualizer) {

        this.sonde.reset();
        double debut = System.currentTimeMillis() / 1000.0;
        int size = array.getSize();

        quickSort(array, 0, size - 1,sonde, visualizer);

        double fin = System.currentTimeMillis() / 1000.0;
        this.sonde.setTime((int)(fin-debut));

        return Sonde.copy(this.sonde);
    }

    private void quickSort(Data array, int left, int right,Sonde sonde, Visualizer visualizer) {

        int size = right - left + 1;
        if (size > 3 ) {

            int median = medianOf3(array, left, right, sonde, visualizer);    // determine partition
            int pivotIndex = partition(array, left, right, median, sonde, visualizer);

            // Recursion calls for smaller elements
            // and greater or equals elements
            quickSort(array, left, pivotIndex - 1, sonde, visualizer);
            quickSort(array, pivotIndex + 1, right, sonde, visualizer);

        }
        else {
            simpleSort(array, left, right, sonde, visualizer);
        } 

    }

    /* * * * * * * * * * * * * * * * * * * * * * * * *
      *  medianOf3 used to determine the location of
      *  the pivot used for recursiveQuicksort.
      * * * * * * * * * * * * * * * * * * * * * * * * */
    private int medianOf3(Data array, int left, int right, Sonde sonde, Visualizer visualizer) {
        int center = (left + right) / 2;

        if (array.getValueAt(left) > array.getValueAt(center)){
            array.swap(left, center);
            sonde.incrementSwaps();
            if(visualizer != null ){
                visualizer.update();
            }
        }
        sonde.incrementComparaison();
        sonde.incrementAccess();
        sonde.incrementAccess();
           

        if (array.getValueAt(left) > array.getValueAt(right)){
            array.swap(left, right);
            sonde.incrementSwaps();
            if(visualizer != null ){
                visualizer.update();
            }
        }
        sonde.incrementComparaison();
        sonde.incrementAccess();
        sonde.incrementAccess();
            

        if (array.getValueAt(center) > array.getValueAt(right)){
            array.swap(center, right);
            sonde.incrementSwaps();
            if(visualizer != null ){
                visualizer.update();
            }
        }
        sonde.incrementComparaison();
        sonde.incrementAccess();
        sonde.incrementAccess();

        array.swap(center, right - 1);    // move the pivot to right
        sonde.incrementSwaps();
        if(visualizer != null ){
            visualizer.update();
        }

        int localMedian = array.getValueAt(right - 1);     // return pointer to location of median
        sonde.incrementAccess();

        return localMedian;
    }

    private int partition(Data array, int left, int right, int median, Sonde sonde, Visualizer visualizer) {

        int leftIndex = left;
        int rightIndex = right-1;

        while(true){

            while (array.getValueAt(++leftIndex) < median){
                // scan for larger int
                sonde.incrementComparaison();
                sonde.incrementAccess();
            }
            sonde.incrementComparaison();
            sonde.incrementAccess();
            
            while (array.getValueAt(--rightIndex) > median) {
                // scan for smaller int

                sonde.incrementComparaison();
                sonde.incrementAccess();
            }
            sonde.incrementComparaison();
            sonde.incrementAccess();

            if (leftIndex >= rightIndex) // pointers crossed: break
                break;
            else{
                array.swap(leftIndex, rightIndex); // pointers never crossed: swap elements
                sonde.incrementSwaps();

                if(visualizer != null ){
                    visualizer.update();
                }
            }
                
        }

        array.swap(leftIndex, right-1); // restore pivot
        sonde.incrementSwaps();

        if(visualizer != null ){
            visualizer.update();
        }

        return leftIndex; // return pivot location
    }

    
    private void simpleSort(Data array, int left, int right, Sonde sonde, Visualizer visualizer) {
        int size = right - left + 1;
        if (size <= 1)
            return; // don't bother
        if (size == 2) {
            sonde.incrementAccess();
            sonde.incrementAccess();
            sonde.incrementComparaison();
            if (array.getValueAt(left) > array.getValueAt(right)){
                array.swap(left, right);
                sonde.incrementSwaps();
            }
                
            return;
        } else {
            sonde.incrementAccess();
            sonde.incrementAccess();
            sonde.incrementComparaison();
            if (array.getValueAt(left) > array.getValueAt(right - 1)){
                array.swap(left, right - 1);
                sonde.incrementSwaps();
            }
                

            sonde.incrementAccess();
            sonde.incrementAccess();
            sonde.incrementComparaison();
            if (array.getValueAt(left) > array.getValueAt(right)){
                array.swap(left, right);
                sonde.incrementSwaps();
            }
                

            sonde.incrementAccess();
            sonde.incrementAccess();
            sonde.incrementComparaison();
            if (array.getValueAt(right - 1) > array.getValueAt(right)){
                array.swap(right - 1, right);
                sonde.incrementSwaps();
            }
                
        }
    }
    
    
    @Override
    public String toString() {
        return "QuickSort";
    }
}
