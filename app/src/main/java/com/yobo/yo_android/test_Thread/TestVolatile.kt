package com.yobo.yo_android.test_Thread

/**
 * Created by ZhangBoshi
 * on 2020-05-07
 */


@Volatile
var count2 = 0


fun volatile1() {

    var isStop = false


    Thread {
        run {
            count1++
            logMsg("线程一开始执行------>")
            while (!isStop) {

            }
            count2++
            logMsg("线程一执行结束!")
        }
    }.start()

    Thread {
        run {
            logMsg("线程一isStop=true!")
            isStop = true
        }
    }.start()
}

//fun main() {
//
//    for (i in 1..100) {
//        volatile1()
//    }
//    Thread.sleep(3000)
//    logMsg("count1=$count1")
//    logMsg("count2=$count2")
//}

@Volatile
var count1 = 0

fun main() {

    for (i in 1..10) {
        Thread {
            run {
                for (i in 1..1000) {
                    increase()
                }
            }
        }.start()
    }
    //如果还有子线程没有执行完成，那就让主线程让出资源，
    // 保证所有的子线程都执行完成
    while (Thread.activeCount() > 2) {
        Thread.yield()
    }
    logMsg("count1=$count1")
}

fun increase() {

    count1++
}


class TestVolatile {

    @Volatile
    var lower = 0
    @Volatile
    var upper = 5

    fun setLower1(value: Int) {

        if (value <= upper) {
            lower = value
        }
    }

    fun setUpper1(value: Int) {

        if (value >= upper) {
            upper = value
        }
    }
}


class TestVolatile1{

    @Volatile
    var stop = false


    fun onInit(){
        if (stop){

        }
    }

    fun onStop(){
        stop=true
    }

}




