package com.yobo.yo_android.test_Handler;

/**
 * Created by ZhangBoshi
 * on 2020-04-20
 */
public class Message {

    int what;

    Object obj;

    long when;

    Handler target;

    private boolean isUse;

    Runnable callback;
    /**
     * MessageQueue 以单链表的形式来存储Message对象
     */
    Message next;

    void markInUse() {
        isUse = true;
    }

    public Runnable getCallback() {
        return callback;
    }

    public Message setCallback(Runnable r) {
        callback = r;
        return this;
    }

    void recycleUnchecked() {
        what = 0;
        obj = null;
        when = 0;
        target = null;
        callback = null;
    }
}
