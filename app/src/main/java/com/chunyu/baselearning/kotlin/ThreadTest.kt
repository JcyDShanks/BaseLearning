package com.chunyu.baselearning.kotlin

import kotlin.concurrent.thread

class ThreadTest {
    fun test() {
        var parentUrl = Thread.currentThread().name
        println(parentUrl)
        thread {
            var url1 = Thread.currentThread().name
            println(url1)
        }

        thread {
            var url2 = Thread.currentThread().name
            println(url2)
        }
    }
}


fun main() {
    ThreadTest().test()
}