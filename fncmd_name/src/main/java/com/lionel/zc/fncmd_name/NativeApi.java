package com.lionel.zc.fncmd_name;

public class NativeApi {
    static {
        System.loadLibrary("hello");
    }

    public native int add(int a, int b);
}
