package com.example.smsall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    //把祝福语放入数组中
    String objects[]={"当我遇到挫折时，是你的鼓励近在咫尺；当我得到帮助时，是你的关爱倍感雅致；当我向你诉苦时，是你的理解悉心微词；当我兴高采烈时，是你的微笑和我相伴。真正的友情能给心灵带来温馨，朋友，愿我们友谊长存！",
        "我们的友谊需要每天的互致关心，我们的快乐需要经常的保持互动，我们的幸福需要你的魅力纷呈，我们的未来需要彼此精心奏鸣。送上我所有的幸福和好心情，朋友，愿幸福一生！",
    "最美好的一天：今天。最简单的事：犯错。最大的障碍：害怕。最严重的错误：自暴自弃。最好的休闲活动：工作。最沉重的挫败：灰心。最优先的需要：沟通。最令人快乐的事：帮助别人。最大的缺点：坏脾气。把这些看清了，你也就释怀了。",
    "【幸福的小秘密】8点起，12点睡；每天至少翻5页书；跑30分钟步；对1个陌生人微笑；赞美1个人；说1句我爱你；每半年旅行1次；每年做1次体检；至少有1人值得深爱；至少有1个爱好让你锲而不舍；至少有5个电话可在深夜打扰。"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1.找到控件
        ListView lv = findViewById(R.id.lv);

        //2.设置数据,得先有数据
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.layout,objects);

        //3.设置数据适配器
        lv.setAdapter(adapter);

        //4.给listView设置点击事件 小技巧:所有事件用setOn
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //当listview的一个条目被点击时调用
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //5.把点击条目的数据取来了  掌握一条原则:数据在哪存着,去哪里取
                String content =objects[position];
                //6.跳转到发送短信页面
                Intent intent= new Intent();
                //设置action
                intent.setAction("android.intent.action.SEND");
                //添加category
                intent.addCategory("android.intent.category.DEFAULT");
                //设置Type
                intent.setType("text/plain");
                //传递数据
                intent.putExtra("sms_body",content);

                //跳转到发送短信页面
                startActivity(intent);


            }
        });


    }
}
