package com.chunyu.baselearning.kotlin

import kotlinx.coroutines.*
import kotlin.concurrent.thread
/*
* 阻塞 runBlocking、Thread.sleep
* 非阻塞 delay
* 挂起 coroutineScope 挂起函数 suspe
*
* */
fun main() {

    GlobalScope.launch {
        runBlocking {// 在launch中使用，提示 不适合在阻塞方法调用 Inappropriate blocking method call
            delay(2000)
            print("launch")
        }
        delay(1000)
        print("runBlocking")
    }

//    // launch方法启动了一个协程
//    // launch方法是CoroutineScope的拓展方法，
//    GlobalScope.launch {    // 启动一个新线程并继续执行
//        // 协程内执行的代码
//        delay(1000) // 非阻塞等待1s
//        print("world")      // 延迟后打印输出
//    }
//    print("hello ") // 协程等待时，主线程还在继续
////    Thread.sleep(2000)  // 阻塞主线程2s保证jvm存活
//
//    runBlocking {   // 该表达式阻塞了主线程
//        delay(2000) // 延迟2s保证jvm存活
//    }
//
//    runBlock()
//    runBlocking {
//        val job = GlobalScope.launch {
//            delay(1000)
//            print("run job")
//        }
//        print("在外部 ")
//        job.join()
//    }
//    runAndCoroutines()
}


// runBlocking 启动顶层主协程的适配器
fun runBlock() = runBlocking {// 开始执行主协程
    GlobalScope.launch(Dispatchers.IO) {    // 在后台启动一个新的协程并继续
        delay(1000)
        print("in launch ")
    }
    print("in run block")   // 主协程会立即执行
    delay(2000)     // 延迟2s保证JVM存活
}

/*
* 执行顺序
*
* */
fun runAndCoroutines() = runBlocking {
    launch {
        delay(2000)                     // 4 延迟最久
        println("Task from runBlocking")
    }

    coroutineScope {
        launch {
            delay(1000)
            println("Task from nested launch")  // 2 其次执行
        }
        delay(1000)
        println("Task from coroutine scope")  // 1 最先执行
    }
    println("Coroutine scope is over")  // 3 内嵌执行完毕后才输出？
}