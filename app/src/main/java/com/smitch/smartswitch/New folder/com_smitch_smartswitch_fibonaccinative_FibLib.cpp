#include <android/log.h>
#include <jni.h>


namespace myNamespace {

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

static jlong FibNativeRecursive  (JNIEnv *env, jclass mainClass, jlong n) {
        __android_log_print(ANDROID_LOG_DEBUG, "Fiblib.c", "some message");
        return fib(n);
  }

/*
 * Class:     com_smitch_smartswitch_fibonaccinative_FibLib
 * Method:    FibNativeIterative
 * Signature: (J)J
 */
static jlong FibNativeIterative  (JNIEnv *env, jclass mainClass, jlong n) {
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

  static JNINativeMethod methodTable[] =  {
    { "FibNativeRecursive", "(J)J", (void *) FibNativeRecursive},
    { "FibNativeIterative", "(J)J", (void *) FibNativeIterative}
  };
}

using namespace myNamespace;
extern "C" jint JNI_OnLoad(JavaVM* vm, void* reserved)
           {
               JNIEnv* env;
               if (vm->GetEnv(reinterpret_cast<void**>(&env), JNI_VERSION_1_6) != JNI_OK) {
                   return -1;
               } else {
                jclass clazz = env->FindClass("com/smitch/smartswitch/fibonaccinative/FibLib");
                if(clazz) {
                    env->RegisterNatives(clazz, methodTable, 2);
                    env->DeleteLocalRef(clazz);
                    return JNI_VERSION_1_6;
                } else {
                    return -1;
                }
               }

               // Get jclass with env->FindClass.
               // Register methods with env->RegisterNatives.

               return JNI_VERSION_1_6;
           }
