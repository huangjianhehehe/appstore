package com.itheima.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class BanzhengService extends Service {

    //把我们定义的中间人对象返回
    @Override
    public IBinder onBind(Intent intent) {
        return new Mybinder();
    }
    //服务里面的方法
    public void banZheng(int money){
        if (money>1000){
            Toast.makeText(getApplicationContext(),"有钱就能办证",Toast.LENGTH_SHORT).show();

        }else
        {
            Toast.makeText(getApplicationContext(),"没钱还想办证",Toast.LENGTH_SHORT).show();

        }
    }
    //中间人对象IBinder
    public class Mybinder extends Binder{
        public void callBanZheng(int money){
            //调用办证方法
            banZheng(money);
        }
    }

}
