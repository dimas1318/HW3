package test;

import counters.*;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import static java.lang.Thread.sleep;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openjdk.jmh.annotations.Mode.Throughput;

public class Test {

    private static final int THREAD_COUNT = 8;
//    private static final long THREAD_TIME_MS = 2;
    private static final int WARMUP_ITERATIONS = 1;
    private static final int MEASUREMENT_ITERATION = 5;

    @State(Scope.Benchmark)
    public static class CounterState {
        Counter synchronizedCounter = new SynchronizedCounter();
        Counter reentrantLockCounter = new ReentrantLockCounter();
        Counter atomicLongCounter = new AtomicLongCounter();
        Counter volatileCounter = new VolatileCounter();
    }

    @Benchmark
    @BenchmarkMode(Throughput)
    @OutputTimeUnit(SECONDS)
    @Group("SynchronizedCounter")
    public void testSynchronizedCounter(CounterState state) {
        state.synchronizedCounter.getNumber();
        threadSleep();
    }

    @Benchmark
    @BenchmarkMode(Throughput)
    @OutputTimeUnit(SECONDS)
    @Group("ReentrantLockCounter")
    public void testReentrantLockCounter(CounterState state) {
        state.reentrantLockCounter.getNumber();
        threadSleep();
    }

    @Benchmark
    @BenchmarkMode(Throughput)
    @OutputTimeUnit(SECONDS)
    @Group("AtomicLongCounter")
    public void testAtomicLongCounter(CounterState state) {
        state.atomicLongCounter.getNumber();
        threadSleep();
    }

    @Benchmark
    @BenchmarkMode(Throughput)
    @OutputTimeUnit(SECONDS)
    @Group("VolatileCounter")
    public void testVolatileCounter(CounterState state) {
        state.volatileCounter.getNumber();
        threadSleep();
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(Test.class.getName())
                .warmupIterations(WARMUP_ITERATIONS)
                .measurementIterations(MEASUREMENT_ITERATION)
                .forks(1)
                .threads(THREAD_COUNT)
                .resultFormat(ResultFormatType.JSON)
                .result("Threads" + THREAD_COUNT + "_W" + WARMUP_ITERATIONS + "_M" + MEASUREMENT_ITERATION + ".json")
                .build();
        new Runner(options).run();
    }

    private void threadSleep() {
//        try {
//            sleep(Test.THREAD_TIME_MS);
//        } catch (InterruptedException ignored) {
//        }
    }
}