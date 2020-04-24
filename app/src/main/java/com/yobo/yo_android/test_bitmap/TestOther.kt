package com.yobo.yo_android.test_bitmap

import android.graphics.Bitmap
import android.util.LruCache

/**
 * Created by ZhangBoshi
 * on 2020-04-24
 */


fun main() {

}


class TestOther {


    lateinit var mMemoryCache: LruCache<String?, Bitmap>

    fun testLruCache() {

        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()

        val cacheSize = maxMemory / 8

        mMemoryCache = object : LruCache<String?, Bitmap>(cacheSize) {
            override fun sizeOf(key: String?, bitmap: Bitmap): Int {
                return bitmap.rowBytes * bitmap.height / 1024
            }
        }
    }


    private fun addBitmapToMemoryCache(key: String, bitmap: Bitmap) {
        if (getBitmapFromMemCache(key) == null) {
            mMemoryCache.put(key, bitmap)
        }
    }






}


