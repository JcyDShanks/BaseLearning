package com.chunyu.baselearning.jetpack

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.chunyu.baselearning.R
import kotlinx.android.synthetic.main.activity_live_data.*

class LiveDataActivity : AppCompatActivity() {

//    private val model : ExampleModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)
        val observer = Observer<ExampleModel> { model ->
            resultTv?.text = model.rawValue.value
        }
        LiveDataObject.observe(this, observer)
//        model.rawValue.observe(this, observer)

        randomBtn.setOnClickListener {
            LiveDataObject.value = ExampleModel().apply {
                this.rawValue.value = Math.random().toString()
            }
            startActivity(Intent(this, LiveDataSecActivity::class.java))
        }
    }
}