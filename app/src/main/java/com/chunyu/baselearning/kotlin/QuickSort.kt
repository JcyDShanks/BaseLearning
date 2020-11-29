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
}