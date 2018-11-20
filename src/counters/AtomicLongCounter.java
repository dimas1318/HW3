package counters;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicLongCounter implements Counter {

    private AtomicLong mCounter = new AtomicLong(0);

    @Override
    public long getNumber() {
        return mCounter.getAndIncrement();
    }
}
