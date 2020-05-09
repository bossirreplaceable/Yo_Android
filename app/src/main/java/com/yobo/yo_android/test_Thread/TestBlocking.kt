package com.yobo.yo_android.test_Thread

import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.PriorityBlockingQueue
import java.util.concurrent.SynchronousQueue

/**
 * Created by ZhangBoshi
 * on 2020-05-08
 */


class Dog(var name: String?, var age: Int) : Comparable<Dog> {

    override fun compareTo(other: Dog): Int {

        return when {
            age > other.age -> {
                1
            }
            age < other.age -> {
                -1
            }
            else -> {
                0
            }
        }
    }
    override fun toString(): String {
        return "Dog(name=$name, age=$age)"
    }
}

fun main() {

    val queue = PriorityBlockingQueue<Dog>()
    queue.put(Dog("小花", 6))
    queue.put(Dog("小白", 3))
    queue.put(Dog("小黑", 9))
    queue.put(Dog("小绿", 2))
    queue.put(Dog("小黄", 5))

    for (i in 0..queue.count()) {
        println(queue.take().toString())

    }

}