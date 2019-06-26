package com.itheima.registbroadcast;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    //使用服务注册特殊的广播接收者
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //开启服务
        Intent intent=new Intent(this,ScreenService.class);
        startService(intent);




    }
}
