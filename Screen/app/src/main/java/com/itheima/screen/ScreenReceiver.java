package com.itheima.screen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenReceiver extends BroadcastReceiver {
    //当我们进行锁屏和解锁时这个方法运行
    @Override
    public void onReceive(Context context, Intent intent) {
        //获取当前广播事件类型
        String action = intent.getAction();
        if ("android.intent.action.SCREEN_OFF".equals(action)){
            System.out.println("屏幕锁屏了");
        }else if ("android.intent.action.SCREEN_ON".equals(action)){
            System.out.println("屏幕解锁了");
        }
    }
}
