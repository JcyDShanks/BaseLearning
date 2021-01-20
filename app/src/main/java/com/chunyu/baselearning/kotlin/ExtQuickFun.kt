package com.chunyu.baselearning.kotlin

class ExtQuickFun {
    var map = HashMap<String, Int>()

    private fun main() {
        map.run {
            this.entries
        }.apply {
            this.toString()
        }.also {
            it.size
        }.let {
            it
        }
        with(map) {

        }

        kotlin.run {

        }
    }
}