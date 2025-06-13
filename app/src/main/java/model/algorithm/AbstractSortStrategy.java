package model.algorithm;

import experience.*;

/**
 * AbstractSortStrategy is an abstract class that implements the SortStrategy interface.
 * It provides a base implementation for sorting strategies with a Sonde object.
 */
public abstract class AbstractSortStrategy implements SortStrategy {

    /**
     * The Sonde object used for tracking sorting metrics.
     */
    protected Sonde sonde;

    /**
     * Constructs an AbstractSortStrategy with a default Sonde object.
     */
    public AbstractSortStrategy(){
        this.sonde = new Sonde(0, 0, 0, 0);
    }

    /**
     * Returns the Sonde object associated with this sorting strategy.
     *
     * @return the Sonde object
     */
    @Override
    public Sonde getSonde() {
        return this.sonde;
    }
    
}
