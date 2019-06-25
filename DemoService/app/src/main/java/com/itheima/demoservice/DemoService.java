package com.itheima.demoservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class DemoService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
//当服务第一次创建时调用
    @Override
    public void onCreate() {
        System.out.println("onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }
//当服务销毁时调用
    @Override
    public void onDestroy() {
        System.out.println("onDestroy");
        super.onDestroy();
    }
}
