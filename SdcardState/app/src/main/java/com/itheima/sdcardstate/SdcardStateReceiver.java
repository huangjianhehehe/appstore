package com.itheima.sdcardstate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SdcardStateReceiver extends BroadcastReceiver {

    //sd卡状态发生改变时执行
    @Override
    public void onReceive(Context context, Intent intent) {
        //获取到广播事件类型
        String action = intent.getAction();
        if("android.intent.action.MEDIA_MOUNTED".equals(action))
        {
            Log.d("SD", "说明Sd卡挂载了......");
        }else if ("android.intent.action.MEDIA_UNMOUNTED".equals(action)){
            Log.d("SD", "说明Sd卡未挂载了......");
        }

    }
}
