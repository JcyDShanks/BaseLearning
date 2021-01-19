package com.chunyu.baselearning.leetcide

import kotlin.math.max

// 27
class RemoveObject {

    fun removeElement(nums: IntArray, value: Int): Int {
        var count = 0

        for (i in nums.indices) {
            if (nums[i] != value) {
                nums[count++] = nums[i];
            }
        }

        nums.forEach {
            print("${it}、")
        }
        print("\n")
        print(count)
        return count
    }
}


// 283
class MoveZeroes {
    fun moveZeroes(nums: IntArray): Unit {
        var index = 0

        nums.forEachIndexed { i, value ->
            if (value != 0) {
                nums[index] = value
                if (index != i) {
                    nums[i] = 0
                }
                index++
            }
        }
    }
}

// 485
class FindMaxConsecutiveOnes {
    fun findMaxConsecutiveOnes(nums: IntArray): Int {
        var max = 0
        var count = 0

        for (element in nums) {
            if (element == 1) {
                count++
            } else {
                count = 0
            }
            if (count >= max) {
                max = count
            }
        }
        return max
    }
}


fun main(args: Array<String>) {
    val nums = IntArray(4)
    nums[0] = 3
    nums[1] = 2
    nums[2] = 2
    nums[3] = 3
    // 27
    val removeObject = RemoveObject()
    removeObject.removeElement(nums, 3)
}