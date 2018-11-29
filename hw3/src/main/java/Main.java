import counters.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * This class is used only for debugging of counter realizations.<br>
 * I've marked it as {@link Deprecated} because realizations are done and a benchmark is done too.
 */
@Deprecated
public class Main {

    public static void main(String[] args) {
        List<Long> list = new CopyOnWriteArrayList<>();

        int end = 1_500_000;
//        Counter counter = new SynchronizedCounter();
//        Counter counter = new ReentrantLockCounter();
//        Counter counter = new AtomicLongCounter();
        Counter counter = new VolatileCounter();
//        Counter counter = new SemaphoreCounter();
//        Counter counter = new StampedLockCounter();

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        for (int i = 0; i < end; i++) {
            executor.execute(() -> {
                list.add(counter.getNumber());
            });
        }

        Set<Long> set = findDuplicates(list);
        System.out.println(set.size() == 0 ? "GOOD REALIZATION" : "BAD REALIZATION");
    }

    private static Set<Long> findDuplicates(List<Long> listContainingDuplicates) {
        final Set<Long> setToReturn = new HashSet<>();
        final Set<Long> set = new HashSet<>();

        for (long number : listContainingDuplicates) {
            if (!set.add(number)) {
                setToReturn.add(number);
            }
        }
        return setToReturn;
    }
}
