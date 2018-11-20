package counters;

public class VolatileCounter implements Counter {

    private volatile long mCounter;

    @Override
    public long getNumber() {
        return mCounter++;
    }
}
