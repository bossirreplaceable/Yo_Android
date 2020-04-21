package com.yobo.yo_android.test_Handler;

/**
 * Created by ZhangBoshi
 * on 2020-04-20
 */
public class Handler {

    private final MessageQueue mQueue;
    private final Callback mCallback;

    /**
     * Callback interface you can use when instantiating a Handler to avoid
     * having to implement your own subclass of Handler.
     */
    public interface Callback {
        boolean handleMessage(Message msg);
    }

    /**
     * Subclasses must implement this to receive messages.
     */
    public void handleMessage(Message msg) {
    }

    public Handler() {
        this(null, false);
    }

    public Handler(Callback callback, boolean async) {

        Looper mLooper = Looper.myLooper();
        if (mLooper == null) {
            throw new RuntimeException("Can't create handler inside thread "
                    + Thread.currentThread() + " that has not called Looper.prepare()");
        }
        mQueue = mLooper.mQueue;
        mCallback = callback;
    }

    /**
     * 发送消息
     */
    public final boolean sendMessage(Message msg) {
        MessageQueue queue = mQueue;
        if (queue == null) {
            return false;
        }
        return enqueueMessage(queue, msg);
    }

    private boolean enqueueMessage(MessageQueue queue, Message msg) {

        msg.target = this;

        return queue.enqueueMessage(msg, 0);
    }

    public void dispatchMessage(Message msg) {
        if (msg.callback != null) {
            handleCallback(msg);
        } else {
            if (mCallback != null) {
                if (mCallback.handleMessage(msg)) {
                    return;
                }
            }
            handleMessage(msg);
        }
    }

    private static void handleCallback(Message message) {
        message.callback.run();
    }

}
