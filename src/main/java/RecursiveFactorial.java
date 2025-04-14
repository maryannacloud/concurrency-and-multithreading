import java.math.BigInteger;
import java.util.Arrays;
import java.util.concurrent.RecursiveAction;

public class RecursiveFactorial extends RecursiveAction {

    private BigInteger[] list;
    public BigInteger result;

    public RecursiveFactorial(BigInteger[] array) {
        this.list = array;
    }

    @Override
    protected void compute() {
        if (list.length == 1) {
            result = list[0];
        } else {
            int midpoint = list.length / 2;
            BigInteger[] l1 = Arrays.copyOfRange(list, 0, midpoint);
            BigInteger[] l2 = Arrays.copyOfRange(list, midpoint, list.length);

            RecursiveFactorial rf1 = new RecursiveFactorial(l1);
            RecursiveFactorial rf2 = new RecursiveFactorial(l2);

            rf1.fork();
            rf2.compute();
            rf1.join();

            result = rf1.result.multiply(rf2.result);
        }
    }
}
