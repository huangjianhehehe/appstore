package com.itheima.readdatabase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 第一种方式读取数据库,需要修改远程数据库权限
         */
        //读取第一个应用的数据库
//        SQLiteDatabase db= SQLiteDatabase.openDatabase("/data/data/com.itheima.db/databases/Account.db",
//                null,SQLiteDatabase.OPEN_READWRITE);
//        //创建游标对象
//        Cursor cursor=db.query("info",null,null,null,
//                null,null,null,null);
//        while (cursor!=null&&cursor.getCount()>0){
//            while(cursor.moveToNext()){
//                String name=cursor.getString(1);
//                String money = cursor.getString(2);
//                System.out.println("name:"+name+"---"+money);
//            }
//        }



    }

    public void add(View view) {
        Uri url =Uri.parse("content://com.itheima.provider/insert");
        ContentValues values = new ContentValues();
        values.put("name","lisisi");
        values.put("money",5000);
        Uri add =getContentResolver().insert(url,values);
        System.out.println(add);

    }

    public void delete(View view) {
        Uri url=Uri.parse("content://com.itheima.provider/delete");
       int delete= getContentResolver().delete(url,"name=?",new String[]{"lisisi"});
        Toast.makeText(getApplicationContext(),"删除了"+delete+"行",Toast.LENGTH_LONG).show();
    }

    public void update(View view) {
        Uri url=Uri.parse("content://com.itheima.provider/update");
        ContentValues values= new ContentValues();
        values.put("money",0.000);

        int update=getContentResolver().update(url,values,"name=?",new String[]{"张三"});

        Toast.makeText(getApplicationContext(),"更新了"+update+"行",Toast.LENGTH_LONG).show();
    }

    public void query(View view) {
        /**
         * 第二种方式读取数据库
         *
         */

        //1.拿到内容解析者,直接通过上下文获取
        Uri uri= Uri.parse("content://com.itheima.provider/query");
        Cursor cursor=getContentResolver().query(uri,null,null,
                null,null);

        if (cursor!=null&&cursor.getCount()>0){
            while(cursor.moveToNext()){
                String name=cursor.getString(1);
                String money = cursor.getString(2);
                System.out.println("第二个应用name:"+name+"---"+money);
            }
        }

    }
}
