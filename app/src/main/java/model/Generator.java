/**
 * The Generator interface provides methods for generating an array of integers,
 * setting and getting the size of the array, setting and getting a variable of type Number,
 * and creating a copy of the Generator instance.
 */
package model;

public interface Generator {
    public int[] generate();
    public void setSize(int size);
    public void setVariable(Number entropy);
    public Number getVariable();
    public int getSize();
    @Override
    String toString();
    Generator copy();
}
