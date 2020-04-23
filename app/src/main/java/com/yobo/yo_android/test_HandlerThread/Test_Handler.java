package com.yobo.yo_android.test_HandlerThread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by ZhangBoshi
 * on 2020-04-21
 */
public class Test_Handler {

    private static Handler handler;

    public static void testHandler() {

        HandlerThread ht = new HandlerThread("Yobo线程");
        ht.start();

        Looper looper = ht.getLooper();

        handler = new Handler(looper) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if (msg.what == 1000) {
                    System.out.println(Thread.currentThread().getName() + "---" + msg.obj);
                }

            }
        };
    }

    /**
     * 输出结果： Yobo线程---你好啊,Thread-2
     */
    public static void testThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                Message msg = handler.obtainMessage(1000,
                        "你好啊," + Thread.currentThread().getName());

                handler.sendMessage(msg);
            }
        }).start();
    }

}
