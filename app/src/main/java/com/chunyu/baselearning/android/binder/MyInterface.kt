package com.chunyu.baselearning.android.binder

import android.os.IBinder
import android.os.IInterface

class MyInterface: IInterface {
    override fun asBinder(): IBinder? {
        return null
    }
}