package com.itheima.bootreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

//当手机重新启动时调用
public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //在这个方法里开启Activity
        Intent intent2 = new Intent(context, MainActivity.class);

        //注意 不能在广播接收者里面直接启动activity,需要添加一个标记,添加一个任务栈的标记
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //开启activity
        context.startActivity(intent2);
    }
}
