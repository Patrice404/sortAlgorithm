package model.algorithm;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import experience.Sonde;
import model.Data;

public class BubbleSortTest {

    private Data data1;
    private Data data2;
    private Data data3;

    private BubbleSort bubbleSort;
    private SortAlgorithm sortAlgorithm;

    @BeforeEach
    void init() {
        bubbleSort = new BubbleSort();
        sortAlgorithm = new SortAlgorithm();
        sortAlgorithm.setStrategy(bubbleSort);

        int array1[] = {10,4,2,14,8};
        int array2[] = {4,2,8,1,9,6,7,3,5,10};
        int array3[] = {16,2,18,1,9,6,14,3,5,10,19,12,13,7,15,4,17,8,11,20};

        data1 = new Data(array1);
        data2 = new Data(array2);
        data3 = new Data(array3);
    }
    
    @Test
    void testSort() {
        sortAlgorithm.setData(data1);
        Sonde sonde = sortAlgorithm.run();
        Sonde sondeAttendu = new Sonde(9, 28, 5, 0); 
        assertTrue(sonde.equals(sondeAttendu), "Mauvais retour dans la mesure des sondes"+sonde);

        sortAlgorithm.setData(data2);
        sonde = sortAlgorithm.run();
        sondeAttendu = new Sonde(39, 112, 17, 0);
        assertTrue(sonde.equals(sondeAttendu), "Mauvais retour dans la mesure des sondes");

        sortAlgorithm.setData(data3);
        sonde = sortAlgorithm.run();
        sondeAttendu = new Sonde(169, 488, 75, 0);
        assertTrue(sonde.equals(sondeAttendu), "Mauvais retour dans la mesure des sondes");
    }

    @Test
    void testIsSorted() {
        sortAlgorithm.setData(data1);
        sortAlgorithm.run();
        
        int[] array = data1.getArray();
        for(int i = 1; i < array.length; i++){

            assertTrue(array[i-1] <= array[i], "Tableau non trié");
        }
        
    }
}
