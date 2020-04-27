package com.yobo.yo_android.test_bitmap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Looper
import android.util.Log
import android.util.LruCache
import com.jakewharton.disklrucache.DiskLruCache
import com.yobo.yo_android.test_bitmap.original.ImageLoader
import java.io.*
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by ZhangBoshi
 * on 2020-04-24
 */


fun main() {

}


class TestOther {


    lateinit var mMemoryCache: LruCache<String?, Bitmap>

    fun initLruCache() {

        val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()

        val cacheSize = maxMemory / 8

        mMemoryCache = object : LruCache<String?, Bitmap>(cacheSize) {
            override fun sizeOf(key: String?, bitmap: Bitmap): Int {
                return bitmap.rowBytes * bitmap.height / 1024
            }
        }
    }

    private fun addBitmapToMemoryCache(key: String, bitmap: Bitmap) {
        mMemoryCache.put(key, bitmap)
    }


    private fun getBitmapFromMemCache(key: String): Bitmap? {
        return mMemoryCache[key]
    }


    lateinit var mDiskLruCache: DiskLruCache

    private val DISK_CACHE_SIZE = 1024 * 1024 * 50.toLong() //50MB

    fun initDiskLruCache() {

        val diskCacheDir: File = File("A")

        mDiskLruCache = DiskLruCache.open(
            diskCacheDir, 1, 1,
            DISK_CACHE_SIZE
        )
    }


    private val DISK_CACHE_INDEX = 0

    private fun addToDiskCache(url: String) {

        var key = "图片的url转换成key"

        val editor = mDiskLruCache.edit(key)

        if (editor != null) {
            val outputStream = editor.newOutputStream(DISK_CACHE_INDEX)

            if (downloadUrlToStream(url, outputStream)) {
                editor.commit()
            } else {
                editor.abort()
            }
            mDiskLruCache.flush()
        }
    }

    private val IO_BUFFER_SIZE = 8 * 1024

    fun downloadUrlToStream(urlString: String?, outputStream: OutputStream?): Boolean {
        var urlConnection: HttpURLConnection? = null
        var out: BufferedOutputStream? = null
        var inStream: BufferedInputStream? = null

        try {
            val url = URL(urlString)
            urlConnection = url.openConnection() as HttpURLConnection

            inStream = BufferedInputStream(urlConnection.inputStream, IO_BUFFER_SIZE)

            out = BufferedOutputStream(outputStream, IO_BUFFER_SIZE)

            var b: Int
            while (inStream.read().also { b = it } != -1) {
                out.write(b)
            }

            return true
        } catch (e: IOException) {

            Log.e("Yobo", "downloadBitmap failed.$e")
        } finally {
            urlConnection?.disconnect()
            out?.close()
            inStream?.close()
        }
        return false
    }


    private fun getBitmapFromDiskCache(url: String, reqWidth: Int, reqHeight: Int): Bitmap? {

        var bitmap: Bitmap? = null

        val key: String ="url转换的key"

        val snapShot = mDiskLruCache[key]

        if (snapShot != null) {
            val fileInputStream = snapShot.getInputStream(DISK_CACHE_INDEX) as FileInputStream

            val fileDescriptor = fileInputStream.fd

            //在这里加上图片的采样率压缩
            bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor)

        }

        return bitmap
    }


}


