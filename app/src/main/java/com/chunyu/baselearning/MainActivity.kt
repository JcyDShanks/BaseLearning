package com.chunyu.baselearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.chunyu.baselearning.android.HandlerActivity
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

    private fun initAction() {
        button.setOnClickListener {
            toNext()
        }
        syncBtn.setOnClickListener {
            startActivity(Intent(this, SynchronizedTestActivity::class.java))
        }
        handlerBtn.setOnClickListener {
            // TODO: 2020/12/28
            HandlerActivity.start(this)
        }
    }

    @Subscribe
    fun getNextActivityMessage(nextMessage: NextMessage){
        textView.text = "${textView.text} \n${nextMessage.message}"
    }

    fun toNext() {
        startActivity(Intent(this, NextActivity::class.java))
    }

    override fun onDestroy() {
        HermesEventBus.getDefault().unregister(this)
        super.onDestroy()
    }
}

data class NextMessage(val message: String)