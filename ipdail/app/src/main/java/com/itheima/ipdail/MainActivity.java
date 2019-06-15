package com.itheima.ipdail;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
//打电话时在长途号码前加17951
public class MainActivity extends AppCompatActivity {

    private EditText et_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_number = findViewById(R.id.et_ipnumber);

    }
    //点击按钮保存用户输入的号码
    public void click(View view) {
        //1获取号码
        String ipnumber=et_number.getText().toString().trim();
        //2把当前ipnumber存起来,放在sp里边
        SharedPreferences sp = getSharedPreferences("config",0);
        //3获取sp的编译器
        sp.edit().putString("ipnumber",ipnumber).commit();
        //4保存成功
        Toast.makeText(getApplicationContext(),"保存成功",Toast.LENGTH_SHORT).show();
        finish();


    }
}
