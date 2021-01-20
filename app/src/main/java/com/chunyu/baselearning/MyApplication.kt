package com.chunyu.baselearning

import android.app.Application
import com.chunyu.baselearning.android.SharedPrefManager

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        SharedPrefManager.init(this)
    }

}