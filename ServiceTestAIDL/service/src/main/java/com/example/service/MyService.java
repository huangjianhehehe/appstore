package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by admin on 2019/6/28.
 */

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Mybind();
    }

    //定义一个内部类
    class Mybind extends IMyAidlInterface.Stub{

        @Override
        public String getString() throws RemoteException {
            String string = "我是从服务返回的";
            return string;
        }
    }
}
