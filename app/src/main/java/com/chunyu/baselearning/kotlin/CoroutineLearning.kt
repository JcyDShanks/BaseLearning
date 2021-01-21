package com.chunyu.baselearning.kotlin


import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
import java.lang.Thread.currentThread
import java.util.*
import java.util.concurrent.CountDownLatch
import kotlin.concurrent.thread

class CoroutineLearning {
    fun request(index: Int): String {
        Thread.sleep(2000)
        countDownLatch.countDown()
        return "thread $index:"+ currentThread().name
    }

    private var images = arrayListOf<String?>("1", "2", "3", "4", "5")

    val countDownLatch = CountDownLatch(images.size)

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
            val deffer = images.mapIndexed { index, s ->
                GlobalScope.async {
                    request(index)
                }
            }
            val array = deffer.map {
                var temp: String?
                it.await().apply {
                    temp = this ?: ""
                }
                temp
            }
            images.clear()
            images.addAll(array)
        }

        images.forEach {
            println(it)
        }
    }

    // 使用callback回调
    fun request(index: Int, callback: ((String) -> Unit)?) {
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
    CoroutineLearning().doCallbackTask()
}