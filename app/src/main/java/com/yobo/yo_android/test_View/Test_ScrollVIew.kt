package com.yobo.yo_android.test_View

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.ViewGroup
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.RotateAnimation
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Scroller
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.yobo.yo_android.R
import kotlinx.android.synthetic.main.item_test_4.*

/**
 * Created by ZhangBoshi
 * on 2020-04-28
 */
class Test_ScrollVIew : AppCompatActivity() {


    lateinit var textView: MyTextView

    private lateinit var viewLL: LinearLayout

    private lateinit var imageView: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_scroll_view)

        initView()

    }

    private fun initView() {

        textView = findViewById(R.id.view_tv)
        imageView = findViewById(R.id.view_iv)

        imageView.setOnClickListener { testHandler() }
        textView.setOnClickListener { testMove1() }
    }


    fun testOriginal() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.translation)
        animation.duration = 300
        textView.startAnimation(animation)
    }

    fun testOriginal1() {
        val animation = TranslateAnimation(0f, 100f, 0f, 100f)
        val animation1 = RotateAnimation(0f, 120f)
        val animationSet = AnimationSet(false)
        animationSet.addAnimation(animation)
        animationSet.addAnimation(animation1)
        animationSet.duration = 300
        animationSet.fillAfter = true
        textView.startAnimation(animationSet)
    }

    fun testObject() {

        ObjectAnimator.ofFloat(textView, "translationX", 0f, 100f).setDuration(300).start()
        ObjectAnimator.ofFloat(textView, "rotation", 0f, 100f).setDuration(300).start()

    }


    fun testLayout() {

        val params: ViewGroup.MarginLayoutParams =
            textView.layoutParams as ViewGroup.MarginLayoutParams
        params.width = 100
        params.topMargin = 200

        textView.requestLayout()
        //或者textView.layoutParams=params
    }


    private fun testMove1() {
        showMsg("imageView.scrollX", imageView.scrollX)
        showMsg("imageView.scrollY", imageView.scrollY)
        imageView.scrollBy(50, 50)
        showMsg("---------------滑动后------------", 1)
        showMsg("imageView.scrollX", imageView.scrollX)
        showMsg("imageView.scrollY", imageView.scrollY)
    }

    private fun testMove() {

        showMsg("textView.scrollX", textView.scrollX)
        showMsg("textView.scrollY", textView.scrollY)
        showMsg("---------------滑动后------------", 1)
        textView.scrollTo(50, 50)
        showMsg("textView.scrollX", textView.scrollX)
        showMsg("textView.scrollY", textView.scrollY)

    }


    fun testScroller1() {

        textView.smoothScrollTo(200, 200)

    }

    fun testScroller() {

        val startX = 0
        val endX = 100

        val animator = ValueAnimator.ofInt(0, 1)
        animator.duration = 1000

        animator.addUpdateListener(object : ValueAnimator.AnimatorUpdateListener {

            override fun onAnimationUpdate(animation: ValueAnimator?) {

                val fraction = animation?.animatedFraction

                textView.scrollTo((startX + (endX * fraction!!)).toInt(), 0)

            }
        })
        animator.start()

    }

    private val startX = 0
    private val endX = 200
    private var count = 0

    val handler = Handler() {

        if (it.what == 100 && count <= 30) {

            count++

            val fraction = count / 30f
            textView.scrollTo(startX + (endX * fraction).toInt(), 0)

            it.target.sendEmptyMessageDelayed(100, 30)

        }
        
        true
    }


    fun testHandler() {

        handler.sendEmptyMessage(100)
    }


    fun showMsg(tag: String, msg: Int) {

        Log.e("TextView", "$tag---$msg")
    }


}












