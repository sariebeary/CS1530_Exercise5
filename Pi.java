
import java.util.concurrent.atomic.*;
import java.util.concurrent.ThreadLocalRandom;

public class Pi {

    public static AtomicLong in = new AtomicLong();
    public static long numThreads;
    public static long numIterations;

    public static void main(String[] args) {

        if (!twoArgs(args) || !isLong(args)) {
            System.out.println("USAGE: java Pi <numThreads> <numIterations>");
            System.exit(1);
        }
        numThreads = Long.parseLong(args[0]);
        numIterations = Long.parseLong(args[1]);

        // Create an array which will hold numThreads 
        Thread[] ts = new Thread[(int)numThreads];

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
     * isLong - ensures args are longs
     *
     * @return true if integer, false otherwise
     */
    public static boolean isLong(String[] args) {
        try {
            Long.parseLong(args[0]);
            Long.parseLong(args[1]);
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
