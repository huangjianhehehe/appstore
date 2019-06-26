package com.itheima.registbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class ScreenReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //1.获取当前事件
        String action=intent.getAction();
        if ("android.intent.action.SCREEN_OFF".equals(action)){
            Log.d("Screen", "说明屏幕锁屏了");
        }else if("android.intent.action.SCREEN_ON".equals(action)){
            Log.d("Screen", "说明屏幕解锁了");
        }
    }
}
