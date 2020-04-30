package com.yobo.yo_android.test_DispatchTouchEvent

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.TextView
import com.yobo.yo_android.logMsg

/**
 * Created by ZhangBoshi
 * on 2020-04-29
 */
class Son_View @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr) {


    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {

        logMsg("dispatchTouchEvent---Son", event?.action.toString())

        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        logMsg("onTouchEvent---Son", event?.action.toString())

        return super.onTouchEvent(event)
    }
}