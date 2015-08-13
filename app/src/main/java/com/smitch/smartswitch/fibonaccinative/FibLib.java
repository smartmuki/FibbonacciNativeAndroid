package com.smitch.smartswitch.fibonaccinative;

/**
 * Created by mukeshag on 08/11/2015.
 */
public class FibLib {
    public static long FibJavaRecursive(long n) {
        if(n == 0 || n == 1)
            return n;
        return FibJavaRecursive(n - 1) + FibJavaRecursive(n - 2);
    }

    public native static long FibNativeRecursive(long n);  // sort of abstract class
        // native implementation will be provided later
        // JNI

    public static long FibJavaIterative(long n) {
        long result = 1;
        long prev = -1;
        for (long i = 0; i <= n; i++) {
            long sum = result + prev;
            prev = result;
            result = sum;
        }

        return result;
    }

    public native static long FibNativeIterative(long n);

    static {
        System.loadLibrary("com_smitch_smartswitch_fibonaccinative_FibLib");
    }
}
