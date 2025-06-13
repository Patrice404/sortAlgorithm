package model.algorithm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import experience.Sonde;
import model.Data;

public class TimSortTest {

    private Data data;

    private TimSort timsort;
    private SortAlgorithm sortAlgorithm;

    @BeforeEach
    void init() {
        timsort = new TimSort();
        sortAlgorithm = new SortAlgorithm();
        sortAlgorithm.setStrategy(timsort);

        int array1[] = {10,4,2,14,8};

        data = new Data(array1);
    }

    @Test
    void testSort() {
        sortAlgorithm.setData(data);
        Sonde sonde = sortAlgorithm.run();
        Sonde sondeAttendu = new Sonde(9, 27, 0, 0); 
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
