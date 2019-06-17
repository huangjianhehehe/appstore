package com.itheima.receiverice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ProvienceReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //获取广播发送的数据
        String data = getResultData();
       // abortBroadcast();

        Toast.makeText(context,"省:"+data,Toast.LENGTH_SHORT).show();

        setResultData("习大大给村民发800斤大米");
    }
}
