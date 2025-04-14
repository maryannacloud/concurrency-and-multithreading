import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.math.BigInteger;
import java.time.Clock;
import java.util.concurrent.ForkJoinPool;

public class Main {

    private static final int N = 10;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executor = Executors.newFixedThreadPool(N);
        for (int i = 0; i <= 500; i++){
            RunnableTask task = new RunnableTask(10000000L + i);
            executor.execute(task);
        }

        executor.shutdown();
        executor.awaitTermination(30, TimeUnit.SECONDS);
        System.out.println("Finished all threads");

        Clock clock = Clock.systemDefaultZone();
        long start, stop;
        MakeBigIntArray test = new MakeBigIntArray(5000);

        // Check the number of available processors
        int nThreads = Runtime.getRuntime().availableProcessors();
        System.out.println(nThreads);

        // Printouts for threaded results



        // Print outs for sequential results
        BigInteger sum = new BigInteger("1");
        start = clock.millis();
        for (int i = 0; i < test.getList().length; i++) {
            sum = sum.multiply(test.getList()[i]);
        }
        stop = clock.millis();
        System.out.println("Time in ms: " + (stop - start));
        System.out.println("Serial Result: " + sum);

        RecursiveFactorial rSum = new RecursiveFactorial(test.getList());
        ForkJoinPool pool = new ForkJoinPool(nThreads);

        start = clock.millis();
        pool.invoke(rSum);
        stop = clock.millis();

        System.out.println("Time in ms: " + (stop - start));

        BigInteger result = rSum.result;
        System.out.println("Pooled Result: " + result);
    }
}