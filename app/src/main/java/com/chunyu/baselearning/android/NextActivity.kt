package com.chunyu.baselearning.android


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.chunyu.baselearning.NextMessage
import com.chunyu.baselearning.R
import kotlinx.android.synthetic.main.activity_next.*
import xiaofei.library.hermeseventbus.HermesEventBus

class NextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)
        btn.setOnClickListener {
            sendMessage()
        }
    }

    private fun sendMessage(){
        HermesEventBus.getDefault().post(NextMessage("我是NextMessage传递的信息"))
        Toast.makeText(this, "成功发送消息！", Toast.LENGTH_SHORT).show()
    }
}