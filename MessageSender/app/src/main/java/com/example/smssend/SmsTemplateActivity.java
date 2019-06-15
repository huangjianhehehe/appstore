package com.example.smssend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SmsTemplateActivity extends AppCompatActivity {
    String object[]={"我在吃饭,请稍后联系","我在开会,请稍后联系","我在上课,请稍后联系","我在打代码,请稍后联系",
            "我在忙,请稍后联系"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_template);

        ListView lv =findViewById(R.id.lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.smstemplate_item,object);
        lv.setAdapter(adapter);

        //设置lv的点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //取出点击条目的数据
                String smsContent = object[position];
                //把数据返回给调用者
                Intent intent= new Intent();
                intent.putExtra("smscontent",smsContent);
                setResult(20,intent);
                //调用finish
                finish();

            }
        });
    }
}
