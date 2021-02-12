package com.chunyu.baselearning.kotlin

import kotlinx.coroutines.*
import java.lang.Thread.currentThread
import java.util.concurrent.CountDownLatch
import kotlin.concurrent.thread

class CoroutineLearning {
    private fun request(index: Int): String {
        Thread.sleep(2000)
//        countDownLatch.countDown()
        return "thread $index:"+ currentThread().name
    }

    private fun requestKotlin(index: String?): String? {
        Thread.sleep(2000)
        if (index == null) return null
        return "kotlin: $index"
    }

    private suspend fun requestCommon(index: String?): String? {
        delay(2000)
        if (index == null) {
            return null
        }
        return "Common: $index"
    }

    private var images = arrayListOf<String?>("1", "2", "3", "4", "5")

    private val countDownLatch = CountDownLatch(images.size)

    // 使用线程
    fun doThreadTask() {
        images.mapIndexed { index, s ->
            thread {
                images[index] = request(index)
            }
        }
        countDownLatch.await()
        images.forEach {
            println("images: $it")
        }
    }


    // 使用协程执行
    fun doCoroutineTask() {
        runBlocking {
            val deffer = images.mapIndexed { index, string ->
                GlobalScope.async(Dispatchers.IO) {
                    requestKotlin(string)
                }
            }
            val array = deffer.map {
                it.await()
            }

            array.forEach {
                println("array: $it")
            }

            val tasks = array.map {
               GlobalScope.async(Dispatchers.IO) {
                   requestCommon(it)
               }
            }

            val result = tasks.map { it.await() }

            result.forEach {
                println("result: $it")
            }
        }
        images.forEach {
            println("images: $it")
        }
    }

    // 使用callback回调
    private fun request(index: Int, callback: ((String) -> Unit)?) {
        Thread.sleep(2000)
        callback?.invoke("thread $index:"+ currentThread().name)
    }

    fun doCallbackTask() {
        val executeResults = arrayListOf(false, false, false, false, false)
        images.mapIndexed { index, s ->
            thread {
                request(index) {
                    images[index] = it
                    executeResults[index] = true
                }
            }
        }

        while (executeResults.contains(false)) {
            Thread.yield()
        }

        images.forEach {
            println("images: $it")
        }
    }
}

fun main() {
    CoroutineLearning().doCoroutineTask()
}