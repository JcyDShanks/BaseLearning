package com.chunyu.baselearning.leetcode.binarytree

import android.R.attr.data


class BinaryTreeForeach {
    /*二叉树的前序遍历递归算法*/
    fun preTraverse(root: BinaryTree?) {
        if (root == null) return
        print(root.value)
        preTraverse(root.left) /*先序遍历左子树*/
        preTraverse(root.right) /*最后先序遍历右子树*/
    }
    /* 中序遍历 */
    fun midTraverse(root: BinaryTree?) {
        if (root == null) return
        midTraverse(root.left)
        print(root.value)
        midTraverse(root.right)
    }
    /* 后序遍历 */
    fun lastTraverse(root: BinaryTree?) {
        if (root == null) return
        lastTraverse(root.left)
        lastTraverse(root.right)
        print(root.value)
    }
}