package counters;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockCounter implements Counter {

    private long mCounter;

    private ReentrantLock mLock = new ReentrantLock(true);

    @Override
    public long getNumber() {
        mLock.lock();
        long number = mCounter++;
        mLock.unlock();
        return number;
    }
}
