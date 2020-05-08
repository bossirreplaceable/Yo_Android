package com.yobo.yo_android.test_IntentService;

/**
 * Created by ZhangBoshi
 * on 2020-05-07
 */
public class aaa {


    private static volatile aaa instance=null;

    public aaa getInstance1() {
        if (instance==null) {
            synchronized (this) {
                if (instance==null) {
                    instance=new aaa();
                }
            }
        }
        return instance;
    }

}
