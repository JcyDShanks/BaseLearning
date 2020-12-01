package com.chunyu.baselearning.android.sharedpref

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chunyu.baselearning.R
import com.chunyu.baselearning.android.SharedPrefManager
import kotlinx.android.synthetic.main.activity_shared_pref_learnning.*

class SharedPrefLearnningActivity : AppCompatActivity() {

    companion object {
        const val MY_KEY_SP = "MY_KEY_SP"
        const val SYSTEM_SP = "SYSTEM_SP"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_pref_learnning)
        action()
    }

    fun action() {
        getMySpBtn.setOnClickListener {
            getMySp()
        }
        getDefaultSpBtn.setOnClickListener {
            getDefault()
        }
        useSpManagerBtn.setOnClickListener {
            getManager()
        }
        newPage.setOnClickListener {
            openNewPage()
        }
    }


    fun getMySp() {
        val sharedPreferences = getSharedPreferences(MY_KEY_SP, MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(MY_KEY_SP, "this is MY_KEY_SP").apply()
    }

    fun getDefault() {
        val sharedPreferences = getPreferences(MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(SYSTEM_SP, "this is SYSTEM_SP").apply()
    }

    fun getManager() {
        val sharedPreferences = SharedPrefManager.getSystemSharedPref()
        SharedPrefManager.saveString("SP_Manager", "this is sp manager")
    }

    fun openNewPage() {
        startActivity(Intent(this, NewSharedPrefActivity::class.java))
    }
}