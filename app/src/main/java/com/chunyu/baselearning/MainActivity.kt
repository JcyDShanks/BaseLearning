package com.chunyu.baselearning

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.greenrobot.eventbus.Subscribe
import xiaofei.library.hermeseventbus.HermesEventBus

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        HermesEventBus.getDefault().register(this)
        button.setOnClickListener {
            toNext()
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