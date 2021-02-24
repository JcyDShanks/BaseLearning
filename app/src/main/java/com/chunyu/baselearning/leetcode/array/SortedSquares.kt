package com.chunyu.baselearning.leetcode.array

import kotlin.math.abs

/*
* leetcode 977. 有序数组的平方
* */
class SortedSquares {
    fun sortedSquares(nums: IntArray): IntArray {
        val absNums = nums.map {
            if (it < 0) {
                -it
            } else {
                it
            }
        }
        val new = absNums.sorted()
        return new.map { it * it }.toIntArray()
    }
}