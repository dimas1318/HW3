package counters;

import java.util.concurrent.locks.StampedLock;

/**
 * {@link Counter} realization via {@code synchronized} method.
 */
public class SynchronizedCounter implements Counter {

    /**
     * Counter value.
     */
    private long mCounter;

    /**
     * {@inheritDoc}
     */
    @Override
    public synchronized long getNumber() {
        return mCounter++;
    }
}
