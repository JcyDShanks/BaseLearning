package com.chunyu.baselearning.kotlin

class Cat {
    fun run(): String {
        return "running"
    }
}

fun Cat.miao() = "miao"

fun main() {
    val cat = Cat()
    println(cat.miao())
    val a = "122"

    val int1: Str = Str("122")
    val int2: Str = Str("122")


    println(int1 == int2)
    println(int1 === int2)

    println(int1)
    println(int2)
}

class Str(val value: String)