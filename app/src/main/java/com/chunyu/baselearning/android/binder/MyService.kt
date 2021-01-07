package com.chunyu.baselearning.android.binder

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    val myBinder = MyBinder()
    var isPlaying = false

    fun startPlay() {
        isPlaying = true
        Log.e("MyService","start isPlaying: ${isPlaying}")
    }

    fun stopPlay() {
        isPlaying = false
        Log.e("MyService","stop isPlaying: ${isPlaying}")
    }

    override fun onBind(intent: Intent): IBinder {
        return myBinder
    }

    inner class MyBinder: Binder() {
        fun getService(): MyService {
            return this@MyService
        }

        fun isPlaying(): Boolean {
            return isPlaying
        }
    }
}