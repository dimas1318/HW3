package counters;

import java.util.concurrent.Semaphore;

/**
 * {@link Counter} realization via {@link Semaphore}.
 */
public class SemaphoreCounter implements Counter {

    /**
     * Counter value.
     */
    private long mCounter = 0;

    /**
     * Semaphore.
     */
    private Semaphore mSemaphore = new Semaphore(1, true);

    /**
     * {@inheritDoc}
     */
    @Override
    public long getNumber() {
        long number = 0;
        try {
            mSemaphore.acquire();
            number = mCounter++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            mSemaphore.release();
        }
        return number;
    }
}
