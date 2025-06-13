package model.algorithm;

import experience.Sonde;
import model.Data;
import view.Visualizer;

public class TimSort extends AbstractSortStrategy {
    static int MIN_MERGE = 32;

    public TimSort(){
        super();
    }


    public static int minRunLength(int n) 
    { 
        assert n >= 0; 
  
        // Becomes 1 if any 1 bits are shifted off 
        int r = 0; 
        while (n >= MIN_MERGE) { 
            r |= (n & 1); 
            n >>= 1; 
        } 
        return n + r; 
    }

    // This function sorts array from left index to 
    // to right index which is of size atmost RUN 
    public static void insertionSort(Data array, int left, int right, Sonde sonde, Visualizer visualizer) 
    { 
        for (int i = left + 1; i <= right; i++) { 
            int temp = array.getValueAt(i);
            sonde.incrementAccess();

            int j = i - 1; 
            while (j >= left && array.getValueAt(j) > temp) { 
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

            array.setValueAt(j+1, temp);
            sonde.incrementAccess(1);

            if(visualizer != null ){
                visualizer.update();
            }

        } 
    }

    // Iterative Timsort function to sort the 
    // array[0...n-1] (similar to merge sort) 
    public static void timSort(Data array, int n, Sonde sonde, Visualizer visualizer) 
    { 
        int minRun = minRunLength(MIN_MERGE); 
  
        // Sort individual subarrays of size RUN 
        for (int i = 0; i < n; i += minRun) { 
            insertionSort( 
                array, i, 
                Math.min((i + MIN_MERGE - 1), (n - 1)), sonde, visualizer); 
        } 
  
        // Start merging from size 
        // RUN (or 32). It will 
        // merge to form size 64, 
        // then 128, 256 and so on 
        // .... 
        for (int size = minRun; size < n; size = 2 * size) { 
  
            // Pick starting point 
            // of left sub array. We 
            // are going to merge 
            // arr[left..left+size-1] 
            // and arr[left+size, left+2*size-1] 
            // After every merge, we 
            // increase left by 2*size 
            for (int left = 0; left < n; left += 2 * size) { 
  
                // Find ending point of left sub array 
                // mid+1 is starting point of right sub 
                // array 
                int mid = left + size - 1; 
                int right = Math.min((left + 2 * size - 1), 
                                     (n - 1)); 
  
                // Merge sub array arr[left.....mid] & 
                // arr[mid+1....right] 
                if (mid < right) MergeSort.merge(array, left, mid, right, sonde, visualizer);
            } 
        } 
    }

    @Override
    public Sonde sort(Data array, Visualizer visualizer) {
        this.sonde.reset();
        double debut = System.currentTimeMillis() / 1000.0;
        int size = array.getSize();

        timSort(array, size, sonde, visualizer);

        double fin = System.currentTimeMillis() / 1000.0;
        this.sonde.setTime((int)(fin-debut));

        return Sonde.copy(this.sonde);
    }

    @Override
    public String toString() {
        return "Tim Sort";
    }

}
