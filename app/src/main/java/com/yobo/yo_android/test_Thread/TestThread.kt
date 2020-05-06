package com.yobo.yo_android.test_Thread

import java.util.concurrent.*

/**
 * Created by ZhangBoshi
 * on 2020-04-30
 */


class TestThread : Thread() {
    override fun run() {
        super.run()
        println("线程1")
    }
}
fun main() {
    /**
     * 方式一：实现Thread 的子类
     */
    val t1 = TestThread()
    t1.start()

    /**
     * 方式二：实现Runnable接口
     */
    val t2 = Thread(object :Runnable {
        override fun run() {
            Thread.sleep(2000)
            println("线程2")
        }
    })
    t2.start()

    /**
     * 方式三：
     */
    val executor = Executors.newSingleThreadExecutor()

    val future: Future<String> = executor.submit(object : Callable<String> {
        override fun call(): String {
            Thread.sleep(3000)
            val result = "线程3"

            return result
        }
    })
    println(future.get())



    TimeUnit.MILLISECONDS.sleep(10000)

}


