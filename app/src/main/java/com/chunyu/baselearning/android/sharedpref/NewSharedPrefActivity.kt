package com.chunyu.baselearning.android.sharedpref

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chunyu.baselearning.R
import kotlinx.android.synthetic.main.activity_new_shared_pref.*

class NewSharedPrefActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_shared_pref)
        initViews()
    }

    fun initViews() {
        sharedPrefBtn.setOnClickListener {
            getDefault()
        }
    }

    fun getDefault() {
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(SharedPrefLearnningActivity.SYSTEM_SP, "this is SYSTEM_SP").apply()
    }
}