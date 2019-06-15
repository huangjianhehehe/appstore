package com.example.rpcalc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText et_name;
    private RadioGroup rg_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name = findViewById(R.id.et_name);
        rg_group = findViewById(R.id.radioGroup1);
    }

    public void click(View view) { //点击按钮实现计算人品,跳转到下一页面
        //1.获取用户名
        String name=et_name.getText().toString().trim();
        //2.判断一下name是否为空
        if(TextUtils.isEmpty(name))
        {
            Toast.makeText(getApplicationContext(),"输入为空",Toast.LENGTH_SHORT).show();

        }
        //3.判断 用户选择的性别
        int radioButtonId =rg_group.getCheckedRadioButtonId();
        int sex=0;
        switch (radioButtonId){
            case R.id.rb_male: //代表选择男
                sex=1;
                break;
            case R.id.rb_female: //女
                sex=2;
                break;
            case R.id.rb_other:
                sex=3;
                break;

                }
        if (sex==0){
            Toast.makeText(getApplicationContext(),"人必须有性别",Toast.LENGTH_SHORT).show();
            return;
        }
        //4.跳转
        Intent intent = new Intent(this,ResultActivity.class);

        //传递姓名
        intent.putExtra("name",name);

        //传递性别
        intent.putExtra("sex",sex);
        this.startActivity(intent);


    }
}
