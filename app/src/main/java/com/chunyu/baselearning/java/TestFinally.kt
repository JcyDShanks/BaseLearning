package com.chunyu.baselearning.java

import java.lang.Exception

fun main() {
    A()
}

fun A(): String {
    try {
        val a = "!2"
        throw Exception()
        print("1111")
    } catch (e: Exception) {
        print("e-call")
        return "e null"
    } finally {
        print("finish")
    }
    return "null"
}