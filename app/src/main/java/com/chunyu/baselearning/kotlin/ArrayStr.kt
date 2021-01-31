package com.chunyu.baselearning.kotlin

class ArrayStr {
    fun sort(array: CharArray): CharArray {
        val charCountArray = IntArray(26)
        array.forEach {
            charCountArray[it - 'a'] += 1
        }
        // 取最大值
        var max = charCountArray[0]
        charCountArray.forEach {
            if (it > max) { max = it }
        }
        val charArray = CharArray(array.size)
        var resIndex = 0
        for (i in 0 until max) {
            charCountArray.forEachIndexed { index, current ->
                if (current > 0 && resIndex < array.size) {
                    charArray[resIndex] = (index + 'a'.toInt()).toChar()
                    charCountArray[index]--
                    resIndex++
                }
            }
        }
        return charArray
    }

}

fun main() {
    val charArray = charArrayOf('a', 'a', 'b', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c', 'c','d', 'd', 'd')
    val obj = ArrayStr().sort(charArray)
    obj.forEach {
        print(it)
    }
}