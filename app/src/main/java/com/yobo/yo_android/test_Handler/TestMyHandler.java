package com.yobo.yo_android.test_Handler;

/**
 * Created by ZhangBoshi
 * on 2020-04-20
 */
public class TestMyHandler {

    private static Handler mHandler;

    public static void main(String[] args) throws InterruptedException {
        initHandler();

        Thread.sleep(3000);

        sendMessage();

    }

    private static void initHandler() {
        new Thread("Handler线程") {
            @Override
            public void run() {
                super.run();

                Looper.prepare();

                mHandler = new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {

                        if (msg.what == 1000) {
                            String result = (String) msg.obj;
                            showLog("---接受--->: " + result);
                        }
                        return false;
                    }
                }, false);

                Looper.loop();
            }
        }.start();

    }

    private static void showLog(String msg) {
        System.out.println(Thread.currentThread().getName() + ": " + msg);
    }

    private static void sendMessage() {

        new Thread("Message线程") {
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 1000;
                msg.obj = Thread.currentThread().getName() + ": 你好啊";
                Message msg1 = new Message();
                msg1.what = 1000;
                msg1.obj = Thread.currentThread().getName() + ": 你好啊1";

                mHandler.sendMessage(msg);
                mHandler.sendMessage(msg1);
            }
        }.start();

    }
}
