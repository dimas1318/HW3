package counters;

/**
 * Interface of counter.
 */
public interface Counter {

    /**
     * Return current value to consumer and then increment it inside a counter.
     *
     * @return counter value before incrementing
     */
    long getNumber();
}
