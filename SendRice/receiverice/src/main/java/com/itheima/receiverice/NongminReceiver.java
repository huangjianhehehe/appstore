package com.itheima.receiverice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class NongminReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String data = getResultData();

        Toast.makeText(context,"农民:"+data,Toast.LENGTH_SHORT).show();

    }
}
