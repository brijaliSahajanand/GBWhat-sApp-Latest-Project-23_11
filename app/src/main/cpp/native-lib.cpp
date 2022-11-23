
#include "native-lib.h"

extern "C" JNIEXPORT jstring JNICALL



Java_com_newfastwa_msgemojitype_gbfo_retrofit_APIClient_stringFromJNI(
        JNIEnv *env, //default env parameter.
        jobject /* this, activity context. */) {
    std::string hello = "http://139.59.84.76:1207/";
    return env->NewStringUTF(hello.c_str());
}



