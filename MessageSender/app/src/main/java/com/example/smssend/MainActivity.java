package com.example.smssend;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText et_phone;
    private EditText et_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_phone = findViewById(R.id.et_number);
        et_content = findViewById(R.id.et_content);

    }

    //点击跳转到
    public void add(View view) {
        Intent intent = new Intent(this,ContactActivity.class);
       // startActivity(intent);
        //小细节 如果点击按钮开启另外一个Activity 并且当开启的这个Activity关闭的时候,用下面的方法
        //开启action
        startActivityForResult(intent,1);
    }
    //当我们开启的Activity页面关闭的时候,这个方法就调用

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode==10){
            //说明数据是从ContactActivity返回的
            String phone =data.getStringExtra("phone");
            et_phone.setText(phone);
        }else if(resultCode==20){
            //说明数据是从smsTemplate中返回
            String smsContent=data.getStringExtra("smscontent");
            et_content.setText(smsContent);
        }



        super.onActivityResult(requestCode, resultCode, data);
    }

    //发送短信的功能
    public void send(View view) {
        //1.获取发送短信的号和内容
        String number = et_phone.getText().toString().trim();
        String content = et_content.getText().toString().trim();

        //获取smsmanager的实例
        SmsManager smsManager= SmsManager.getDefault();

        //超过最大字数限制需要分批发
        ArrayList<String> divideMessage = smsManager.divideMessage(content);
        for(String div:divideMessage){
            /**
             * destinationAddress 发送给谁
             * scAddress 服务中心号玛
             */
            smsManager.sendTextMessage(number,null,div,null,null);
        }
    }

    //跳转到发送短信模板页面
    public void insertmessage(View view) {
        Intent intent = new Intent(this,SmsTemplateActivity.class);
        startActivityForResult(intent,2);
    }
}
