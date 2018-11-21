package counters;

public class SynchronizedCounter implements Counter {

    private long mCounter;

    @Override
    public synchronized long getNumber() {
        return mCounter++;
    }
}
