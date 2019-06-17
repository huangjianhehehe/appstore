package com.itheima.sendrice2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class FinalReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String data = getResultData();

        Toast.makeText(context,"报告习大大:"+data,Toast.LENGTH_SHORT).show();
    }
}
