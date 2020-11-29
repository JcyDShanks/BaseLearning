package com.chunyu.baselearning.android

import android.content.Context
import android.content.SharedPreferences

object SharedPrefManager {
    lateinit var mContext: Context

    const val SHARED_SYSTEM = "SHARED_SYSTEM"

    fun init(context: Context) {
        mContext = context
    }

    /*
    * 获取sharedpreference
    * */
    private fun getSharedPreferences(key: String): SharedPreferences {
        return mContext.getSharedPreferences(key, Context.MODE_PRIVATE)
    }

    /*
    * 默认的sp
    * */
    fun getSystemSharedPref(): SharedPreferences {
        return getSharedPreferences(SHARED_SYSTEM)
    }

    /*
    * 保存string
    * */
    fun saveStringSync(key: String, value: String) {
        val sharedPreferences = getSystemSharedPref()
    }


}