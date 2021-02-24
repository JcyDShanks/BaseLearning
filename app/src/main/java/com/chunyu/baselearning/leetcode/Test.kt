package com.chunyu.baselearning.leetcode
/*
* 递归
* 分析思路：将问题缩小范围？
* */
class Test {
    fun act(x: Int): Int {
        return if (x == 1) {
            1
        } else {
            x * act(x - 1)
        }
    }

    fun printReverse(str: CharArray) {
        help(0, str)
    }

    private fun help(index: Int, str: CharArray?) {
        if (str == null || index >= str.size) return
        help(index + 1, str)
        println(str[index])
    }

    // help0 { help1 { help2 {  help3() } } }

    // help3 - print
    // help2 - print
    // help1 - print
    // node0 - node1 - node2 - node3
    //

    // 思路
    // 取数组中间位置 k = s.size / 2 + 1
    // 将i in 0..k
    // 0 -- length - 1
    // 1 -- length - 2
    // 2 -- length - 3
    // i -- (s.size - i - 1)
    // 递归条件
    // i <= k

    //
    fun reverseString(s: CharArray): CharArray {
        helpRv(0, s)
        return s
    }

    private fun helpRv(index: Int, s: CharArray) {
        if (index >= (s.size + 1) / 2) return
        helpRv(index + 1, s)
        val temp = s[index]
        s[index] = s[s.size - index - 1]
        s[s.size - index - 1] = temp
    }

    // 1 - 2 - 3 - 4
    fun swapPairs(head: ListNode?): ListNode? {
        return head
    }
    // 1 - 2 - 3 - 4
    // head 1 - next 2 - 3 - 4
    // head 1 - 3 - 4
    // next 2 - head 1 - 3 - 4

    // head 3 - next 4
    // head 3 - null
    // next 4 - head 3 - null
//    public ListNode help(ListNode before){
//        if(before == null || before.next == null){
//            return before;
//        }
//        ListNode after = before.next;
//        before.next = help(after.next);
//        after.next = before;
//        return after;
//    }

    private fun swapNode(before: ListNode?): ListNode? {
        if (before?.next == null) return before
        val after = before.next
        before.next = swapNode(after?.next)
        after?.next = before
        return after
    }
    // 1 [1] list[0]
    // 2 [1, 1]

    fun generate(numRows: Int): List<List<Int>> {
        val ret = ArrayList<List<Int>>()
        for (i in 0..numRows) { // 第1行开始 ，到第numRows行
            val row = ArrayList<Int>()
            for (j in 1 until i) { // 从0 到 当前行
            if (j == 0 || j == i) {
                row.add(1)
            } else {
                row.add(ret[i - 1][j - 1] + ret[i - 1][j])
            }
        }
            ret.add(row)
        }
        return ret
    }

}

fun main() {
    val c = charArrayOf('1', '2', 'b', 'd','3', 'a','4')
    val new = Test().reverseString(c)
    new.forEach {
        println(it)
    }
}