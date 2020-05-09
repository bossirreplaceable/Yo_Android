package com.yobo.yo_android.test_DispatchTouchEvent

import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.yobo.yo_android.R
import com.yobo.yo_android.logMsg
import kotlinx.android.synthetic.main.activity_test_dispatch.*

/**
 * Created by ZhangBoshi
 * on 2020-04-29
 */

class Dispatch_Activity : AppCompatActivity() {


    lateinit var child: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_dispatch)
        initView()



        testView()

    }


    fun testView() {

        val layoutView = window.decorView.findViewById<ViewGroup>(android.R.id.content)[0]

        val son=layoutView.findViewById<TextView>(R.id.test_son)

        son.setOnClickListener {
            logMsg("setOnClickListener---son","点击了son")

        }

        son.text = "你好"

    }


    fun initView() {


        test_grandFather.setOnTouchListener { v, event ->
            logMsg("OnTouchListener.onTouch---grandFather", event?.action.toString())
            false
        }

        test_father.setOnTouchListener(object : View.OnTouchListener {

            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                logMsg("OnTouchListener.onTouch---father", event?.action.toString())
                return false
            }
        })

        test_father.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                logMsg("OnClickListener.onClick---father", "点击事件")

            }

        })
    }


    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {

        logMsg("dispatchTouchEvent---Activity", event?.action.toString())

        return super.dispatchTouchEvent(event)
    }


    override fun onTouchEvent(event: MotionEvent?): Boolean {

        logMsg("onTouchEvent---Activity", event?.action.toString())

        return super.onTouchEvent(event)
    }


//    /**
//     * 1、用来进行事件分发，如果事件传递到该View，那么该方法一定会被调用；
//     * 2、返回的结果受到当前View的onTouchEvent()和下级View的dispatchTouchEvent()影响
//     */
//    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
//
//        return super.dispatchTouchEvent(ev)
//    }
//
//    /**
//     * 1、该方法决定当前View是否需要拦截某个事件；
//     * 2、如果拦截了点击事件，返回结果ture，并且它的下级View将接受不到点击事件
//     */
//    fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
//        return true
//    }
//
//    /**
//     *1、该方法用来消费事件，
//     * 2、如果你处理的点击事件，返回结果True，那么它的上级View将不会再接收到点击事件
//     */
//    override fun onTouchEvent(ev: MotionEvent?): Boolean {
//        return super.onTouchEvent(ev)
//    }


}