package com.itheima.phonelistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //利用广播开启服务
        Intent service1 = new Intent(context,PhoneSevice.class);
        context.startService(service1);
    }

}
