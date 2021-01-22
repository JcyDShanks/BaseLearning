package com.chunyu.baselearning.kotlin

import java.util.concurrent.TimeUnit
import kotlin.concurrent.thread

class StopThread {
    companion object {
        var stopRequested: Boolean = false

        @JvmStatic
        fun main(args: Array<String>) {
            var backgroundThread = Thread {
                var i = 0
                while (!stopRequested) {
                    i++
                }
            }
            backgroundThread.start()
            TimeUnit.SECONDS.sleep(1)
            stopRequested = true
        }
    }
}