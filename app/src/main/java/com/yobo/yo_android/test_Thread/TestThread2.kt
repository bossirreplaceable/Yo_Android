package com.yobo.yo_android.test_Thread

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

/**
 * Created by ZhangBoshi
 * on 2020-05-06
 */


class TestThread2 {









}


fun main() {

    test1()

    Thread.sleep(3000)
}

fun test1() {
    count=0
    for (i in 1..10) {
        Thread {
            run {
                testReentrantLock()
                println(Thread.currentThread().name + "---count=$count")
            }
        }.start()

    }
    println(Thread.currentThread().name + "---all-count=$count")

}

fun test2() {

    count=0

    repeat(10) {
        Thread {
            run {
                count++
                println(Thread.currentThread().name + "---count=$count")
            }
        }.start()
    }
    println(Thread.currentThread().name + "---all-count=$count")
}


var count = 0


fun testReentrantLock() {

    val mLock = ReentrantLock()

    mLock.lock()
    try {

        count++
    } finally {
        mLock.unlock()
    }


}
