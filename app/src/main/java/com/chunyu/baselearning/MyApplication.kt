package com.chunyu.baselearning

import android.app.Application
import xiaofei.library.hermeseventbus.HermesEventBus

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        HermesEventBus.getDefault().init(this)
//        HermesEventBus.getDefault().connectApp()
    }
}