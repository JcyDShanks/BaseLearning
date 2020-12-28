package com.chunyu.baselearning.java;

import android.util.Log;

public class SyncTest {

    public static void main(String[] args) {
        final SyncTest syncTest = new SyncTest();
        for (int i =0 ; i < 5; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    syncTest.test();
                }
            });
            thread.start();
        }
    }

    public void test() {
        Log.d("SYNC start", Thread.currentThread().getName());
        Log.d("SYNC end", Thread.currentThread().getName());
    }
}
