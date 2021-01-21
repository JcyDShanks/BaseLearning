package com.chunyu.baselearning.kotlin

class QuickSort {
    companion object {
        fun sort(array: Array<Int>, first: Int, last: Int) {

            var k = 0
            if (first >= last) {
                return
            }
            var i = first
            var j = last
            k = array[i]
            while (i != j) {
                while (array[j] >= k && i < j) { j-- }
                if (i < j) {
                    val tmp = array[j]
                    array[j] = array[i]
                    array[i] = tmp
                }
                while (array[i] <= k && i < j) { i++ }
                if (i < j) {
                    val tmp = array[i]
                    array[i] = array[j]
                    array[j] = tmp
                }
            }
            array[i] = k

            sort(array, first, i - 1)
            sort(array, i + 1, last)

        }

    }

    fun quickSort(array: IntArray): IntArray? {
        if (array.size < 2) return array // 基线条件，为空或1个元素的数组是有序的
        var pivot = array[0]
        val res = arrayListOf<Int>()
        var left = arrayListOf<Int>()
        var right = arrayListOf<Int>()

        for (i in 1 until array.size) {
            if (array[i] <= pivot) {
                left.add(array[i])
            } else if(array[i] > pivot){
                right.add(array[i])
            }
        }

        quickSort(left.toIntArray())?.forEach {
            res.add(it)
        }
        res.add(pivot)
        quickSort(right.toIntArray())?.forEach {
            res.add(it)
        }
        return res.toIntArray()
    }
}

fun main() {
    val quickSort = QuickSort().quickSort(intArrayOf(3, 4, 2, 1, 5, 6))

    quickSort?.forEach {
        print(it)
    }
}