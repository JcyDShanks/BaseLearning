package com.chunyu.baselearning.kotlin

class KotlinOptional {


    fun checkOptional() {
        var string: String? = "123"
        string = "12213"
    }
}


abstract class KotlinParent {

    var e = 0

    abstract fun getA(): Int

    fun getB(): Int {
        return 0
    }

    open fun getC() {

    }

    private fun getD(): Int {
        return 1
    }
}

fun main() {
//    var subKt = SubKt()
}

class SubKt(override var aa: Int) : KotlinParent(), IParent {

    override fun getA(): Int {
        return 0
    }

    override fun getC() {
        super.getC()
    }

    override fun countA(): Int {
        TODO("Not yet implemented")
    }

    override fun countB(): Int {
        TODO("Not yet implemented")
    }

    override fun countC(): Int {
        return super.countC()
    }

}

interface IParent {
    var aa: Int

    abstract fun countA(): Int

    fun countB(): Int

    fun countC(): Int {
        return 0
    }
}