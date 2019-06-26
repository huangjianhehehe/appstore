package com.itheima.bindservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MyConn conn;
    private BanzhengService.Mybinder myBinder; //创建中间人对象

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent= new Intent(this,BanzhengService.class);
        conn = new MyConn();
        //连接服务
        bindService(intent, conn,BIND_AUTO_CREATE);
    }
    //点击按钮调用办证方法
    public void click1(View view) {
        myBinder.callBanZheng(10);
    }
    //监视服务的状态
    private class MyConn implements ServiceConnection{
        //当服务连接成功调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myBinder = (BanzhengService.Mybinder)service;
        }
        //当服务没有连接调用
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    @Override
    protected void onDestroy() {
        //当activity销毁时,解邦服务
        unbindService(conn);
        super.onDestroy();
    }
}
