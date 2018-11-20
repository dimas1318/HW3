import counters.*;

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
