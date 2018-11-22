package counters;

import java.util.concurrent.locks.StampedLock;

/**
 * {@link Counter} realization via {@link StampedLock}.
 */
public class StampedLockCounter implements Counter {

    /**
     * Counter value.
     */
    private long mCounter;

    /**
     * Lock;
     */
    private final StampedLock mLock = new StampedLock();

    /**
     * {@inheritDoc}
     */
    @Override
    public long getNumber() {
        long stamp = mLock.readLock();
        try {
            return mCounter++;
        } finally {
            mLock.unlockRead(stamp);
        }
    }
}