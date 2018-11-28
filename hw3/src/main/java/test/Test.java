package test;

import counters.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openjdk.jmh.annotations.Mode.Throughput;

/**
 * Benchmark tests of {@link Counter} realizations.
 */
public class Test {

    /**
     * Number of threads.
     */
    private static final int THREAD_COUNT = 8;

    /**
     * Time of thread sleep (in milliseconds).
     */
    private static final long THREAD_SLEEP_TIME_MS = 2;

    /**
     * Number of warmup iterations.
     */
    private static final int WARMUP_ITERATIONS = 10;

    /**
     * Number of measurement iterations.
     */
    private static final int MEASUREMENT_ITERATIONS = 70;

    @State(Scope.Benchmark)
    public static class CounterState {
        Counter synchronizedCounter = new SynchronizedCounter();
        Counter reentrantLockCounter = new ReentrantLockCounter();
        Counter atomicLongCounter = new AtomicLongCounter();
        Counter volatileCounter = new VolatileCounter();
        Counter semaphoreCounter = new SemaphoreCounter();
        Counter stampedLockCounter = new StampedLockCounter();
    }

    @Benchmark
    @BenchmarkMode(Throughput)
    @OutputTimeUnit(SECONDS)
    @Group("SynchronizedCounter")
    public void testSynchronizedCounter(CounterState state) {
        state.synchronizedCounter.getNumber();
//        sleep();
    }

    @Benchmark
    @BenchmarkMode(Throughput)
    @OutputTimeUnit(SECONDS)
    @Group("ReentrantLockCounter")
    public void testReentrantLockCounter(CounterState state) {
        state.reentrantLockCounter.getNumber();
//        sleep();
    }

    @Benchmark
    @BenchmarkMode(Throughput)
    @OutputTimeUnit(SECONDS)
    @Group("AtomicLongCounter")
    public void testAtomicLongCounter(CounterState state) {
        state.atomicLongCounter.getNumber();
//        sleep();
    }

    @Benchmark
    @BenchmarkMode(Throughput)
    @OutputTimeUnit(SECONDS)
    @Group("VolatileCounter")
    public void testVolatileCounter(CounterState state) {
        state.volatileCounter.getNumber();
//        sleep();
    }

    @Benchmark
    @BenchmarkMode(Throughput)
    @OutputTimeUnit(SECONDS)
    @Group("SemaphoreCounter")
    public void testSemaphoreCounter(CounterState state) {
        state.semaphoreCounter.getNumber();
//        sleep();
    }

    @Benchmark
    @BenchmarkMode(Throughput)
    @OutputTimeUnit(SECONDS)
    @Group("StampedLockCounter")
    public void testStampedLockCounter(CounterState state) {
        state.stampedLockCounter.getNumber();
//        sleep();
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(Test.class.getName())
                .warmupIterations(WARMUP_ITERATIONS)
                .measurementIterations(MEASUREMENT_ITERATIONS)
                .forks(1)
                .threads(THREAD_COUNT)
                .resultFormat(ResultFormatType.JSON)
                .result("Threads" + THREAD_COUNT + "_W" + WARMUP_ITERATIONS + "_M" + MEASUREMENT_ITERATIONS + "sleep.json")
                .build();
        new Runner(options).run();
    }

    /**
     * Sleep for {@code THREAD_SLEEP_TIME_MS} milliseconds.
     */
    private void sleep() {
        try {
            Thread.sleep(Test.THREAD_SLEEP_TIME_MS);
        } catch (InterruptedException ignored) {
        }
    }
}