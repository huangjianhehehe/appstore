package com.example.rpcalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView tv_name=findViewById(R.id.tv_name);
        TextView tv_sex=findViewById(R.id.tv_sex);
        TextView tv_result=findViewById(R.id.tv_result);

        //获取传递过来的数据
        Intent intent = getIntent();

        //获取name和sex 的值   小技巧:传递的是什么数据类型,这边就照什么类型取
        String name = intent.getStringExtra("name");
        int sex = intent.getIntExtra("sex", 0);

        //根据name和sex显示
        tv_name.setText(name);
        byte[] bytes = null;
        try {
            switch (sex){
                case  1:
                    tv_sex.setText("男");
                     bytes = name.getBytes("gbk"); //转化为字节数&xff
                    break;
                case 2:
                    tv_sex.setText("女");
                     bytes = name.getBytes("utf-8"); //转化为字节数&xff
                    break;
                case 3:
                    tv_sex.setText("人妖");
                  bytes = name.getBytes("iso-8859-1"); //转化为字节数&xff
                    break;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //计算 人品的结果 ,市面上大多采用的是随机数
//        byte[] bytes = name.getBytes(); //转化为字节数&xff
        int total=0;
        for (byte b:bytes){
            int number=b&0xff;
            total+=number;
        }
        //获取得分,0-100之间
       int score= Math.abs(total)%100;
        if(score>90){
            tv_result.setText("您的人品非常好,祖坟都冒清烟了");
        }else if(score>80){
            tv_result.setText("您的人品还可以");
        }else if(score>60){
            tv_result.setText("您的人品刚及格");
        }else{
            tv_result.setText("您的人品很LOW,你要努力哦!~");
        }

    }
}
