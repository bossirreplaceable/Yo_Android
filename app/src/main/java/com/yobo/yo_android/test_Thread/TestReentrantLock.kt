package com.yobo.yo_android.test_Thread

import java.util.concurrent.locks.ReentrantLock


/**
 * Created by ZhangBoshi
 * on 2020-05-06
 */


fun main() {

    for (i in 1..10) {
        Thread {
            run {
                val test = Test()
                test.add()
                logMsg("Test-end")
            }
        }.start()
    }
}

class Test {

    var count = 10

    val mLock = ReentrantLock()

    val condition = mLock.newCondition()

    fun add() {
        mLock.lock()
        try {

            while (count % 2 != 0) {
                condition.await()
            }
            count -= 1

            condition.signalAll()
        } finally {
            mLock.unlock()
        }
    }
    val lock = Object()

    fun add1() = synchronized(lock) {

        while (count % 2 != 0) {
            lock.wait()
        }

        lock.notifyAll()
    }



    fun decrease() {
        mLock.lock()
        try {
            logMsg("Test-decrease")

        } finally {
            mLock.unlock()
        }
    }
}


class Test2 {

    val lock = MyReentrantLock()

    fun add() {
        lock.lock()
        logMsg("Test2-add")
        decrease()
        lock.unlock()
    }

    fun decrease() {
        lock.lock()
        logMsg("Test2-decrease")
        lock.unlock()
    }
}






class Test1 {

    val lock = MyLock()

    fun add() {
        lock.lock()
        logMsg("Test1-add")
        decrease()
        lock.unlock()
    }

    fun decrease() {
        lock.lock()
        logMsg("Test1-decrease")
        lock.unlock()
    }
}

fun logMsg(msg: String) {
    println(Thread.currentThread().name + ":---:$msg")
}


