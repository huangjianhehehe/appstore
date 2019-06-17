package com.itheima.sendrice2;

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
    //有序广播 发大米案例
    public void send(View view) {
        Intent intent = new Intent();
        intent.setAction("com.itheima.sendrice");
        /**
         * intent  意图
         * receiverPermission :接收权限
         * resultReceiver: 钦差大臣 FinalReceiver
         * initialCode: 初始码
         * initialData :初始数据
         *
         */

        sendOrderedBroadcast(intent,null,new FinalReceiver(),null,
                1,"习大大给村民发1000斤大米",null);
    }
}
