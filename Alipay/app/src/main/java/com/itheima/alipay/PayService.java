package com.itheima.alipay;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by admin on 2019/6/28.
 */

public class PayService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
    //支付的方法
    public boolean pay(String username,String pwd,int money){
        System.out.println("检测用户名密码是否正确");
        System.out.println("检测密码通过C实现");
        System.out.println("检测斗地主应用是否携带病毒");
        System.out.println(".......................");

        if("abc".equals(username)&&"123".equals(pwd)&&money<5000){
            return true;
        }else{
            return  false;
        }
    }
    //定义中间人对象
    private class MyBinder extends Iservice.Stub{

        @Override
        public boolean callpay(String username, String pwd, int money) throws RemoteException {
            //调用支付的方法

            return pay(username,pwd,money);
        }
    }
}































