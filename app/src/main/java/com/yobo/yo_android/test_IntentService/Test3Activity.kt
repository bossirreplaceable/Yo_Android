package com.yobo.yo_android.test_IntentService

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yobo.yo_android.R

class Test3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        test()

    }


    fun test() {

        val service = Intent(this, Test_IntentService::class.java)

        service.putExtra("yobo", "小狗子，你慢走！")
        startService(service)

        service.putExtra("yobo", "小狗子，我回来了！")
        startService(service)

        service.putExtra("yobo", "小狗子，我又回来了！")
        startService(service)

    }

    fun test1() {


    }
}
