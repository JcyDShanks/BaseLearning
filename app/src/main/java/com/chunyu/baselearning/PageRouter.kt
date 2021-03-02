package com.chunyu.baselearning

import android.content.Context
import android.content.Intent
import android.view.View
import com.chunyu.baselearning.android.activityCallback.ACallbackActivity
import com.chunyu.baselearning.android.binder.MyClientActivity
import com.chunyu.baselearning.android.launchMode.ALaunchActivity
import com.chunyu.baselearning.android.view.DialogActivity
import com.chunyu.baselearning.jetpack.LiveDataActivity
import com.chunyu.baselearning.models.*

object PageRouter {
    fun router(context: Context): List<HomeActionModel> {
        return listOf(
            HomeActionModel(BinderAction, "Binder通信", View.OnClickListener {
                val intent = Intent(context, MyClientActivity::class.java)
                context.startActivity(intent)
            }),
            HomeActionModel(LaunchModeAction, "Activity启动模式", View.OnClickListener {
                val intent = Intent(context, ALaunchActivity::class.java)
                context.startActivity(intent)
            }),
            HomeActionModel(LiveDataAction, "ViewModel测试", View.OnClickListener {
                val intent = Intent(context, LiveDataActivity::class.java)
                context.startActivity(intent)
            }),
            HomeActionModel(ActivityCallbackAction, "Activity回调", View.OnClickListener {
                val intent = Intent(context, ACallbackActivity::class.java)
                context.startActivity(intent)
            }),
            HomeActionModel(DialogAction, "dialog Fragment测试", View.OnClickListener {
                val intent = Intent(context, DialogActivity::class.java)
                context.startActivity(intent)
            })
        )
    }
}