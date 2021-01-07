package com.chunyu.baselearning

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chunyu.baselearning.android.HandlerActivity
import com.chunyu.baselearning.android.NextActivity
import com.chunyu.baselearning.java.SynchronizedTestActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.Subscribe
import xiaofei.library.hermeseventbus.HermesEventBus

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        HermesEventBus.getDefault().register(this)
        initAction()
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