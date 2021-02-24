package com.chunyu.baselearning.leetcode

import java.util.*

//933

class RecentCounter {

    var queue: Queue<Int> = LinkedList<Int>()

    fun ping(t: Int): Int {
        queue.offer(t)
        val min = t - 3000
        val temp = LinkedList<Int>()

        while (queue.isNotEmpty()) {
            val node = queue.poll()
            if (node != null && node >= min) {
                temp.offer(node)
            }
        }

        queue = temp
        return queue.size
    }

}