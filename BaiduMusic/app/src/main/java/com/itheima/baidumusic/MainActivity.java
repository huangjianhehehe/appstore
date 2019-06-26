package com.itheima.baidumusic;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private Iservice iservice;
    private Myconn conn;

    @Override
    protected void onDestroy() {
        //[3]调用unbindService解绑服务
        unbindService(conn);
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //混合方式开启服务
        //[1] 先调用startService方法开启服务,能够 保证服务在后台长期运行
        Intent intent= new Intent(this,MusicService.class);
        startService(intent);

//        调用bindService方法,去获取中间人对象
        conn = new Myconn();
        bindService(intent, conn,BIND_AUTO_CREATE);

    }
    //播放
    public void play(View view) {
        iservice.CallplayMusic();
    }
    //暂停
    public void pause(View view) {
        iservice.CallpauseMusic();
    }
    //继续播放
    public void continueplay(View view) {
        iservice.CallcontinueMusic();
    }
//监听服务的状态
    private class Myconn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iservice = (Iservice) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
