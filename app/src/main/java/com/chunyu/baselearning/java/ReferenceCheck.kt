package com.chunyu.baselearning.java

import java.lang.ref.SoftReference
import java.lang.ref.WeakReference
import kotlin.concurrent.thread

class ReferenceCheck {
    fun checkSoft() {
        var a = 1
        // 软引用对象集合
        var list = arrayListOf<SoftReference<Int>>()
        val referenceA = SoftReference<Int>(++a)
        val referenceB = WeakReference<Int>(++a)
        while (true) {

        }

        while (true) {
            System.gc()
            System.runFinalization()
            if (referenceA.get() == null) {
                println("referenceA == null : ${referenceA.get() == null}")
                break
            } else {
                println("referenceA == null : ${referenceA.get() == null}")
            }
        }
    }

}

fun main() {
    val check = ReferenceCheck().checkSoft()
}