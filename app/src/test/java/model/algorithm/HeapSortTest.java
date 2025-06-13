package model.algorithm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import experience.Sonde;
import model.Data;

public class HeapSortTest {

    private Data data;

    private HeapSort heapsort;
    private SortAlgorithm sortAlgorithm;

    @BeforeEach
    void init() {
        heapsort = new HeapSort();
        sortAlgorithm = new SortAlgorithm();
        sortAlgorithm.setStrategy(heapsort);

        int array1[] = {10,4,2,14,8};

        data = new Data(array1);
    }

    @Test
    void testSort() {
        sortAlgorithm.setData(data);
        Sonde sonde = sortAlgorithm.run();
        Sonde sondeAttendu = new Sonde(22, 62, 9, 0); 
        assertTrue(sonde.equals(sondeAttendu), "Mauvais retour dans la mesure des sondes"+sonde);
    }
    
    @Test
    void testIsSorted() {
        sortAlgorithm.setData(data);
        sortAlgorithm.run();
        
        int[] array = data.getArray();
        for(int i = 1; i < array.length; i++){

            assertTrue(array[i-1] <= array[i], "Tableau non trié");
        }
        
    }
}
