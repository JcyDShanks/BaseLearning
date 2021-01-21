package com.chunyu.baselearning.java

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import com.chunyu.baselearning.R
import kotlinx.android.synthetic.main.activity_synchronized_test.*

class SynchronizedTestActivity : AppCompatActivity() {


    private val testModel = TestModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_synchronized_test)
        initView()
        for (i in 1..5) {
            val thread = Thread {
                testModel.noLock()
            }
            thread.start()
        }
        Message.obtain()
    }

    private fun initView() {
        noLockBtn.setOnClickListener {
            Log.e("SYNC", "No Lock")
            for (i in 1..5) {
                val thread = Thread {
                    testModel.noLock()
                }
                thread.start()
            }
        }

        lockThisBtn.setOnClickListener {
            Log.e("SYNC", "Lock This")
            for (i in 1..5) {
                val thread = Thread {
                    testModel.lockThis()
                }
                thread.start()
            }
        }

        lockObjectBtn.setOnClickListener {
            Log.e("SYNC", "Lock Object")
            for (i in 1..5) {
                val thread = Thread {
                    testModel.lockObj(testModel)
                }
                thread.start()
            }
        }

        lockClassBtn.setOnClickListener {
            Log.e("SYNC", "Lock Class")
            for (i in 1..5) {
                val thread = Thread {
                    testModel.lockClass()
                }
                thread.start()
            }
        }
    }
}