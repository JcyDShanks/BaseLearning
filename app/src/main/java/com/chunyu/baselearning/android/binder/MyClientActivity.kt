package com.chunyu.baselearning.android.binder

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.chunyu.baselearning.MainActivity
import com.chunyu.baselearning.R
import kotlinx.android.synthetic.main.activity_my_client.*


class MyClientActivity : AppCompatActivity() {

    var myService: MyService? = null
    var myBinder: MyService.MyBinder? = null
    var isBinded = false

    var serviceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            Log.e("MyClient","call Server method Connect server");
            isBinded = true
            myBinder = service as MyService.MyBinder
            myService = myBinder?.getService()
            startPlayBtn.isEnabled = true
            stopPlayBtn.isEnabled = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            isBinded = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_client)
        startPlayBtn.setOnClickListener {
            serviceStartPlay()
        }
        stopPlayBtn.setOnClickListener {
            serviceStopPlay()
        }
        launchModeBtn.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
        startPlayBtn.isEnabled = false
        stopPlayBtn.isEnabled = false
    }

    override fun onStart() {
        super.onStart()
        if (!isBinded) {
            val intent = Intent(this, MyService::class.java)
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (isBinded) {
            unbindService(serviceConnection)
            isBinded = false
        }
    }

    private fun serviceStartPlay() {
        Log.e("MyClient","call serviceStartPlay");
        if (myBinder?.isPlaying() == true){
            Log.e("MyClient","music has already play!");
        }else{
            myService?.startPlay()
            Log.e("MyClient","call Server method startPlay");
        }
    }

    private fun serviceStopPlay() {
        Log.e("MyClient","call serviceStopPlay!")
        myService?.stopPlay()
    }
}