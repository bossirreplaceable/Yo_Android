package com.yobo.yo_android.test_Thread

import java.util.concurrent.TimeUnit

/**
 * Created by ZhangBoshi
 * on 2020-04-30
 */


fun main() {

    val t1 = Thread(Runnable {
        TimeUnit.MILLISECONDS.sleep(2000)
        print("线程1")
    })
    t1.start()

    val t2 = TestThread()
    t2.start()

    TimeUnit.MILLISECONDS.sleep(5000)
    t2.interrupt()

    TimeUnit.MILLISECONDS.sleep(10000)
}


class TestThread1 : Thread() {

    private var i: Long = 0

    override fun run() {
        super.run()
        print("线程2")

        while (!currentThread().isInterrupted) {
            TimeUnit.MILLISECONDS.sleep(500)
            i++
            println("i=$i")

        }
        print("stop")

    }

}