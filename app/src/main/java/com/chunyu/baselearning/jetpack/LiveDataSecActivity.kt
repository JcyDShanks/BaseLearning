package com.chunyu.baselearning.jetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chunyu.baselearning.R
import kotlinx.android.synthetic.main.activity_live_data_sec.*

class LiveDataSecActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data_sec)
        sendBtn.setOnClickListener {
            LiveDataObject.value = ExampleModel().apply {
                this.rawValue.value = "from second"
            }
        }
    }
}