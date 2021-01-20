package com.chunyu.baselearning.kotlin

class Cat {
    fun run(): String {
        return "running"
    }
}

fun Cat.miao() = "miao"

fun main() {
    val cat = Cat()
    print(cat.run())
    print(cat.miao())
}