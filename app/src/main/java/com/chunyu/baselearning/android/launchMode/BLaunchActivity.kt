package com.chunyu.baselearning.android.launchMode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chunyu.baselearning.MainActivity
import com.chunyu.baselearning.R
import kotlinx.android.synthetic.main.activity_b_launch.*

class BLaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_launch)
        navigateToC.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}