package com.yobo.yo_android.test_DispatchTouchEvent

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.RelativeLayout
import com.yobo.yo_android.logMsg

/**
 * Created by ZhangBoshi
 * on 2020-04-29
 */

class Grandfather_View @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr){


    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {

        logMsg("onInterceptTouchEvent---Grandfather", ev?.action.toString())

        return super.onInterceptTouchEvent(ev)
    }


    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {

        logMsg("dispatchTouchEvent---Grandfather", event?.action.toString())

        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        logMsg("onTouchEvent---Grandfather", event?.action.toString())

        return super.onTouchEvent(event)
    }
}
