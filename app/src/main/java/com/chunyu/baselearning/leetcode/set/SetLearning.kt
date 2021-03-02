package com.chunyu.baselearning.leetcode.set

class SetLearning {
    // 1. LinkedHashSet与HashSet
    // LinkedHashSet 存储结构是一个双向链表，内部使用的是一个LinkHashMap，因此它存储的元素是有序的，遍历序和插入序是一致的。
    var hashSet = HashSet<Int>()
    var linkedHashSet = LinkedHashSet<Int>()

    var hashMap = HashMap<String, Int>()
    var linkedHashMap = LinkedHashMap<String, Int>()


    fun checkLinkedHashMap() {
        linkedHashMap.put("1", 2)
        linkedHashMap.put("2", 2)
        linkedHashMap.put("d", 2)
        linkedHashMap.put("a", 2)
        linkedHashMap.forEach {
            println(it.key)
        }
    }

    fun checkHashSet() {
        hashSet.add(2)
        hashSet.add(1)
        hashSet.add(4)
        hashSet.add(3)
        hashSet.forEach {
            println(it)
        }
    }

    fun checkLinkedHashSet() {
        linkedHashSet.add(2)
        linkedHashSet.add(1)
        linkedHashSet.add(4)
        linkedHashSet.add(3)
        linkedHashSet.forEach {
            println(it)
        }
    }

    fun checkHashMap() {
        hashMap.put("1", 2)
        hashMap.put("2", 2)
        hashMap.put("d", 2)
        hashMap.put("a", 2)
        hashMap.forEach {
            println(it.key)
        }
    }
}

fun main() {
    val setLearning = SetLearning()
    setLearning.checkLinkedHashMap()
    setLearning.checkLinkedHashSet()
    setLearning.checkHashMap()
    setLearning.checkHashSet()
}