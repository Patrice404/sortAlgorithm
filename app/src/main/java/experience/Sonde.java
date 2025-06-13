package experience;

/**
 * The Sonde class is used to track and record various metrics such as the
 * number of comparisons, accesses, swaps, and time taken during an algorithm's
 * execution.
 */
public class Sonde {

    private long nbComparaison, nbAccess, nbEchanges, time;

    /**
     * Constructs a new Sonde object with the specified metrics.
     *
     * @param nbComparaison the number of comparisons
     * @param nbAccess      the number of accesses
     * @param nbEchanges    the number of swaps
     * @param time          the time taken
     */
    public Sonde(long nbComparaison, long nbAccess, long nbEchanges, long time) {
        this.nbComparaison = nbComparaison;
        this.nbAccess = nbAccess;
        this.nbEchanges = nbEchanges;
        this.time = time;
    }

    /**
     * Creates a copy of the given Sonde object.
     *
     * @param sonde the Sonde object to copy
     * @return a new Sonde object that is a copy of the given Sonde
     */
    public static Sonde copy(Sonde sonde) {
        Sonde copy = new Sonde(sonde.getNbComparaisons(), sonde.getNbAccess(), sonde.getNbEchanges(), sonde.getTime());
        return copy;
    }

    /**
     * Adds the metrics of the given Sonde object to this Sonde.
     *
     * @param sonde the Sonde object whose metrics are to be added
     */
    public void add(Sonde sonde) {
        this.nbAccess += sonde.getNbAccess();
        this.nbComparaison += sonde.getNbComparaisons();
        this.nbEchanges += sonde.getNbEchanges();
        this.time += sonde.getTime();
    }

    /**
     * Divides the metrics of this Sonde by the specified value.
     *
     * @param i the value by which to divide the metrics
     */
    public void diviseBy(long i) {
        this.nbComparaison = this.nbComparaison / i;
        this.nbAccess = this.nbAccess / i;
        this.nbEchanges = this.nbEchanges / i;
        this.time = this.time / i;
    }

    /**
     * Resets all metrics of this Sonde to zero.
     */
    public void reset() {
        this.nbAccess = 0;
        this.nbComparaison = 0;
        this.nbEchanges = 0;
        this.time = 0;
    }

    /**
     * Returns a string representation of this Sonde.
     *
     * @return a string representation of this Sonde
     */

    public String toString() {
        return "Sonde{" +
                "nbComparaison =" + nbComparaison +
                ", nbAccess =" + nbAccess +
                " , nbEchanges=" + nbEchanges +
                " , temps =" + time +
                '}';

    }

    /**
     * Returns the number of comparisons.
     *
     * @return the number of comparisons
     */
    public long getNbComparaisons() {
        return nbComparaison;
    }

    /**
     * Returns the number of accesses.
     *
     * @return the number of accesses
     */
    public long getNbAccess() {
        return nbAccess;
    }

    /**
     * Returns the number of swaps.
     *
     * @return the number of swaps
     */
    public long getNbEchanges() {
        return this.nbEchanges;
    }

    /**
     * Returns the time taken.
     *
     * @return the time taken
     */

    public long getTime() {
        return time;
    }

    /**
     * Increments the number of comparisons by one.
     */
    public void incrementComparaison() {
        nbComparaison++;
    }

    /**
     * Increments the number of accesses by one.
     */
    public void incrementAccess() {
        nbAccess++;
    }

    /**
     * Increments the number of accesses by the specified value.
     *
     * @param n the value by which to increment the number of accesses
     */
    public void incrementAccess(long n) {
        nbAccess += n;
    }

    /**
     * Increments the number of swaps by one and increments the number of accesses
     * by two.
     */
    public void incrementSwaps() {
        nbEchanges++;
        nbAccess += 2;
    }

    /**
     * Sets the time taken to the specified value.
     *
     * @param time the time taken
     */
    public void setTime(long time) {
        if (time >= 0) {
            this.time = time;
        }
    }

    @Override
    public boolean equals(Object obj) {
        Sonde sonde = (Sonde)obj;
        return this.nbAccess==sonde.getNbAccess() && this.nbComparaison==sonde.getNbComparaisons() && this.nbEchanges==sonde.getNbEchanges();
    }

}