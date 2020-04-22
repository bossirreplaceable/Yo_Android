package com.yobo.yo_android.test_AsyncTask;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.util.ArrayDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by ZhangBoshi
 * on 2020-04-21
 */
public abstract class AsyncTask<Params, Progress, Result> {

    private static final int MESSAGE_POST_RESULT = 0x1;
    private static final int MESSAGE_POST_PROGRESS = 0x2;

    private final WorkerRunnable<Params, Result> mWorker;
    private final FutureTask<Result> mFuture;
    private final Handler mHandler;

    public AsyncTask() {

        mHandler = new InternalHandler(Looper.getMainLooper());

        mWorker = new WorkerRunnable<Params, Result>() {
            @Override
            public Result call() {

                Result result = doInBackground(mParams);
                postResult(result);

                return result;
            }
        };

        mFuture = new FutureTask<Result>(mWorker) {
            @Override
            protected void done() {
                super.done();
                try {
                    postResult(get());
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

    }

    private void postResult(Result result) {
        Message message = mHandler.obtainMessage(MESSAGE_POST_RESULT,
                new AsyncTaskResult<>(this, result));
        message.sendToTarget();
    }

    @SafeVarargs
    public final AsyncTask<Params, Progress, Result> execute(Params... params) {
        return executeOnExecutor(sDefaultExecutor, params);
    }

    @SafeVarargs
    private final AsyncTask<Params, Progress, Result> executeOnExecutor(Executor exec,
            Params... params) {

        onPreExecute();

        mWorker.mParams = params;

        exec.execute(mFuture);

        return this;
    }

    private abstract static class WorkerRunnable<Params, Result> implements Callable<Result> {
        Params[] mParams;
    }

    private static class AsyncTaskResult<Data> {
        final AsyncTask mTask;
        final Data[] mData;

        @SafeVarargs
        AsyncTaskResult(AsyncTask task, Data... data) {
            mTask = task;
            mData = data;
        }
    }

    private static class InternalHandler extends Handler {
        InternalHandler(Looper looper) {
            super(looper);
        }

        @SuppressWarnings({ "unchecked", "RawUseOfParameterizedType" })
        @Override
        public void handleMessage(Message msg) {
            AsyncTaskResult<?> result = (AsyncTaskResult<?>) msg.obj;
            switch (msg.what) {
            case MESSAGE_POST_RESULT:
                // There is only one result
                result.mTask.finish(result.mData[0]);
                break;
            case MESSAGE_POST_PROGRESS:
                result.mTask.onProgressUpdate(result.mData);
                break;
            }
        }
    }

    private void finish(Result result) {
        onPostExecute(result);
    }

    final void publishProgress(Progress... values) {
        mHandler.obtainMessage(MESSAGE_POST_PROGRESS, new AsyncTaskResult<>(this, values))
                .sendToTarget();
    }

    /**
     * 运行在主线程中
     */
    protected void onPreExecute() {
    }

    /**
     * 运行在线程池中
     */
    protected abstract Result doInBackground(Params... params);

    /**
     * 运行在主线程之中
     */
    protected void onProgressUpdate(Progress... values) {
    }

    /**
     * 运行在主线程之中
     */
    protected void onPostExecute(Result result) {
    }

    /**
     * -------------------------线程池的创建------------------------------------------
     */
    private static final Executor sDefaultExecutor = new SerialExecutor();
    private static final Executor THREAD_POOL_EXECUTOR;

    private static final BlockingQueue<Runnable> sPoolWorkQueue = new LinkedBlockingQueue<>(128);

    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        private final AtomicInteger mCount = new AtomicInteger(1);

        public Thread newThread(Runnable r) {
            return new Thread(r, "线程-Yobo-" + mCount.getAndIncrement());
        }
    };
    /**
     * CPU核心数量
     */
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    static {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(CPU_COUNT - 1,
                CPU_COUNT * 2 + 1, 30, TimeUnit.SECONDS, sPoolWorkQueue, sThreadFactory);
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        THREAD_POOL_EXECUTOR = threadPoolExecutor;
    }

    private static class SerialExecutor implements Executor {
        final ArrayDeque<Runnable> mTasks = new ArrayDeque<>();
        Runnable mActive;

        public synchronized void execute(final Runnable r) {
            mTasks.offer(new Runnable() {
                public void run() {
                    try {
                        r.run();
                    } finally {
                        scheduleNext();
                    }
                }
            });
            if (mActive == null) {
                scheduleNext();
            }
        }

        synchronized void scheduleNext() {
            if ((mActive = mTasks.poll()) != null) {
                THREAD_POOL_EXECUTOR.execute(mActive);
            }
        }
    }

}
