public class RunnableTask implements Runnable {

    private final long limit;

    // Constructor
    RunnableTask(long limit) {
        this.limit = limit;
    }

    @Override
    public void run(){
        long sum = 0;
        for (long i = 1; i <= limit; i++){
            sum += i;
        }

        System.out.println("Sum up to " + limit + " is " + sum + " | Executed by " + Thread.currentThread().getName());
    }
}
