package com.yobo.yo_android.test_bitmap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.RectF
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.yobo.yo_android.R
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import kotlin.math.roundToInt


/**
 * Created by ZhangBoshi
 * on 2020-04-24
 */


class TestBitmap : AppCompatActivity() {

    private val tag = "TestBitmap"

    private lateinit var iv: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_bitmap)
        initView()
        testQualityCompress()
    }

    private fun initView() {

        iv = findViewById(R.id.bitmap_iv)

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.girls)


        Log.e(tag, "原始Bitmap.width=${bitmap.width},原始Bitmap.height=${bitmap.height}")

        iv.setImageBitmap(bitmap)
    }

    fun smapleClick(view: View) {

        val reqWidth = dp2px(200f)
        val reqHeight = dp2px(150f)


        testInSampleSize(iv, reqWidth, reqHeight)

    }

    fun testInSampleSize(imageView: ImageView, reqWidth: Float, reqHeight: Float) {


        /**
         * 第一步：设置inJustDecodeBounds=true,去解析原始图片的宽高。
         * 注意：这里并不会把图片真正地加载下来，只是解析获取了图片的信息，轻量级的。
         */
        val option = BitmapFactory.Options()

        option.inJustDecodeBounds = true

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.girls, option)


        Log.e(tag, "原始option.width=${option.outWidth},原始option.height=${option.outHeight}")


        /**
         * 第二步：计算inSampleSize 的大小
         */
        val smapleSize_width = option.outWidth / reqWidth
        val smapleSize_height = option.outHeight / reqHeight

        Log.e(
            tag,
            "smapleSize_width=${smapleSize_width.roundToInt()},smapleSize_height=${smapleSize_height.roundToInt()}"
        )

        /**
         * 第三步：加载原始图片，并且按照采样率进行压缩尺寸大小
         */
        option.inSampleSize = smapleSize_width.roundToInt()

        option.inJustDecodeBounds = false

        val bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.girls, option)

        Log.e(tag, "压缩后Bitmap.width=${bitmap1.width},压缩后Bitmap.height=${bitmap1.height}")
        Log.e(tag, "压缩后option.width=${option.outWidth},压缩后option.height=${option.outHeight}")

        imageView.setImageBitmap(bitmap1)

    }


    fun dp2px(dp: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, dp,
            this.resources.displayMetrics
        )
    }


    fun testQualityCompress() {
        /**
         *第一步：创建本地quality.jpg
         */
        val path = Environment.getExternalStorageDirectory()

        val imageFile = File(path, "quality.jpg")

        Log.e("Yobo", "path" + imageFile.absolutePath)


        /**
         * 第二步：将图片解析成Bitmap
         */
        val option = BitmapFactory.Options()

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.girls, option)


        /**
         * 第三步：设置压缩图片的质量，quality取值为1～100，压缩图片
         */
        val quality = 50

        val baos = ByteArrayOutputStream()

        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos)


        /**
         * 第四步：将图片保存到本地
         */
        try {
            val fos = FileOutputStream(imageFile)

            fos.write(baos.toByteArray())

            fos.flush()

            fos.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun compressBitmapToFileBySize() {

        /**
         * 第一步：创建本地sizeCompress.jpg
         */
        val path = Environment.getExternalStorageDirectory()

        val imageFile = File(path, "sizeCompress.jpg")


        /**
         * 第二步：将图片解析成Bitmap
         */
        val option = BitmapFactory.Options()

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.girls, option)


        /**
         * 第三步：根据压缩后的宽高，重新绘制Bitmap
         * ratio-- 压缩尺寸倍数，值越大，图片的尺寸就越小
         */
        val ratio = 4
        val result = Bitmap.createBitmap(
            bitmap.width / ratio,
            bitmap.height / ratio,
            Bitmap.Config.ARGB_8888
        )

        val canvas = Canvas(result)
        val rect = RectF(
            0f, 0f, (bitmap.width / ratio).toFloat(), (bitmap.height / ratio).toFloat()
        )
        canvas.drawBitmap(bitmap, null, rect, null)

        /**
         * 第四步：将图片保存到本地
         */
        val baos = ByteArrayOutputStream()

        result.compress(Bitmap.CompressFormat.JPEG, 100, baos)

        try {
            val fos = FileOutputStream(imageFile)
            fos.write(baos.toByteArray())
            fos.flush()
            fos.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}