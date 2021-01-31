package com.chunyu.baselearning.kotlin

import kotlin.math.max

class FindMaxThree {
    fun findThree(array: Array<Int>): Array<Int> {
        val result = arrayOf(Int.MIN_VALUE, Int.MIN_VALUE, Int.MIN_VALUE)

        for (index in array.indices) {
            for (i in index until array.size) {
                if (array[index] < array[i]) {
                    val temp = array[index]
                    array[index] = array[i]
                    array[i] = temp
                }
            }


        }


        result[0] = array[0]
        result[1] = array[1]
        result[2] = array[2]
        return result
    }
}


fun main() {
    val intArray = arrayOf(0 , 3, 4, 1)
    val res = FindMaxThree().findThree(intArray)
}
