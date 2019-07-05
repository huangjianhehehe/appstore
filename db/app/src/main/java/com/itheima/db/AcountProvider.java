package com.itheima.db;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by admin on 2019/7/1.
 */

public class AcountProvider extends ContentProvider {

    //1.定义一个urimatcher定义路径匹配器
    private static final UriMatcher sURIMatcher =
            new UriMatcher(UriMatcher.NO_MATCH);
    private static final int QUERYSUCCESS=0;
    private static final int INSERTSUCCESS=1;
    private static final int UPDATESUCCESS=2;
    private static final int DELETESUCCESS=3;

    //定义静态代码块 添加匹配规则
    static {
        /*
            authority 注意:这个参数和你在清单文件里面定义的一样

            uri:  content://com.itheima.provider/query

         */
        sURIMatcher.addURI("com.itheima.provider","query", QUERYSUCCESS);
        sURIMatcher.addURI("com.itheima.provider","insert", INSERTSUCCESS);
        sURIMatcher.addURI("com.itheima.provider","update", UPDATESUCCESS);
        sURIMatcher.addURI("com.itheima.provider","delete", DELETESUCCESS);
    }

    private MyOpenHelper myOpenHelper;

    @Override
    public boolean onCreate() {
        myOpenHelper = new MyOpenHelper(getContext());
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        int code =sURIMatcher.match(uri);
        if (code==QUERYSUCCESS){
            //说明路径匹配成功,把query方法给实现,数据库的查询的操作,想操作数据库必须获取
            // sqlitedatabase对象
            SQLiteDatabase db = myOpenHelper.getReadableDatabase();
            Cursor cursor=db.query("info",projection,selection,selectionArgs,
                    null,null,sortOrder);
            return cursor;

        }else{
            //路径不匹配
            throw new IllegalArgumentException("哥们:您的路径不匹配,请检查路径");
        }


    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int code =sURIMatcher.match(uri);
        if (code==INSERTSUCCESS){
            //说明路径匹配成功,把insert方法给实现,数据库的添加的操作,想操作数据库必须获取
            // sqlitedatabase对象
            SQLiteDatabase db = myOpenHelper.getReadableDatabase();
            //返回新插入行的索引ID
           long insert= db.insert("info",null,values);
           Uri uri2= Uri.parse("com.itheima.insert/"+insert);
            return uri2;
        }else{
            //路径不匹配
            throw new IllegalArgumentException("哥们:您的路径不匹配,请检查路径");
        }

    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int code = sURIMatcher.match(uri);
        if (code == DELETESUCCESS) {
            //说明路径匹配成功,数据库的删除的操作,想操作数据库必须获取
            // sqlitedatabase对象
            SQLiteDatabase db = myOpenHelper.getReadableDatabase();
            int delete = db.delete("info", selection, selectionArgs);
            return delete;

        } else {
            //路径不匹配
            throw new IllegalArgumentException("哥们:您的路径不匹配,请检查路径");
        }
    }


    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int code = sURIMatcher.match(uri);
        if (code == UPDATESUCCESS) {
            //说明路径匹配成功,数据库的修改的操作,想操作数据库必须获取
            // sqlitedatabase对象
            SQLiteDatabase db = myOpenHelper.getReadableDatabase();
           int update= db.update("info",values,selection,selectionArgs);
           return update;

        } else {
            //路径不匹配
            throw new IllegalArgumentException("哥们:您的路径不匹配,请检查路径");
        }
    }
}
