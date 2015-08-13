#include <com_smitch_smartswitch_fibonaccinative_FibLib.h>
#include <log.h>
/*
 * Class:     com_smitch_smartswitch_fibonaccinative_FibLib
 * Method:    FibNativeRecursive
 * Signature: (J)J
 */

 jlong fib(jlong n) {
 if(n == 0 || n == 1)
             return n;
 return fib(n - 1) + fib(n-2);
}

JNIEXPORT jlong JNICALL Java_com_smitch_smartswitch_fibonaccinative_FibLib_FibNativeRecursive
  (JNIEnv *env, jclass mainClass, jlong n) {
        __android_log_print(ANDROID_LOG_DEBUG, "Fiblib.c", "some message");
        return fib(n);
  }

/*
 * Class:     com_smitch_smartswitch_fibonaccinative_FibLib
 * Method:    FibNativeIterative
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_com_smitch_smartswitch_fibonaccinative_FibLib_FibNativeIterative
  (JNIEnv *env, jclass mainClass, jlong n) {
            jlong result = 1;
            jlong prev = -1;
            jlong i;
            for (i = 0; i <= n; i++) {
                jlong sum = result + prev;
                prev = result;
                result = sum;
            }

            return result;
  }

