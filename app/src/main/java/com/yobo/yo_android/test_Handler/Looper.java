package com.yobo.yo_android.test_Handler;

/**
 * Created by ZhangBoshi
 * on 2020-04-20
 */
class Looper {

    static final ThreadLocal<Looper> sThreadLocal = new ThreadLocal<>();
    /**
     * 当前线程
     */
    private final Thread mThread;

    MessageQueue mQueue;

    private Looper(boolean quitAllowed) {
        mQueue = new MessageQueue(quitAllowed);
        mThread = Thread.currentThread();
    }

    static void prepare() {
        if (sThreadLocal.get() != null) {
            throw new RuntimeException("Only one Looper may be created per thread");
        }
        sThreadLocal.set(new Looper(true));
    }

    static Looper myLooper() {
        return sThreadLocal.get();
    }

    static void loop() {

        Looper me = myLooper();

        if (me == null) {
            throw new RuntimeException("No Looper; Looper.prepare() wasn't called on this thread.");
        }
        MessageQueue queue = me.mQueue;

        for (;;) {
            Message msg = queue.next(); // might block

            if (msg == null) {
                return;
            }

            try {
                msg.target.dispatchMessage(msg);

            } catch (Exception e) {
                e.printStackTrace();
            }

            msg.recycleUnchecked();
        }
    }
}
