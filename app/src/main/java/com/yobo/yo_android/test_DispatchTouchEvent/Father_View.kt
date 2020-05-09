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



class Father_View @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {


    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {

        return e?.action != MotionEvent.ACTION_DOWN

    }































//    override fun onInterceptTouchEvent(e: MotionEvent?): Boolean {
//
//        var intercept=false
//
//        //获取坐标
//        val x=e?.x
//        val y=e?.y
//
//        when(e?.action) {
//
//            MotionEvent.ACTION_DOWN->{
//
//                intercept=false
//            }
//            MotionEvent.ACTION_MOVE->{
//              /*
//                if (横向滑动){
//                   intercept=true
//                }else{
//                   intercept=false
//                }
//                */
//            }
//            MotionEvent.ACTION_UP->{
//
//                intercept=false
//            }
//
//        }
//        return intercept
//    }


    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {

        logMsg("dispatchTouchEvent---Father", event?.action.toString())

        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        logMsg("onTouchEvent---Father", event?.action.toString())

        return super.onTouchEvent(event)
    }



}