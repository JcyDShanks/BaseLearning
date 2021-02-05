package com.chunyu.baselearning

import android.app.Activity
import android.app.ListActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.chunyu.baselearning.android.HandlerActivity
import com.chunyu.baselearning.android.NextActivity
import com.chunyu.baselearning.android.binder.MyClientActivity
import com.chunyu.baselearning.android.recyclerview.RecyclerViewActivity
import com.chunyu.baselearning.java.SynchronizedTestActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.Subscribe
import xiaofei.library.hermeseventbus.HermesEventBus
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        HermesEventBus.getDefault().register(this)
        initAction()
//        var mainURl = Thread.currentThread().name
//        thread {
//            var url1 = "thread1"
//            Thread.sleep(2000)
//            mainURl = url1
//            Log.e("chunyu", mainURl)
//            Toast.makeText(this, "toast", Toast.LENGTH_SHORT).show()
//        }
//
//        thread {
//            var url2 = "thread2"
//            Thread.sleep(2000)
//        }

    }

    companion object {
        fun start(activity: Activity) {
            val intent = Intent(activity, MainActivity::class.java)
            activity.startActivity(intent)
        }
    }

    private fun initAction() {
        button.setOnClickListener {
            toNext()
        }
        syncBtn.setOnClickListener {
            startActivity(Intent(this, SynchronizedTestActivity::class.java))
        }
        handlerBtn.setOnClickListener {
            HandlerActivity.start(this)
        }
        binderTestBtn.setOnClickListener {
            val intent = Intent(this, MyClientActivity::class.java)
            startActivity(intent)
        }
        recyclerViewBtn.setOnClickListener {
            val intent = Intent(this, RecyclerViewActivity::class.java)
            startActivity(intent)
        }
    }

    @Subscribe
    fun getNextActivityMessage(nextMessage: NextMessage){
        textView.text = "${textView.text} \n${nextMessage.message}"
    }

    private fun toNext() {
        startActivity(Intent(this, NextActivity::class.java))
    }

    override fun onDestroy() {
        HermesEventBus.getDefault().unregister(this)
        super.onDestroy()
    }
}

data class NextMessage(val message: String)