package com.itheima.demoservice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View view) { //开启服务
        Intent service = new Intent(this,DemoService.class);
        startService(service);
    }

    public void click2(View view) {//停止服务
        Intent service = new Intent(this,DemoService.class);
        stopService(service);
    }
}
