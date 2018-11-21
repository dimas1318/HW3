import counters.*;

/**
 * This class is used only for debugging of counter realizations.<br>
 * I've marked it as {@link Deprecated} because realizations are done and a benchmark is done too.
 */
@Deprecated
public class Main {

    public static void main(String[] args) {
        int end = 100;
//        Counter counter = new SynchronizedCounter();
//        Counter counter = new ReentrantLockCounter();
//        Counter counter = new AtomicLongCounter();
        Counter counter = new VolatileCounter();

        Thread thread1 = new Thread(() -> {
            long number;
            while ((number = counter.getNumber()) < end) {
                System.out.println(Thread.currentThread().getName() + ": " + number);
            }
        });
        Thread thread2 = new Thread(() -> {
            long number;
            while ((number = counter.getNumber()) < end) {
                System.out.println(Thread.currentThread().getName() + ": " + number);
            }
        });
        Thread thread3 = new Thread(() -> {
            long number;
            while ((number = counter.getNumber()) < end) {
                System.out.println(Thread.currentThread().getName() + ": " + number);
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
