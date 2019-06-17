package com.itheima.sendwuxu;

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

    public void send(View view) {
        Intent intent = new Intent();
        intent.setAction("com.itheima.custom");

        intent.putExtra("name","新闻联播7点准时开播~");

        sendBroadcast(intent);
    }
}
