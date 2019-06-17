package com.itheima.receiverice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class CityReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String data = getResultData();

        Toast.makeText(context,"市:"+data,Toast.LENGTH_SHORT).show();
        setResultData("习大大给村民发500斤大米");

    }
}
