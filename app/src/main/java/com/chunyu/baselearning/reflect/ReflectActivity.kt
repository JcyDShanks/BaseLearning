package com.chunyu.baselearning.reflect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.OnClickListener
import com.chunyu.baselearning.R
import java.lang.reflect.Proxy

class ReflectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reflact)
    }

    private fun getListener(): OnClickListener {
        return Proxy.newProxyInstance(
            this.classLoader,
            arrayOf(OnClickListener::class.java),
            ProxyHandler(MyProxyListener())
        ) as OnClickListener
    }



}