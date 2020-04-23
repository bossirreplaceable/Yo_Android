package com.yobo.yo_android.test_HandlerThread

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yobo.yo_android.R
import com.yobo.yo_android.test_AsyncTask.Test_AsyncTask
import com.yobo.yo_android.test_AsyncTask.Test_My_AsyncTask

class Test2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        test()

    }


    fun test() {

        Test_Handler.testHandler()

        Test_Handler.testThread()


    }

    fun test1() {



    }
}
