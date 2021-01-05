package com.chunyu.baselearning.java

import android.util.Log

class TestModel {

    var count = 0

    fun noLock() {
        Log.e("SYNC start", Thread.currentThread().name + "---- ${count}")
        Log.e("SYNC before ${Thread.currentThread().name}:", count.toString())
        count++
        Log.e("SYNC after ${Thread.currentThread().name}:", count.toString())
        Log.e("SYNC end", Thread.currentThread().name + "---- ${count}")
    }

    fun lockThis() {
        Log.e("SYNC start", Thread.currentThread().name + "---- ${count}")
        synchronized(this) {
            Log.e("SYNC before ${Thread.currentThread().name}:", count.toString())
            count++
            Log.e("SYNC after ${Thread.currentThread().name}:", count.toString())
        }
        Log.e("SYNC end", Thread.currentThread().name + "---- ${count}")
    }

    fun lockObj(obj: Any) {
        Log.e("SYNC start", Thread.currentThread().name + "---- ${count}")
        synchronized(obj) {
            Log.e("SYNC before ${Thread.currentThread().name}:", count.toString())
            count++
            Log.e("SYNC after ${Thread.currentThread().name}:", count.toString())
        }
        Log.e("SYNC end", Thread.currentThread().name + "---- ${count}")
    }

    fun lockClass() {
        Log.e("SYNC start", Thread.currentThread().name + "---- ${count}")
        synchronized(SynchronizedTestActivity::class.java) {
            Log.e("SYNC before ${Thread.currentThread().name}:", count.toString())
            count++
            Log.e("SYNC after ${Thread.currentThread().name}:", count.toString())
        }
        Log.e("SYNC end", Thread.currentThread().name + "---- ${count}")
    }
}