package com.chunyu.baselearning.android.uitools

import android.annotation.TargetApi
import android.app.AppOpsManager
import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.res.Configuration
import android.os.Binder
import android.os.Build
import android.os.Environment
import android.text.TextUtils
import java.io.Closeable
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.lang.reflect.Method
import java.util.*
import java.util.regex.Pattern

object DeviceHelper {
    private const val KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name"
    private const val KEY_FLYME_VERSION_NAME = "ro.build.display.id"
    private const val FLYME = "flyme"
    private val MEIZUBOARD = arrayOf("m9", "M9", "mx", "MX")
    private var sMiuiVersionName: String? = null
    private var sFlymeVersionName: String? = null
    private var sIsTabletChecked = false
    private var sIsTabletValue = false

    /**
     * 判断是否是flyme系统
     */
    val isFlyme: Boolean
        get() = !TextUtils.isEmpty(sFlymeVersionName) && sFlymeVersionName!!.contains(FLYME)

    /**
     * 判断是否是MIUI系统
     */
    val isMIUI: Boolean
        get() = !TextUtils.isEmpty(sMiuiVersionName)

    val isMIUIV5: Boolean
        get() = "v5" == sMiuiVersionName

    val isMIUIV6: Boolean
        get() = "v6" == sMiuiVersionName

    val isMIUIV7: Boolean
        get() = "v7" == sMiuiVersionName

    val isMIUIV8: Boolean
        get() = "v8" == sMiuiVersionName

    val isMIUIV9: Boolean
        get() = "v9" == sMiuiVersionName

    //查不到默认高于5.2.4
    val isFlymeVersionHigher5_2_4: Boolean
        get() {
            var isHigher = true
            if (sFlymeVersionName != null && sFlymeVersionName != "") {
                val pattern = Pattern.compile("(\\d+\\.){2}\\d")
                val matcher = pattern.matcher(sFlymeVersionName!!)
                if (matcher.find()) {
                    val versionString = matcher.group()
                    if (versionString != "") {
                        val version = versionString.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                        if (version.size == 3) {
                            if (Integer.valueOf(version[0]) < 5) {
                                isHigher = false
                            } else if (Integer.valueOf(version[0]) > 5) {
                                isHigher = true
                            } else {
                                if (Integer.valueOf(version[1]) < 2) {
                                    isHigher = false
                                } else if (Integer.valueOf(version[1]) > 2) {
                                    isHigher = true
                                } else {
                                    if (Integer.valueOf(version[2]) < 4) {
                                        isHigher = false
                                    } else if (Integer.valueOf(version[2]) >= 5) {
                                        isHigher = true
                                    }
                                }
                            }
                        }

                    }
                }
            }
            return isMeizu && isHigher
        }

    /**
     * 判断是否为魅族
     */
    val isMeizu: Boolean
        get() = isPhone(MEIZUBOARD) || isFlyme

    /**
     * 判断是否为小米
     */
    val isXiaomi: Boolean
        get() = Build.BRAND.toLowerCase().contains("xiaomi")

    init {
        var fileInputStream: FileInputStream? = null
        try {
            fileInputStream = FileInputStream(File(Environment.getRootDirectory(), "build.prop"))
            val properties = Properties()
            properties.load(fileInputStream)
            val clzSystemProperties = Class.forName("android.os.SystemProperties")
            val getMethod = clzSystemProperties.getDeclaredMethod("get", String::class.java)
            // miui
            sMiuiVersionName = getLowerCaseName(properties, getMethod, KEY_MIUI_VERSION_NAME)
            //flyme
            sFlymeVersionName = getLowerCaseName(properties, getMethod, KEY_FLYME_VERSION_NAME)

        } catch (e: Exception) {

        } finally {
            close(fileInputStream)
        }
    }

    private fun close(c: Closeable?) {
        if (c != null) {
            try {
                c.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

//    private val _isTablet: Boolean
//        get() {
//            return Application.configuration.screenLayout and
//                    Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
//        }


    /**
     * 判断是否为平板设备
     */
//    private val isTablet: Boolean
//        get() {
//            if (sIsTabletChecked) {
//                return sIsTabletValue
//            }
//            sIsTabletValue = _isTablet
//            sIsTabletChecked = true
//            return sIsTabletValue
//        }

    private fun isPhone(boards: Array<String>): Boolean {
        val board = Build.BOARD ?: return false
        for (board1 in boards) {
            if (board == board1) {
                return true
            }
        }
        return false
    }

    /**
     * 判断悬浮窗权限（目前主要用户魅族与小米的检测）。
     */
//    val isFloatWindowOpAllowed: Boolean
//        get() {
//            val context = SFApplication.instance
//            val version = Build.VERSION.SDK_INT
//            return if (version >= 19) {
//                checkOp(context, 24)  // 24 是AppOpsManager.OP_SYSTEM_ALERT_WINDOW 的值，该值无法直接访问
//            } else {
//                try {
//                    context.applicationInfo.flags and (1 shl 27) == 1 shl 27
//                } catch (e: Exception) {
//                    e.printStackTrace()
//                    false
//                }
//            }
//        }


//    @TargetApi(19)
//    private fun checkOp(context: Context = SFApplication.instance, op: Int): Boolean {
//        val version = Build.VERSION.SDK_INT
//        if (version >= Build.VERSION_CODES.KITKAT) {
//            val manager = context.getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
//            try {
//                val method = manager.javaClass.getDeclaredMethod("checkOp", Int::class.javaPrimitiveType, Int::class.javaPrimitiveType, String::class.java)
//                val property = method.invoke(manager, op,
//                    Binder.getCallingUid(), context.packageName) as Int
//                return AppOpsManager.MODE_ALLOWED == property
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//
//        }
//        return false
//    }

    private fun getLowerCaseName(p: Properties, get: Method, key: String): String? {
        var name: String? = p.getProperty(key)
        if (name == null) {
            try {
                name = get.invoke(null, key) as String
            } catch (ignored: Exception) {
            }

        }
        if (name != null) name = name.toLowerCase()
        return name
    }
}