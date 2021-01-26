package com.chunyu.baselearning.design

import java.util.*

fun main() {
    val CAPACITY = 3
    val queue: Queue<Int> = LinkedList()
    val lock = Object()

    val producer1 = Producer("Producer-1", queue, CAPACITY, lock)
    val producer2 = Producer("Producer-2", queue, CAPACITY, lock)
    val consumer1 = Consumer("Consumer-1", queue, CAPACITY, lock)
    val consumer2 = Consumer("Consumer-2", queue, CAPACITY, lock)
    val consumer3 = Consumer("Consumer-3", queue, CAPACITY, lock)

    producer1.start()
    producer2.start()
    consumer1.start()
    consumer2.start()
    consumer3.start()
}

class Producer(val key: String, val queue: Queue<Int>, private val maxSize: Int, val lock: Object) : Thread() {

    override fun run() {
        val product = (0..100).random()
        while (true) {
            synchronized(lock) {
                while (queue.size == maxSize) {
                    try {
                        println("队列为空, 生产者-[$key] 线程 等待消费者从队列取走产品")
                        lock.wait()
                    } catch (ex: Exception) {
                        ex.printStackTrace()
                    }
                }
                println("[$key] 生产产品 : + ${product}")
                queue.offer(product)
                lock.notifyAll()
                try {
                    sleep(Random().nextInt(1000).toLong())
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }
}

class Consumer(val key: String, val queue: Queue<Int>, private val maxSize: Int, val lock: Object): Thread() {
    override fun run() {
        while (true) {
            synchronized(lock) {
                while (queue.isEmpty()) {
                    try {
                        println("队列为空, 消费者-[$key] 线程 正在等待生产者")
                        lock.wait()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                val product = queue.poll()
                println("[$key] 消费产品 : - $product")
                lock.notifyAll()
                try {
                    sleep(Random().nextInt(1000).toLong())
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }
}