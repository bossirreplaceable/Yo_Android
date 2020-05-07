package com.yobo.yo_android.test_Thread;

/**
 * Created by ZhangBoshi
 * on 2020-05-06
 */




public class MyReentrantLock {

    private boolean isLocked = false;
    private Thread lockedBy = null;
    private int lockedCount = 0;

    public synchronized void lock() throws InterruptedException {
        Thread thread = Thread.currentThread();
        while (isLocked && !lockedBy.equals(thread)) {
            wait();
        }
        isLocked = true;
        lockedCount++;
        lockedBy = thread;
    }

    public synchronized void unlock() {
        if (Thread.currentThread().equals(this.lockedBy)) {
            lockedCount--;
            if (lockedCount == 0) {
                isLocked = false;
                notify();
            }
        }
    }
}
