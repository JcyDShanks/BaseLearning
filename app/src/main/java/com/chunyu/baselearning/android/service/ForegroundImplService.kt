package com.chunyu.baselearning.android.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import kotlin.concurrent.thread

class ForegroundImplService: Service() {

    companion object {
        fun start(context: Context) {
            val intent = Intent(context, ForegroundImplService::class.java)
            context.startService(intent)
        }

        fun stop(context: Context) {
            val intent = Intent(context, ForegroundImplService::class.java)
            context.stopService(intent)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        thread {
            for (i in 1..10) {
                Thread.sleep(2000)
                Log.e("ForegroundImpl", i.toString())
            }
            stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        Log.e("ForegroundImpl", "关闭服务")
        super.onDestroy()
    }
}