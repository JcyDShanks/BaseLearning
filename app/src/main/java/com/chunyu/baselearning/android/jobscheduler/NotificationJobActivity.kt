package com.chunyu.baselearning.android.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.os.AsyncTask
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.chunyu.baselearning.R
import kotlinx.android.synthetic.main.activity_notification_job.*
import kotlinx.coroutines.Job

class NotificationJobActivity : AppCompatActivity() {

    var selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE
    private var mScheduler: JobScheduler? = null

    private val JOB_ID = 0

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_job)
        initView()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun initView() {
        scheduleJob.setOnClickListener { scheduleJob() }
        cannelJob.setOnClickListener { cancelJob() }
        networkOptions.setOnCheckedChangeListener { group, checkedId ->
            when(networkOptions.checkedRadioButtonId) {
                R.id.noNetwork -> selectedNetworkOption = JobInfo.NETWORK_TYPE_NONE
                R.id.anyNetwork -> selectedNetworkOption = JobInfo.NETWORK_TYPE_ANY
                R.id.wifiNetwork -> selectedNetworkOption = JobInfo.NETWORK_TYPE_UNMETERED
            }
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (progress > 0) {
                    seekBarProgress.text = "${progress}s"
                } else {
                    seekBarProgress.text = "未设置"
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}

        })
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun scheduleJob() {
        mScheduler = getSystemService(JOB_SCHEDULER_SERVICE) as JobScheduler
        val serviceName = ComponentName(packageName, NotificationJobService::class.java.name)
        val builder = JobInfo.Builder(JOB_ID, serviceName)
            .setRequiredNetworkType(selectedNetworkOption)
            .setRequiresDeviceIdle(idleSwitch?.isChecked ?: false)
            .setRequiresCharging(chargingSwitch?.isChecked ?: false)

        val seekBarInt = seekBar.progress
        val seekBarSet = seekBarInt > 0

        if (seekBarSet) {
            builder.setOverrideDeadline((seekBarInt * 1000).toLong())
        }

        val constraintSet = selectedNetworkOption != JobInfo.NETWORK_TYPE_NONE || chargingSwitch?.isChecked == true || idleSwitch?.isChecked == true || seekBarSet
        if (!constraintSet) {
            Toast.makeText(this, "请至少设置1条约束条件", Toast.LENGTH_SHORT).show()
            return
        }
        val myJobInfo = builder.build()
        Log.d("SSS", "myJobInfo schedule")
        mScheduler?.schedule(myJobInfo)
        Toast.makeText(this, "job已经安排上，将在满足约束时执行", Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun cancelJob() {
        if (mScheduler == null) return
        mScheduler?.cancelAll()
        mScheduler = null
        Toast.makeText(this, "所有的job都已取消", Toast.LENGTH_SHORT).show()
    }
}