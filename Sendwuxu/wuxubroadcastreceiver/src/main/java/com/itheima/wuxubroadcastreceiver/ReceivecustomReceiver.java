package com.itheima.wuxubroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ReceivecustomReceiver extends BroadcastReceiver {
    //当接收到我们发送的 自定义广播
    @Override
    public void onReceive(Context context, Intent intent) {
        String content = intent.getStringExtra("name");
        Toast.makeText(context,content,Toast.LENGTH_SHORT).show();

    }
}
