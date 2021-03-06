package com.chunyu.baselearning.android.launchMode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.chunyu.baselearning.R
import kotlinx.android.synthetic.main.activity_a_launch.*

class ALaunchActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_a_launch)
        Log.e("LaunchTag","A onCreate")
        navigateToB.setOnClickListener {
            startActivity(Intent(this, BLaunchActivity::class.java))
        }
    }
}