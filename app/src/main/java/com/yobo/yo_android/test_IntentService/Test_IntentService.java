package com.yobo.yo_android.test_IntentService;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

/**
 * Created by ZhangBoshi
 * on 2020-04-21
 */
public class Test_IntentService extends IntentService {

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public Test_IntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
