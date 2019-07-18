package com.itheima.insertsms;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View view) {
        Uri uri=Uri.parse("content://sms/");
        ContentValues values = new ContentValues();
        values.put("address","110");
        values.put("body","你的余额为0.00元");
        values.put("date",System.currentTimeMillis());

        getContentResolver().insert(uri,values);
    }
}
