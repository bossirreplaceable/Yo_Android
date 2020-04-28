package com.yobo.yo_android.test_Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yobo.yo_android.test_Handler.TestActivity

/**
 * Created by ZhangBoshi
 * on 2020-04-27
 */
class Test_Activity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    fun testStartActivity() {

        startActivityForResult(Intent(this,TestActivity::class.java),1,null)
    }
}