package com.chunyu.baselearning.reflact

import android.view.View
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method

class ProxyHandler(private val listener: View.OnClickListener): InvocationHandler {
    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
        return method?.invoke(listener, args)
    }
}