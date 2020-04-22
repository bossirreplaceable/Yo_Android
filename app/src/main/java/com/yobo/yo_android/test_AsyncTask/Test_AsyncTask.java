package com.yobo.yo_android.test_AsyncTask;

import android.os.AsyncTask;
import android.util.Log;

import java.util.Date;

/**
 * Created by ZhangBoshi
 * on 2020-04-21
 */
public class Test_AsyncTask extends AsyncTask<Integer, Integer, String> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        log("onPreExecute.准备");
    }

    @Override
    protected String doInBackground(Integer... integers) {

        int add = 0;
        for (Integer i : integers) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(i);

            add = add + i;
            log("doInBackground.add=" + add);
        }

        String result = "参数之和=" + add;

        return result;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        for (Integer i : values) {
            log("onProgressUpdate.进度：" + i);
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        log("onPostExecute.结果：" + s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();

    }

    private void log(String msg) {
        Log.e("Test_AsyncTask",String.format("%tr", new Date()) + ": "
                + Thread.currentThread().getName() + ": " + msg);
    }
}
