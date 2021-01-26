package com.chunyu.baselearning.java
/*
* 死锁
* */
fun main() {
    val aLock = ALock()
    Thread(aLock).start()
    val bLock = BLock()
    Thread(bLock).start()
}

class DieLock {
    companion object {
        var object1 = "obj1"
        var object2 = "obj2"
    }
}

internal class ALock : Runnable {
    override fun run() {
        try {
            println("ALock 开始执行")
            while (true) {
                synchronized(DieLock.object1) {
                    println("ALock 持有 object1")
                    Thread.sleep(3000) // 等待Block先锁住object2
                    synchronized(DieLock.object2) {
                        println("ALock 持有 object2")
                        Thread.sleep(3000) // 锁住object2 不释放
                        println("ALock 释放 object2")
                    }
                    println("ALock 释放 object1")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

internal class BLock : Runnable {
    override fun run() {
        try {
            println("BLock 开始执行")
            while (true) {
                synchronized(DieLock.object2) {
                    println("BLock 持有 object2")
                    Thread.sleep(3000) // 等待Alock先锁住object1
                    synchronized(DieLock.object1) {
                        println("BLock 持有 object1")
                        Thread.sleep(3000) // 锁住object2 不释放
                        println("BLock 释放 object1")
                    }
                    println("BLock 释放 object2")
                }
            }
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
}