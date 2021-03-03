package com.chunyu.baselearning.leetcode.binarytree

import java.util.*


class BinaryTreeForeach {
    /*二叉树的前序遍历递归算法*/
    private fun preTraverse(root: BinaryTree?) {
        if (root == null) return
        print(root.value)
        preTraverse(root.left) /*先序遍历左子树*/
        preTraverse(root.right) /*最后先序遍历右子树*/
    }
    /* 中序遍历 */
    private fun midTraverse(root: BinaryTree?) {
        if (root == null) return
        midTraverse(root.left)
        print(root.value)
        midTraverse(root.right)
    }
    /* 后序遍历 */
    private fun lastTraverse(root: BinaryTree?) {
        if (root == null) return
        lastTraverse(root.left)
        lastTraverse(root.right)
        print(root.value)
    }

    /* 迭代实现前序遍历 */
    fun preTraverseWithIteration(root: BinaryTree?) {
        if(root == null) return

        val stack = Stack<BinaryTree>()
        stack.push(root)
        while (!stack.isEmpty()) {
            val node = stack.pop()
            print(node.value)
            if (node.left != null) {
                stack.push(node.left)
            }
            if (node.right != null) {
                stack.push(node.right)
            }
        }
    }

    /* 迭代实现中序遍历 */
    fun midTraverseWithIteration(root: BinaryTree?): List<Int> {
        var current = root
        val res: ArrayList<Int> = ArrayList()
        val linkedDeque = LinkedList<BinaryTree>()
        while (current != null || !linkedDeque.isEmpty()) {
            while (current != null) {
                linkedDeque.push(current)
                current = root?.left
            }
            val node = linkedDeque.pop()
            res.add(node.value)
            current = current?.right
        }
        return res.toList()
    }

    
}