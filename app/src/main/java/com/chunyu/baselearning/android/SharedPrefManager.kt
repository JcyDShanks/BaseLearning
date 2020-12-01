package com.chunyu.baselearning.android

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils

/**
 * 一个SharedPreference 工具
 */
object SharedPrefManager {

    private const val SHARED_PREF_SETTING = "SHAREDPREF_SETTING"

    lateinit var mContext: Context

    fun init(context: Context) {
        mContext = context
    }

    private fun getSharedPreferences(key: String): SharedPreferences? {
        return mContext.getSharedPreferences(key, Context.MODE_PRIVATE)
    }

    fun getSystemSharedPref(): SharedPreferences? {
        return getSharedPreferences(SHARED_PREF_SETTING)
    }


    /*------------------------------------add---------------------------------------*/
    /*
    * 保存string
    * */
    fun saveString(key: String, value: String, isSync: Boolean = true) {
        checkKey(key)
        val preferences = getSystemSharedPref()
        val edit = preferences?.edit()
        edit?.putString(key, value)
        if (isSync) {
            edit?.commit()
        } else {
            edit?.apply()
        }
    }

    fun saveInt(key: String, value: Int, isSync: Boolean = true) {
        checkKey(key)
        val preferences = getSystemSharedPref()
        val edit = preferences?.edit()
        edit?.putInt(key, value)
        if (isSync) {
            edit?.commit()
        } else {
            edit?.apply()
        }
    }

    fun saveLong(key: String, value: Long, isSync: Boolean = true) {
        checkKey(key)
        val preferences = getSystemSharedPref()
        val edit = preferences?.edit()
        edit?.putLong(key, value)
        if (isSync) {
            edit?.commit()
        } else {
            edit?.apply()
        }
    }

    fun saveFloat(key: String, value: Float, isSync: Boolean = true) {
        checkKey(key)
        val preferences = getSystemSharedPref()
        val edit = preferences?.edit()
        edit?.putFloat(key, value)
        if (isSync) {
            edit?.commit()
        } else {
            edit?.apply()
        }
    }

    fun saveBoolean(key: String, value: Boolean, isSync: Boolean = true) {
        checkKey(key)
        val preferences = getSystemSharedPref()
        val edit = preferences?.edit()
        edit?.putBoolean(key, value)
        if (isSync) {
            edit?.commit()
        } else {
            edit?.apply()
        }
    }

    fun saveStringSet(key: String, value: Set<String>, isSync: Boolean = true) {
        checkKey(key)
        val preferences = getSystemSharedPref()
        val edit = preferences?.edit()
        edit?.putStringSet(key, value)
        if (isSync) {
            edit?.commit()
        } else {
            edit?.apply()
        }
    }

/*---------------------------------read-----------------------------------------*/

    fun readString(key: String, default: String?): String? {
        checkKey(key)
        val sharedPreferences = getSystemSharedPref()
        return sharedPreferences?.getString(key, default)
    }

    fun readInt(key: String, default: Int): Int {
        checkKey(key)
        val sharedPreferences = getSystemSharedPref()
        return sharedPreferences?.getInt(key, default) ?: default
    }

    fun readFloat(key: String, default: Float): Float {
        checkKey(key)
        val sharedPreferences = getSystemSharedPref()
        return sharedPreferences?.getFloat(key, default) ?: default
    }

    fun readLong(key: String, default: Long): Long {
        checkKey(key)
        val sharedPreferences = getSystemSharedPref()
        return sharedPreferences?.getLong(key, default) ?: default
    }

    fun readBoolean(key: String, default: Boolean): Boolean {
        checkKey(key)
        val sharedPreferences = getSystemSharedPref()
        return sharedPreferences?.getBoolean(key, default) ?: default
    }

    fun readStingSet(key: String, default: Set<String>?): Set<String>? {
        checkKey(key)
        val sharedPreferences = getSystemSharedPref()
        return sharedPreferences?.getStringSet(key, default)
    }

/*---------------------------------remove---------------------------------------*/

    /**
     * Context.MODE_PRIVATE（0）：默认模式，创建的文件只能由 调用的应用程序（或者共享相同用户ID的应用程序）访问。
     * 后面3种已不推荐使用, 详见 @see Context.PreferencesMode
     *
     * commit 同步
     * apply 异步提交到磁盘
     *
     * 若调用apply，而apply仍未提交到磁盘，此时再调用commit会造成commit阻塞
     */
    fun remove(key: String, isSync: Boolean = true) {
        checkKey(key)
        val sharedPreferences = getSharedPreferences(key)
        sharedPreferences?.edit()?.remove(key).apply {
            if (isSync) {
                this?.commit()
            } else {
                this?.apply()
            }
        }
    }


    fun clearSharedPref(preferences: SharedPreferences?) {
        if (preferences == null) {
            return
        }
        val edit = preferences.edit()
        edit.clear()
        edit.commit()
    }

    private fun checkKey(key: String) {
        require(!TextUtils.isEmpty(key)) { "key should not be null or empty" }
    }
}