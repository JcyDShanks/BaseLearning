package com.chunyu.baselearning.android.activityCallback

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chunyu.baselearning.R
import kotlinx.android.synthetic.main.activity_b_callback.*

class BCallbackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_callback)
        callbackBtn?.setOnClickListener {
            setResult(2000)
        }
    }

}