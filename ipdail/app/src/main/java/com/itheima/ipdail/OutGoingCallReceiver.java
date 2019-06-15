package com.itheima.ipdail;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;


import static android.content.ContentValues.TAG;

public class OutGoingCallReceiver extends BroadcastReceiver { //定义广播接收者类,继承父类
    //四大组件定义时,必须要在清单文件中配置
    @Override
    public void onReceive(Context context, Intent intent) {
       //0.获取用户输入的ip号码
        SharedPreferences sp = context.getSharedPreferences("config", 0);
        String ipNumber = sp.getString("ipnumber", "");

        //1.获取当前拨打的电话号码
        String currentNumber=getResultData();
        //2.在当前的号码前加上17951

        //2.1判断当前拨打的号码是否是长途
        if(currentNumber.startsWith("0")){ //长途一般以0开头
            setResultData(ipNumber+currentNumber);
        }
    }
}
