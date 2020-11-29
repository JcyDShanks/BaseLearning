package com.chunyu.baselearning.kotlin

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Thread.yield

class CoroutineLearning {

    fun main() {
        // launch方法启动了一个协程
        // launch方法是CoroutineScope的拓展方法，
        GlobalScope.launch {
            // 协程内执行的代码
            delay(1000)
        }
    }
}