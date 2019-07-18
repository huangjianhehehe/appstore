package com.itheima.savacontactdemo;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_phone;
    private EditText et_email;
    private EditText et_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找到关心的控件
        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        et_email = findViewById(R.id.et_email);
        et_address = findViewById(R.id.et_address);
    }

    public void save(View view) {

        //[1]获取内容解析者
        ContentResolver resolver =getContentResolver();

        //[2]获取Uri
        //查询raw_contact表的Uri
        Uri raw_contact_uri=Uri.parse("content://com.android.contacts/raw_contacts");
        //查询data表的uri
        Uri data_uri=Uri.parse("content://com.android.contacts/data");

        //[3]确定插入到raw_contact_uri中的id值
        Cursor cursor=resolver.query(raw_contact_uri,new String[]{"contact_id"},null,null,null);
        //获取当前查询结果一共有多行,那么就用查询结果+1来作为新插入的contact_id值
        int count = cursor.getCount();

        //[4] 拿到确定的contact_id的值 ,把对应的值 插入到raw_contact表中
        ContentValues value= new ContentValues();
        value.put("contact_id",count);
        resolver.insert(raw_contact_uri,value);

        //[5] 操作data表,要保存联系人的姓名,号码,邮箱,地址,每一个数据都代表data表中的一行
        //姓名
        ContentValues name_values = new ContentValues();
        String name=et_name.getText().toString();
        //插入列名和具体值
        name_values.put("raw_contact_id",count+1);
        name_values.put("data1",name);
        name_values.put("mimetype","vnd.android.cursor.item/name");
        resolver.insert(data_uri,name_values);

        //号码
        ContentValues phone_values = new ContentValues();
        String phone=et_phone.getText().toString();
        phone_values.put("raw_contact_id",count+1);
        phone_values.put("data1",phone);
        phone_values.put("mimetype","vnd.android.cursor.item/phone_v2");
        resolver.insert(data_uri,phone_values);

        //邮箱
        ContentValues email_values = new ContentValues();
        String email=et_email.getText().toString();
        email_values.put("raw_contact_id",count+1);
        email_values.put("data1",email);
        email_values.put("mimetype","vnd.android.cursor.item/email_v2");
        resolver.insert(data_uri,email_values);



        //地址
        ContentValues add_values = new ContentValues();
        String address=et_address.getText().toString();
        add_values.put("raw_contact_id",count+1);
        add_values.put("data1",address);
        add_values.put("mimetype","vnd.android.cursor.item/postal-address_v2");
        resolver.insert(data_uri,add_values);



    }
}
