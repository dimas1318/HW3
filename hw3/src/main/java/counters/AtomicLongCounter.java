package counters;

import java.util.concurrent.atomic.AtomicLong;

/**
 * {@link Counter} realization via {@link AtomicLong}.
 */
public class AtomicLongCounter implements Counter {

    /**
     * Counter value.
     */
    private AtomicLong mCounter = new AtomicLong(0);

    /**
     * {@inheritDoc}
     */
    @Override
    public long getNumber() {
        return mCounter.getAndIncrement();
    }
}
