package com.chunyu.baselearning.kotlin

/*
* 生产类
* out 父类泛型对象 不能当成 子类泛型对象使用
* 子类泛型对象可以赋值给父类泛型对象，用 out
* */
interface Production<out T> {
    fun produce(): T
}

/*
* in 泛型 父类泛型的对象能够赋值给使用子类泛型。
* 父类泛型对象可以赋值给子类泛型对象，用 in
* */
interface Customer<in T> {
    fun consume(item: T)
}

/*
* 不加in out， 既可以作为返回值类型，也可以作为参数类型
* */
interface ProductionCustomer<T> {
    fun produce(): T
    fun consume(item: T)
}

open class Food

open class FastFood: Food()

class Hamburger: FastFood()

class FoodStore: Production<Food> {
    override fun produce(): Food {
        return Food()
    }
}

class FastFoodStore : Production<FastFood> {
    override fun produce(): FastFood {
        return FastFood()
    }
}

class InOutBurgerStore: Production<Hamburger> {
    override fun produce(): Hamburger {
        return Hamburger()
    }
}

fun main() {
//    val foodStore : Production<Hamburger> = FoodStore()
//    val fastFoodStore : Production<Hamburger> = FastFoodStore()
    val burgerStore : Production<Hamburger> = InOutBurgerStore()
    val consumer1 : Customer<Hamburger> = Everybody()
    val consumer2 : Customer<Hamburger> = People()
    val consumer3 : Customer<Hamburger> = Person()
}

class Everybody: Customer<Food> {
    override fun consume(item: Food) {
        print("Eat food")
    }
}

class People: Customer<FastFood> {
    override fun consume(item: FastFood) {
        print("Eat fast food")
    }
}

class Person: Customer<Hamburger> {
    override fun consume(item: Hamburger) {
        println("Eat burger")
    }
}