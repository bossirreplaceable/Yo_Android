package com.yobo.yo_android.test_View

import android.content.Context
import android.util.AttributeSet
import android.widget.Scroller
import android.widget.TextView

/**
 * Created by ZhangBoshi
 * on 2020-04-28
 */

class MyTextView
@JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
    : TextView(context, attrs, defStyleAttr) {

    private val mScroller = Scroller(context)

    fun smoothScrollTo(destX: Int, destY: Int) {

        val scrollx = scrollX  //获取当前View滑动的位置的X坐标，没滑动之前为0
        val scrolly = scrollY

        val deltaX = destX - scrollx  //计算出滑动的间距
        val deltaY = destY - scrolly

        /**
         * 这个移动动画在1000毫秒内完成
         */
        mScroller.startScroll(scrollx, scrolly, deltaX, deltaY, 1000)
        invalidate()
    }

    override fun computeScroll() {

        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.currX, mScroller.currY)
            postInvalidate()
        }
    }
}