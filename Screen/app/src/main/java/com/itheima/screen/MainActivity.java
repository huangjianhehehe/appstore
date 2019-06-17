package com.itheima.screen;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ScreenReceiver screenReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //动态的去注册广播接收者
        screenReceiver = new ScreenReceiver();

//        <intent-filter>
//                <action android:name="android.intent.action.SCREEN_OFF"/>
//                <action android:name="android.intent.action.SCREEN_ON"/>
//            </intent-filter>
        //创建intentfilter对象
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        //动态注册广播接收者
        registerReceiver(screenReceiver,intentFilter);


    }
    //当Activity销毁时调用取消注册

    @Override
    protected void onDestroy() {
        unregisterReceiver(screenReceiver);
        super.onDestroy();
    }
}
