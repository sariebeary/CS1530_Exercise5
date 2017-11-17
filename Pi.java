
import java.util.concurrent.atomic.*;
import java.util.concurrent.ThreadLocalRandom;

public class Pi {

    public static AtomicLong in = new AtomicLong();
    public static int numThreads;
    public static int numIterations;

    public static void main(String[] args) {

        if (!twoArgs(args) || !isInteger(args)) {
            System.out.println("USAGE: java Pi <numThreads> <numIterations>");
            System.exit(1);
        }
        numThreads = Integer.parseInt(args[0]);
        numIterations = Integer.parseInt(args[1]);

        // Create an array which will hold numThreads 
        Thread[] ts = new Thread[numThreads];

        for (int j = 0; j < numThreads; j++) {

            ts[j] = new Thread(() -> {
                for (int i = 0; i < numIterations / numThreads; i++) {
                    double x = ThreadLocalRandom.current().nextDouble();
                    double y = ThreadLocalRandom.current().nextDouble();
                    double r2 = x * x + y * y;
                    if (r2 < 1) {
                        //inside circle
                        in.getAndIncrement();
                    }
                }
            });
        }

        try {
            for (Thread t : ts) {
                t.start();
            }

            for (Thread t : ts) {
                t.join();
            }

        } catch (InterruptedException iex) {
        }

        long inside = in.longValue();
        double ratio = inside / (double) numIterations;
        double pi = ratio * 4;

        System.out.println("Total  = " + numIterations); // total number of iterations
        System.out.println("Inside = " + inside);// total number of values "inside" the circle 
        System.out.println("Ratio  = " + ratio);// ratio of points inside to total number of iterations
        System.out.println("Pi     = " + pi); // calculate the value of pi from that.

    }

    /**
     * isInteger - ensures args are a 32 bit integer
     *
     * @return true if integer, false otherwise
     */
    public static boolean isInteger(String[] args) {
        try {
            Integer.parseInt(args[0]);
            Integer.parseInt(args[1]);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * twoArgs - ensures two arguments
     *
     * @return true if two arg, false otherwise
     */
    public static boolean twoArgs(String[] args) {
        if (args.length != 2) {
            return false;
        }
        return true;
    }

}
