LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
LOCAL_SRC_FILES := com_smitch_smartswitch_fibonaccinative_FibLib.cpp
LOCAL_MODULE := com_smitch_smartswitch_fibonaccinative_FibLib
LOCAL_LDLIBS += -llog
include $(BUILD_SHARED_LIBRARY)