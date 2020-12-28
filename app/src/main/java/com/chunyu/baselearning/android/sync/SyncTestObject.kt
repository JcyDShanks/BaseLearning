package com.chunyu.baselearning.android.sync

import android.util.Log

class SyncTestObject {
    fun printLog() {
        Log.e("Sync-unlock", "start-${Thread.currentThread()}")
        Log.e("Sync-unlock", "end-${Thread.currentThread()}")
    }
}