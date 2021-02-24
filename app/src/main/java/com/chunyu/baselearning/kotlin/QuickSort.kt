package com.chunyu.baselearning.kotlin

class QuickSort {
    fun quickSort(array: IntArray): IntArray? {
        if (array.size < 2) return array // 基线条件，为空或1个元素的数组是有序的
        val pivot = array[0]
        val res = arrayListOf<Int>()
        val left = arrayListOf<Int>()
        val right = arrayListOf<Int>()

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