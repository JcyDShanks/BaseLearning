package com.chunyu.baselearning.leetcide

import java.util.*

class StackStruct {
    val stack = Stack<Char>()
    // 28 () 29
    // 91 [] 93
    // 123 {} 125

    fun isValid(s: String): Boolean {
        val charArray = s.toCharArray()
        for (char in charArray) {
            if (stack.isEmpty()) {
                stack.push(char)
            } else {
                if (char == ')') {
                    if (stack.peek() == '(') {
                        stack.pop()
                        continue
                    }
                } else if (char == ']') {
                    if (stack.peek() == '[') {
                        stack.pop()
                        continue
                    }
                } else if (char == '}') {
                    if (stack.peek() == '{') {
                        stack.pop()
                        continue
                    }
                }
                stack.push(char)
            }
        }
        return stack.empty()
    }
}