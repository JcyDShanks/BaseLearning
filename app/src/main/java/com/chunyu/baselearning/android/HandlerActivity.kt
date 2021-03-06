package com.chunyu.baselearning.android

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.Log
import android.widget.Toast
import com.chunyu.baselearning.R
import kotlinx.android.synthetic.main.activity_handler.*
import kotlinx.coroutines.awaitAll
import java.lang.Exception
import java.util.function.LongFunction
import kotlin.concurrent.thread

class HandlerActivity : AppCompatActivity() {

    companion object {
        fun start(activity: Activity) {
            activity.startActivity(Intent(activity, HandlerActivity::class.java))
        }

        const val ReceiverMessage = 100001
        const val FromGradson = 100002
    }

    //  主线程
    private var handler: Handler? = null

    private var sHandler = SHandler()

    class KHandler: Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
        }
    }


    /* 该处理程序类应为静态，否则可能发生泄漏 */
    @SuppressLint("HandlerLeak")
    inner class SHandler: Handler() {
        override fun handleMessage(msg: Message) {
            when(msg.what) {
                ReceiverMessage -> {
                    val result = msg.obj as String
                    logTv.text = result

                    true
                }
                FromGradson -> {
                    val result = msg.obj as String
                    logTv.text = "From" + result
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler)

        var kHandler = KHandler()
//        handler = Handler { message ->
//            when(message.what) {
//                ReceiverMessage -> {
//                    val result = message.obj as String
//                    logTv.text = result
//                    true
//                }
//                FromGradson -> {
//                    val result = message.obj as String
//                    logTv.text = "From" + result
//                    true
//                }
//                else -> {
//                    false
//                }
//            }
//        }
        initAction()
    }

    private fun initAction() {
        sendToMainBtn.setOnClickListener {
            // 子线程
            thread {
                val text = "子线程: ${Thread.currentThread().name}"
                val message = Message().apply {
                    what = ReceiverMessage
                    this.obj = text
                }
                sHandler.sendMessageDelayed(message, 20000)
//                handler?.sendMessage(message)
                // 子线程中使用handler需要先创建looper
                Looper.prepare()
                Looper.loop()
                val subThreadHandler = Handler {
                    when(it.what) {
                        ReceiverMessage -> {
                            val msg = Message().apply {
                                this.what = FromGradson
                                this.obj = "孙子线程: ${it.obj as String}"
                            }
                            Log.e("Loop", "孙子线程: ${it.obj as String}")
                            handler?.sendMessage(message)
                            false
                        }
                        else -> false
                    }
                }
                thread {
                    Log.e("HAN", "孙子线程开始执行")
                    val message = Message().apply {
                        what = ReceiverMessage
                        obj = Thread.currentThread().name
                    }
                    subThreadHandler.sendMessage(message)
                }
                // handler创建完再开启消息轮询，否则收不到消息
//                Looper.loop()
            }
        }
    }
}