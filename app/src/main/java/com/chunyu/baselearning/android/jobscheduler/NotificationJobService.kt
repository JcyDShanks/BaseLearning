package com.chunyu.baselearning.android.jobscheduler

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.job.JobParameters
import android.app.job.JobService
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.AsyncTask
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.chunyu.baselearning.R

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
class NotificationJobService: JobService() {

    lateinit var mNotifyManager: NotificationManager

    companion object {
        private const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
    }

    override fun onStartJob(params: JobParameters?): Boolean {
        Log.d("SSS", "onStartJob")
        createNotificationChannel()
        val contentPaddingIntent = PendingIntent.getActivity(this, 0, Intent(this, NotificationJobActivity::class.java), PendingIntent.FLAG_UPDATE_CURRENT)
        val builder = NotificationCompat.Builder(this, PRIMARY_CHANNEL_ID)
            .setContentTitle("Job Service")
            .setContentText("这里是内容")
            .setContentIntent(contentPaddingIntent)
            .setSmallIcon(R.drawable.card_notify)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setAutoCancel(true)
        mNotifyManager.notify(0, builder.build())
        val myTask = MyTask(this) {
            Log.d("SSS", "MyTask execute")
            Thread.sleep(5000)
            jobFinished(params,true)
        }
        myTask.execute()
        return true
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        Toast.makeText(this, "reschedule my task", Toast.LENGTH_SHORT).show()
        return true
    }

    private fun createNotificationChannel() {
        Log.d("SSS", "createNotificationChannel")
        mNotifyManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(PRIMARY_CHANNEL_ID, "Job service notification", NotificationManager.IMPORTANCE_HIGH)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = "Notifications from Job Service"
            mNotifyManager.createNotificationChannel(notificationChannel)
        }

    }

    class MyTask(private val context: Context,val block: (() -> Unit)?): AsyncTask<Unit, Int, Unit>() {
        override fun doInBackground(vararg params: Unit?) {
            block?.invoke()
        }

        override fun onCancelled() {
            Toast.makeText(context, "close my task", Toast.LENGTH_SHORT).show()
        }

        override fun onPostExecute(result: Unit?) {
            Toast.makeText(context, "finish my task", Toast.LENGTH_SHORT).show()
        }
    }
}