package com.yobo.yo_android.test_View

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.View.OnTouchListener
import android.view.ViewConfiguration
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.yobo.yo_android.R

/**
 * Created by ZhangBoshi
 * on 2020-04-27
 */
class Test_View : AppCompatActivity() {

    lateinit var textView: TextView

    private lateinit var viewLL: ScrollView

    private lateinit var imageView: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_view)

        initView()

    }

    private fun initView() {

        textView = findViewById(R.id.view_tv)
        imageView = findViewById(R.id.view_iv)
        viewLL = findViewById(R.id.view_ll)

        imageView.setOnClickListener { show() }

//        testMotionEvent()
//        testVelocity()
//        testGesture()
        testOnDoubleTapListener()
    }


    fun show() {


        showMsg("textView.left", textView.left)
        showMsg("textView.top", textView.top)
        showMsg("textView.right", textView.right)
        showMsg("textView.bottom", textView.bottom)
        showMsg("textView.translationX", textView.translationX.toInt())
        showMsg("textView.translationY", textView.translationY.toInt())
        showMsg("textView.translationZ", textView.translationZ.toInt())
        showMsg("textView.x", textView.x.toInt())
        showMsg("textView.y", textView.y.toInt())

        showMsg("-------------------", 1)

        showMsg(" viewLL.left", viewLL.left)
        showMsg(" viewLL.top", viewLL.top)
        showMsg(" viewLL.right", viewLL.right)
        showMsg(" viewLL.bottom", viewLL.bottom)
        showMsg(" viewLL.translationX", viewLL.translationX.toInt())
        showMsg(" viewLL.translationY", viewLL.translationY.toInt())
        showMsg(" viewLL.translationZ", viewLL.translationZ.toInt())
        showMsg(" viewLL.x", viewLL.x.toInt())
        showMsg(" viewLL.y", viewLL.y.toInt())

        showMsg("-------------------", 2)
        imageView.translationX = 60f
        imageView.translationY = 60f


        showMsg("imageView.left", imageView.left)
        showMsg("imageView.top", imageView.top)
        showMsg("imageView.right", imageView.right)
        showMsg("imageView.bottom", imageView.bottom)
        showMsg("imageView.translationX", imageView.translationX.toInt())
        showMsg("imageView.translationY", imageView.translationY.toInt())
        showMsg("imageView.translationZ", imageView.translationZ.toInt())
        showMsg("imageView.x", imageView.x.toInt())
        showMsg("imageView.y", imageView.y.toInt())


    }

    fun showMsg(tag: String, msg: Int) {

        Log.e("TextView", "$tag---$msg")
    }


    fun showMsg(tag: String, msg: Float) {

        Log.e("TextView", "$tag---$msg")
    }


    @SuppressLint("ClickableViewAccessibility")
    fun testMotionEvent() {

        showMsg("TochSlop", ViewConfiguration.get(this).scaledTouchSlop)

        viewLL.setOnTouchListener(OnTouchListener { v, event ->

            showMsg("textView.x", event.x.toInt())
            showMsg("textView.y", event.y.toInt())
            showMsg("textView.rawX", event.rawX.toInt())
            showMsg("textView.rawY", event.rawY.toInt())

            false
        })


    }

    private val velocity: VelocityTracker = VelocityTracker.obtain()

    @SuppressLint("ClickableViewAccessibility")
    fun testVelocity() {

        viewLL.setOnTouchListener(OnTouchListener { v, event ->

            velocity.addMovement(event)
            velocity.computeCurrentVelocity(100)

            showMsg("velocity.xVelocity", velocity.xVelocity)
            showMsg("velocity.yVelocity", velocity.yVelocity)

            false
        })

    }


    @SuppressLint("ClickableViewAccessibility")
    fun testGesture() {

        val detector = GestureDetector(this, object : GestureDetector.OnGestureListener {
            /**
             * 手指轻轻触摸屏幕的一瞬间，由1个ACTION_DOWN触发
             */
            override fun onDown(e: MotionEvent): Boolean {
                return false
            }

            /**
             * 手指轻轻触摸屏幕，尚未松开或者拖动，由一个ACTION_DOWN触发
             * 注意：和onDown的区别，它强调的是没有松开或者拖动的状态
             */
            override fun onShowPress(e: MotionEvent) {
            }

            /**
             * 手指（轻轻触摸屏幕后）松开，伴随着一个ACTION_UP而触发，这是单击行为
             */
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                return false
            }

            /**
             * 手指按下屏幕并拖动，由一个ACTION_DOWN和多个ACTION_MOVE触发，这是拖动行为
             */
            override fun onScroll(
                e1: MotionEvent,
                e2: MotionEvent,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                return false
            }

            /**
             * 用户长久的按住屏幕不放，即长按
             */
            override fun onLongPress(e: MotionEvent) {
            }

            /**
             * 手指按下屏幕并且快速滑动后松开，由1个ACTION_DOWN、多个ACTION_MOVE和1个ACTION_UP触发，
             * 这是快速滑动行为
             */
            override fun onFling(
                e1: MotionEvent,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                return false
            }
        })
        //解决长按屏幕以后无法拖动的现象
        detector.setIsLongpressEnabled(false)

        viewLL.setOnTouchListener(OnTouchListener { v, event ->

            val consume = detector.onTouchEvent(event)

            consume
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    fun testOnDoubleTapListener() {

        val detector = GestureDetector(this, object : GestureDetector.OnGestureListener {
            /**
             * 手指轻轻触摸屏幕的一瞬间，由1个ACTION_DOWN触发
             */
            override fun onDown(e: MotionEvent): Boolean {
                return false
            }

            /**
             * 手指轻轻触摸屏幕，尚未松开或者拖动，由一个ACTION_DOWN触发
             * 注意：和onDown的区别，它强调的是没有松开或者拖动的状态
             */
            override fun onShowPress(e: MotionEvent) {
            }

            /**
             * 手指（轻轻触摸屏幕后）松开，伴随着一个ACTION_UP而触发，这是单击行为
             */
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                return false
            }

            /**
             * 手指按下屏幕并拖动，由一个ACTION_DOWN和多个ACTION_MOVE触发，这是拖动行为
             */
            override fun onScroll(
                e1: MotionEvent,
                e2: MotionEvent,
                distanceX: Float,
                distanceY: Float
            ): Boolean {
                return false
            }

            /**
             * 用户长久的按住屏幕不放，即长按
             */
            override fun onLongPress(e: MotionEvent) {
            }

            /**
             * 手指按下屏幕并且快速滑动后松开，由1个ACTION_DOWN、多个ACTION_MOVE和1个ACTION_UP触发，
             * 这是快速滑动行为
             */
            override fun onFling(
                e1: MotionEvent,
                e2: MotionEvent,
                velocityX: Float,
                velocityY: Float
            ): Boolean {
                return false
            }
        })

        detector.setOnDoubleTapListener(object : GestureDetector.OnDoubleTapListener {
            /**
             * 双击，由2次连续的点击组成，它不能和onSingleTapConfirmed共存
             */
            override fun onDoubleTap(e: MotionEvent?): Boolean {
                showMsg("onDoubleTap",1)
                return false
            }
            /**
             * 表示发生了双击行为，在双击的期间，ACTION_DOWN、ACTION_MOVE和ACTION_UP都会触发此回调
             */
            override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
                showMsg("onDoubleTapEvent",1)
                return false
            }
            /**
             * 严格的点击行为
             * 注意：它和onSingleTapUp的区别：如果触发了onSingleTapConfirmed，后面不会有任何其他的操作，
             * onSingleTapUp触发后，后面可能跟着滑动，或者双击等操作
             */
            override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                showMsg("onSingleTapConfirmed",1)
                return false
            }

        })

        //解决长按屏幕以后无法拖动的现象
        detector.setIsLongpressEnabled(false)

        viewLL.setOnTouchListener(OnTouchListener { v, event ->

            val consume = detector.onTouchEvent(event)

            consume
        })
    }


    override fun onDestroy() {
        super.onDestroy()
        velocity.clear()
        velocity.recycle()
    }


}