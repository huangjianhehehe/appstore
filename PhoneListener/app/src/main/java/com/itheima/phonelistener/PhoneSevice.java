package com.itheima.phonelistener;

import android.app.Service;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.IOException;

public class PhoneSevice extends Service {

    private MediaRecorder mRecorder;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        //1.获取telephoneManager的实例
        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        //2.跳转到电话的监听
        tm.listen(new MyPhoneStateListener(),PhoneStateListener.LISTEN_CALL_STATE);

        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //1定义一个类用来监听电话的状态
    private  class MyPhoneStateListener extends PhoneStateListener{

        //2当电话设置状态发生改变的时候调用
        @Override
        public void onCallStateChanged(int state, String phoneNumber) {
            //3.具体判断状态
            switch (state){
                case TelephonyManager.CALL_STATE_IDLE: //空闲状态
                    if (mRecorder!=null){
                        mRecorder.stop();
                        mRecorder.reset();
                        mRecorder.release();
                    }

                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK: //接听
                    Log.d("phone", "onCallStateChanged:开始录音 ");
                    mRecorder.start();
                    break;
                case TelephonyManager.CALL_STATE_RINGING: //响铃状态
                    Log.d("phone", "onCallStateChanged:准备一个录音机 ");
                    //1.创建MediaRecorder实例
                    mRecorder = new MediaRecorder();
                    //2.设置音频来源
                    mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                    //3.设置音频格式
                    mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                    //4.设置音频编码方式
                    mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
                    //5.设置音频存放路径
                    mRecorder.setOutputFile("/mnt/sdcard/luyin.3gp");
                    //6.准备录
                    try {
                        mRecorder.prepare();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
            }


            super.onCallStateChanged(state, phoneNumber);

        }
    }











}
