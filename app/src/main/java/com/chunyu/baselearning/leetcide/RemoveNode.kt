package com.chunyu.baselearning.leetcide


class RemoveNode {

    fun remove(head: ListNode?, k: Int): ListNode? {
        var count = k
        var node1 = head
        var node2 = head
        var prev = head
        while (node1 != null && count > 0) {
            node1 = node1.next
            count--
        }
        while (node1 != null) {
            node1 = node1.next
            prev = node2
            node2 = node2!!.next
        }
        prev?.next = node2?.next
        return head
    }
}
