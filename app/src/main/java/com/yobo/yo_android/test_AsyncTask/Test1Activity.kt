package com.yobo.yo_android.test_AsyncTask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yobo.yo_android.R
import com.yobo.yo_android.test_AsyncTask.Test_AsyncTask
import com.yobo.yo_android.test_AsyncTask.Test_My_AsyncTask

class Test1Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        test1()

    }


    fun test() {

        val test = Test_AsyncTask()
        test.execute(1, 2, 3)

    }

    fun test1() {

        val test = Test_My_AsyncTask()
        test.execute(1, 2, 3)

    }
}
