package counters;

import java.util.concurrent.locks.ReentrantLock;

/**
 * {@link Counter} realization via {@link ReentrantLock}.
 */
public class ReentrantLockCounter implements Counter {

    /**
     * Counter value.
     */
    private long mCounter;

    /**
     * Lock.
     */
    private final ReentrantLock mLock = new ReentrantLock(true);

    /**
     * {@inheritDoc}
     */
    @Override
    public long getNumber() {
        mLock.lock();
        try {
            return mCounter++;
        } finally {
            mLock.unlock();
        }
    }
}
