package com.itheima.baidumusic;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service {
    //把我们定义的中间人对象返回
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }
    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    //服务里面写播放音乐的方法
    public  void playMusic(){
        //TODO 等讲完多媒体
        System.out.println("音乐播放了...");
    }
    public  void pauseMusic(){
        //TODO 等讲完多媒体
        System.out.println("音乐暂停了...");
    }
    public  void continueMusic(){
        //TODO 等讲完多媒体
        System.out.println("音乐继续播放了...");
    }
    //1.在服务内部定义一人中间人对象IBinder
    private  class  MyBinder extends Binder implements Iservice{

        @Override
        public void CallplayMusic() { //调用播放音乐
            playMusic();
        }

        @Override
        public void CallpauseMusic() { //调用暂停
            pauseMusic();
        }

        @Override
        public void CallcontinueMusic() { //调用继续播放
            continueMusic();
        }
    }






























}
