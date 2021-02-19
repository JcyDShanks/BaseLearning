package com.chunyu.baselearning.android.activityCallback

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chunyu.baselearning.R
import kotlinx.android.synthetic.main.activity_callback.*

class ACallbackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_callback)
        toBBtn.setOnClickListener {
            startActivityForResult(Intent(this, BCallbackActivity::class.java), 1000)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(resultCode) {
            1000 -> {
                callbackTv?.text = "requestCode 1000"
            }
            2000 -> {
                callbackTv?.text = "resultCode 2000"
            }
            else -> {
                callbackTv?.text = "onActivityResult, but no resultCode"
            }
        }
    }
}