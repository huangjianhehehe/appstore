package com.itheima.drawableanim;

import android.graphics.drawable.AnimationDrawable;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //1.找到控件
       final ImageView rocketImage = findViewById(R.id.rocket_Image);
        //2.设置背景
        rocketImage.setBackgroundResource(R.drawable.my_anim);

        //兼容2.1版本
        new Thread(){
            @Override
            public void run() {
                SystemClock.sleep(20);
                //3.获取AnimationDrawable类型
                AnimationDrawable rocketAnimation=(AnimationDrawable)rocketImage.getBackground();
                //4.开始动画
                rocketAnimation.start();
            }
        }.start();






    }
}
