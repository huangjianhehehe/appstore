package com.itheima.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by admin on 2019/6/28.
 */

public class MyOpenHelper extends SQLiteOpenHelper {

    public MyOpenHelper(Context context) {
        super(context, "Account.db", null, 1);
    }

    //表结构的初始化
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table info(_id integer primary key autoincrement," +
                "name varchar(20),money varchar(20))");
        db.execSQL("insert into info(name,money) values(?,?)",new String[]{"张三","5000"});
        db.execSQL("insert into info(name,money) values(?,?)",new String[]{"李四","4000"});

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
