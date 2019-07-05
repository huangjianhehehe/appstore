package com.itheima.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private MyOpenHelper myOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //新建一个数据库实例
        myOpenHelper = new MyOpenHelper(getApplicationContext());
        //打开数据库
        SQLiteDatabase db=myOpenHelper.getReadableDatabase();
        //创建游标对象
        Cursor cursor=db.query("info",null,null,null,
                null,null,null,null);
        while (cursor!=null&&cursor.getCount()>0){
            while(cursor.moveToNext()){
                String name=cursor.getString(1);
                String money = cursor.getString(2);
                System.out.println("name:"+name+"---"+money);
            }
        }


    }
}
