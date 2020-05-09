package com.yobo.yo_android.test_Thread

import java.util.concurrent.ArrayBlockingQueue

/**
 * Created by ZhangBoshi
 * on 2020-05-08
 */


const val queueSize = 10
val queue = ArrayBlockingQueue<String>(queueSize)

class Consumer : Thread() {
    override fun run() {
        super.run()

        while (true) {

            val result = queue.take()
            logMsg(result)
            //消费该元素result
        }
    }
}

class Producer : Thread() {

    override fun run() {
        super.run()

        while (true) {
            queue.put(currentThread().name + "我是一个元素")
        }
    }
}

fun main() {

    val producer = Producer()
    producer.start()

    val consumer = Consumer()
    consumer.start()
}








