package com.chunyu.baselearning.android.sync

import android.os.Bundle
import android.os.Looper
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.chunyu.baselearning.R
import kotlinx.android.synthetic.main.content_sync_test.*

class SyncTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sync_test)
        setSupportActionBar(findViewById(R.id.toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        initView()
    }

    private fun initView() {
        /*
        * synchronized加锁的对象可以是：
        * this -> 锁住当前对象
        * Object -> 实例化一个对象
        * xxx.class -> 类
        * */
        noSyncBtn.setOnClickListener {
            noLock()
        }
        syncClassBtn.setOnClickListener {
            lockClass()
        }
        syncThisBtn.setOnClickListener {
            lockCurrent()
        }
        syncObjectBtn.setOnClickListener {
            lockObject()
        }
    }


    /*
    * 不加锁
    * */
    private fun noLock() {
        Log.e("Sync", "Unlock")
        for (i in 1..5) {
            val thread = Thread {
                val syncTestObject = SyncTestObject()
                if (Looper.getMainLooper() == Looper.myLooper()) {
                    Log.e("Sync", "MainThread")
                }
                syncTestObject.printLog()
            }
            thread.start()
        }
    }

    /*
    * 锁住this
    * */
    private fun lockCurrent() {
        Log.e("Sync", "This")
        val syncTestObject = SyncTestObject()
        synchronized(this) {
            for (i in 1..5) {
                val thread = Thread {
                    syncTestObject.printLog()
                }
                thread.start()
            }
        }
    }

    val obj = Object()
    /*
    * 锁住当前对象
    * */
//    val syncTestObject = SyncTestObject()

    private fun lockObject() {
        Log.e("Sync", "Object")
        val syncTestObject = SyncTestObject()
        synchronized(syncTestObject) {
            for (i in 1..5) {
                val thread = Thread {
                    syncTestObject.printLog()
                }
                thread.start()
            }
        }
    }

    val syncTestObject = SyncTestObject()
    /*
    * 锁住class
    * */
    private fun lockClass() {
        Log.e("Sync", "Class")
        synchronized(syncTestObject.javaClass) {
            for (i in 1..5) {
                val thread = Thread {
                    syncTestObject.printLog()
                }
                thread.start()
            }
        }
    }
}