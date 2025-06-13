package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataTest {

    private Data data;

    @BeforeEach
    void setUp() {
        int[] initialArray = { 5, 3, 8, 1, 2 };
        data = new Data(initialArray);
    }

    @Test
    void testConstructorValidArray() {
        int[] initialArray = { 5, 3, 8, 1, 2 };
        Data testData = new Data(initialArray);

        assertArrayEquals(initialArray, testData.getArray());
        assertEquals(8, testData.getMaxValue());
        assertEquals(initialArray.length, testData.getSize());
    }

    @Test
    void testConstructorInvalidArray() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Data(null));
        assertEquals("The array cannot be null or empty.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> new Data(new int[] {}));
        assertEquals("The array cannot be null or empty.", exception.getMessage());
    }

    @Test
    void testShuffle() {
        int[] originalArray = data.getArray().clone();
        data.shuffle();
        assertNotEquals(originalArray, data.getArray());
        assertEquals(originalArray.length, data.getArray().length);
    }

    @Test
    void testSwapValidIndices() {
        int firstValue = data.getValueAt(0);
        int secondValue = data.getValueAt(1);

        assertTrue(data.swap(0, 1));
        assertEquals(secondValue, data.getValueAt(0));
        assertEquals(firstValue, data.getValueAt(1));
    }

    @Test
    void testSwapInvalidIndices() {
        assertFalse(data.swap(-1, 1));
        assertFalse(data.swap(0, 100));
    }

    @Test
    void testReset() {
        int[] originalArray = data.getArray().clone();
        data.shuffle();
        data.swap(0, 1);
        data.reset();

        assertArrayEquals(originalArray, data.getArray());
    }

    @Test
    void testSetValueAtValidIndex() {
        data.setValueAt(0, 10);
        assertEquals(10, data.getValueAt(0));
    }

    @Test
    void testSetValueAtInvalidIndex() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> data.setValueAt(-1, 10));
        assertEquals("The provided index is out of bounds.", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> data.setValueAt(100, 10));
        assertEquals("The provided index is out of bounds.", exception.getMessage());
    }

    @Test
    void testIsSorted() {
        assertFalse(data.isSorted());

        int[] sortedArray = { 1, 2, 3, 4, 5 };
        data = new Data(sortedArray);
        assertTrue(data.isSorted());
    }

    @Test
    void testFindMaxValue() {
        assertEquals(8, data.getMaxValue());
    }

    @Test
    void testToString() {
        String expectedString = "[5, 3, 8, 1, 2]";
        assertEquals(expectedString, data.toString());
    }
}