package com.yobo.yo_android.test_Handler;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yobo.yo_android.R;

/**
 * Created by ZhangBoshi
 * on 2020-04-20
 */
public class TestActivity extends AppCompatActivity {

    TextView text;
    Handler mHandler;
    String tag = "TestActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        initView();
        initHandler();
    }

    @SuppressLint("HandlerLeak")
    private void initMainHandler() {
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.what == 1000) {
                    String result = (String) msg.obj;
                    text.setText(result);
                }
            }
        };
    }

    private void initView() {
        text = findViewById(R.id.handler_text);
    }

    private void initHandler() {
        new Thread("Handler线程：") {
            @Override
            public void run() {
                super.run();

                Looper.prepare();
                mHandler = new Handler(new Handler.Callback() {
                    @Override
                    public boolean handleMessage(Message msg) {

                        if (msg.what == 1000) {
                            String result = (String) msg.obj;
                            showLog("-接受到的消息:"+result);
                            text.setText(result);
                        }
                        return false;
                    }
                });
                Looper.loop();
            }
        }.start();

    }

    private void showLog(String msg) {

        Log.i(tag,Thread.currentThread().getName()+": "+msg);

    }

    public void sendMessage(View view) {

        new Thread("Message线程："){
            @Override
            public void run() {
                Message msg = new Message();
                msg.what = 1000;
                msg.obj = Thread.currentThread().getName() + ": 你好啊";

                mHandler.sendMessage(msg);
            }
        }.start();


        text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

        GestureDetector detector=new GestureDetector(new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return false;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });
        ObjectAnimator.ofFloat(text,"translationX",0,100).setDuration(300).start();

    }
}
