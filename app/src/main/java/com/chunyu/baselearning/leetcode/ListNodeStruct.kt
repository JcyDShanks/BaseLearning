package com.chunyu.baselearning.leetcode

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}


fun main() {

    val node1 = ReverseList().initData(1, 4)
    val node2 = ReverseList().initData(3, 5)
    val res = ReverseList().merge(node2, node1)
    var current: ListNode? = res
    while (current != null) {
        print(current.`val`)
        current = current.next
    }
}


class ReverseList {

    fun initData(start: Int, end: Int): ListNode? {
        var head: ListNode? = ListNode(0)
        val result = head
        for (i in start..end) {
            head?.next = ListNode(i)
            head = head?.next
        }
        return result?.next
    }

    /* 反转链表 循环 */
    fun reverseList(head: ListNode?): ListNode? {
        var prev = head
        var current = head?.next

        while (current != null) {
            val next = current.next
            prev?.next = current.next
            current.next = prev
            prev = current
            current = next
        }
        return prev
    }

    /* 反转链表 递归 */
    fun reverse(head: ListNode?): ListNode? {
//        if (head?.next == null) {
//            return head
//        }
//        val newNode = reverse(head.next)
//        head.next?.next = head
//        head.next = null
//        return newNode

        return if (head?.next == null) {
            head
        } else {
            val tmp = reverse(head.next)
            head.next?.next = head
            head.next = null
            tmp
        }
    }

    /* 移除第k个元素 */
    fun removeElements(head: ListNode?, value: Int): ListNode? {
        val sentinel = ListNode(0)
        sentinel.next = head

        var prev: ListNode? = sentinel
        var cur = head

        while (cur != null) {
            if (cur.`val` == value) {
                prev?.next = cur.next
            } else {
                prev = cur
            }
            cur = cur.next
        }
        return sentinel.next
    }

    /* 合并两个递增的链表 */
    fun merge(firstNode: ListNode?, secondNode: ListNode?): ListNode? {
        if (firstNode == null) return secondNode
        if (secondNode == null) return firstNode

        var temp: ListNode? = ListNode(-1)
        val head: ListNode? = temp //  作为结果

        var node1 = firstNode
        var node2 = secondNode
        var isUseSec = false

        while (node1 != null || node2 != null) {
            var current: ListNode? = null
            // 优先使用第一个
            if (node1 != null) {
                current = node1
                isUseSec = false
            }

            // 如果第二个列表小, temp = min
            if (node2 != null) {
                if (node1 != null) {
                    if (node2.`val` < node1.`val`) {
                        current = node2
                        isUseSec = true
                    } else {
                        current = node1
                        isUseSec = false
                    }
                } else {
                    current = node2
                    isUseSec = true
                }
            }
            // 取较小一方的下一个节点
            if (isUseSec) {
                node2 = node2?.next
            } else {
                node1 = node1?.next
            }
            // 当前结果最小值current 添加到结果链表中
            temp?.next = current
            // 指向下一个节点
            temp = temp?.next
        }
        return head?.next
    }
}

