package com.chunyu.baselearning.java

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.chunyu.baselearning.R
import kotlinx.android.synthetic.main.activity_synchronized_test.*
import kotlin.concurrent.thread

class SynchronizedTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_synchronized_test)
        initView()
    }

    private fun initView() {
        openThreadCallMethodBtn.setOnClickListener {
            for (i in 1..5) {
                val thread = Thread {
                    syncCallback(i)
                }
                thread.start()
            }
        }
    }

    private fun syncCallback(tag: Int) {
        Log.e("SYNC start", Thread.currentThread().name)
        Log.e("SYNC end", Thread.currentThread().name)
    }
}