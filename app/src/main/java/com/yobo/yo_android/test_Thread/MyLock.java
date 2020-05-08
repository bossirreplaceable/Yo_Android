package com.yobo.yo_android.test_Thread;

/**
 * Created by ZhangBoshi
 * on 2020-05-06
 */







public class MyLock {

    private static volatile MyLock instance=null;

    public static MyLock getInstance() {
        if (instance==null) {
            synchronized (MyLock.class) {
                if (instance==null) {
                     instance=new MyLock();
                }
            }
        }
        return instance;
    }

    private boolean isLocked = false;

    public synchronized void lock()
            throws InterruptedException {
        while (isLocked) {
            wait();
        }
        isLocked = true;
    }

    public synchronized void unlock() {
        isLocked = false;
        notify();
    }

}
