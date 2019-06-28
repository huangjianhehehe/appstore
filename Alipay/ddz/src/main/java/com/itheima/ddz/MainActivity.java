package com.itheima.ddz;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.itheima.alipay.Iservice;

public class MainActivity extends AppCompatActivity {
    private  Iservice iservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setAction("com.itheima.alipay.PayService");
        intent.setPackage("com.itheima.alipay");
        bindService(intent,conn,BIND_AUTO_CREATE);
    }
    private ServiceConnection conn=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
           iservice=Iservice.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    //点击买豆操作
    public void click1(View view) {
        try {
            boolean result=iservice.callpay("abc","123",5000);
            if (result){
                Toast.makeText(getApplicationContext(),"买豆成功",
                        Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(getApplicationContext(),"买豆失败",
                        Toast.LENGTH_SHORT).show();
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
