package com.yobo.yo_android.test_IntentService;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

/**
 * Created by ZhangBoshi
 * on 2020-04-21
 */
public class Test_IntentService extends IntentService {

    /**
     * Creates an IntentService. Invoked by your subclass's constructor.
     */
    public Test_IntentService() {
        super("yobo");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.i("Yobo", intent.getStringExtra("yobo"));
    }
}
