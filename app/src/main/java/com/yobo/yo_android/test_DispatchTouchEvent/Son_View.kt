package com.yobo.yo_android.test_DispatchTouchEvent

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.TextView
import com.yobo.yo_android.logMsg
import kotlin.math.abs

/**
 * Created by ZhangBoshi
 * on 2020-04-29
 */



class Son_View @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {

    var lastX:Float=0f
    var lastY:Float=0f

    override fun dispatchTouchEvent(e: MotionEvent): Boolean {

        //获取坐标
        val x = e.x
        val y = e.y

        when (e.action) {

            MotionEvent.ACTION_DOWN -> {
                parent.requestDisallowInterceptTouchEvent(true)

            }
            MotionEvent.ACTION_MOVE -> {

                val deltaX= x.minus(lastX)
                val deltaY= y.minus(lastY)

                //  横向滑动
                if (abs(deltaX) >abs(deltaY)) {

                    parent.requestDisallowInterceptTouchEvent(false)

                }else{

                //自己消费掉
                }
            }
        }
        lastX= x
        lastY= y
        return super.dispatchTouchEvent(e)
    }



//    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
//
//        logMsg("dispatchTouchEvent---Son", event?.action.toString())
//
//        return super.dispatchTouchEvent(event)
//    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        logMsg("onTouchEvent---Son", event?.action.toString())

        return super.onTouchEvent(event)
    }
}