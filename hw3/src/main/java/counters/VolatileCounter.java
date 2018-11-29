package counters;

/**
 * {@link Counter} realization via {@code volatile} variable.
 */
public class VolatileCounter implements Counter {

    /**
     * Counter value.
     */
    private volatile long mCounter;

    /**
     * {@inheritDoc}
     */
    @Override
    public long getNumber() {
        synchronized (this) {
            return mCounter++;
        }
    }
}