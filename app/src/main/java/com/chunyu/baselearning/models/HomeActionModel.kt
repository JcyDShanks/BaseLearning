package com.chunyu.baselearning.models

import android.view.View


data class HomeActionModel(
    var id: HomeAction,
    var desc: String,
    var listener: View.OnClickListener? = null
)

sealed class HomeAction

object BinderAction: HomeAction()
object LaunchModeAction: HomeAction()
object ActivityCallbackAction: HomeAction()
object LiveDataAction: HomeAction()
object DialogAction: HomeAction()
