package com.chunyu.baselearning.android.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.widget.Toast
import com.chunyu.baselearning.R

class MyForegroundService : Service() {

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel("MyForegroundService", "MyForegroundChannel", NotificationManager.IMPORTANCE_HIGH)

            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(notificationChannel)
            val builder = Notification.Builder(this, "MyForegroundService")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText("服务正在运行中")
            startForeground(100001, builder.build())
        }
        ForegroundImplService.start(this)
        stopSelf()
        return super.onStartCommand(intent, flags, startId)
    }
}
