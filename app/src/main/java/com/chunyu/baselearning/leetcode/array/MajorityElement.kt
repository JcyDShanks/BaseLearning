package com.chunyu.baselearning.leetcode.array


/*
* leetcode 面试题 17.10. 主要元素
* */
class MajorityElement {
    fun majorityElement(nums: IntArray): Int {
        val sortedNums = nums.sorted()
        var realMax = 0
        var maxCount = 0
        var maxKey = 0
        var currentKey = nums[0]
        sortedNums.forEach {
            if (currentKey == it) {
                maxCount += 1
            } else {
                currentKey = it
                maxCount = 1
            }
            if (realMax < maxCount) {
                realMax = maxCount
                maxKey = currentKey
            }
        }
        return if (realMax > nums.size / 2) maxKey else -1
    }

    private fun quickSort(nums: IntArray): IntArray {
        if (nums.size < 2) return nums
        val pivot = nums[0]
        val res = arrayListOf<Int>()
        val left = arrayListOf<Int>()
        val right = arrayListOf<Int>()
        for (i in 1 until nums.size) {
            if (nums[i] < pivot) {
                left.add(nums[i])
            } else {
                right.add(nums[i])
            }
        }
        res.addAll(quickSort(left.toIntArray()).toList())
        res.add(pivot)
        res.addAll(quickSort(right.toIntArray()).toList())
        return res.toIntArray()
    }
}

fun main() {
    val intArray = intArrayOf(1, 2)
    val res = MajorityElement().majorityElement(intArray)
    print(res)
}