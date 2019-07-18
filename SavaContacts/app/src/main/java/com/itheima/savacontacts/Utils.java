package com.itheima.savacontacts;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by admin on 2019/7/17.
 */

public class Utils {
    public static ArrayList<Contact> getContacts(ContentResolver resolver){

        ArrayList<Contact> contacts = new ArrayList<Contact>();
        //通过resolver查询数据
        //[2] 确定要查询的表,要使用的Uri
        //查询raw_contact表的Uri
        Uri raw_contact_uri=Uri.parse("content://com.android.contacts/raw_contacts");
        //查询data表的uri
        Uri data_uri=Uri.parse("content://com.android.contacts/data");
        //[3] 先查询raw_contacts表,只查contact_id这一列
        Cursor cursor=resolver.query(raw_contact_uri,new String[]{"contact_id"},null,null,null);

        while(cursor.moveToNext()){
            //每查询出一个数据对应一个联系人,用id来保存查询的结果
            String id=cursor.getString(0);
//            System.out.println("id"+id);

            //[4]每查询出一个id值就创建一个联系人对象
            Contact contact=new Contact();

            //[5]使用查询出来的id作为条件,查询Data表要查询的列
            Cursor cursor2=resolver.query(data_uri,new String[]{"data1","mimetype"},"raw_contact_id=?",new String[]{id},null);
//            cursor2.moveToNext();
//            int columnCount=cursor2.getColumnCount();
//            for (int i=0;i<columnCount;i++){
//                String columnName=cursor2.getColumnName(i);
//                if(columnName.contains("mime")){
//                    System.out.println(columnName);
//                }
//            }
            while (cursor2.moveToNext()){

                //[6]获取每一列中的data1数据和mimetype数据
                String result = cursor2.getString(0);
                String type=cursor2.getString(1);
                System.out.println(result+"---"+type);

                //[7]通过mimetype数据确定data1数据到底要保存到javabean中的哪一个字段中
                if("vnd.android.cursor.item/phone_v2".equals(type)){
                    contact.setPhone(result);
                }else if("vnd.android.cursor.item/email_v2".equals(type)){
                    contact.setEmail(result);
                }else if("vnd.android.cursor.item/name".equals(type)){
                    contact.setName(result);
                }else if("vnd.android.cursor.item/postal-address_v2".equals(type)){
                    contact.setAddress(result);
                }

            }

            //[8]while循环结束,说明一个联系人保存的过程已经完成,可以把这个联系人放到联系人对象 集合中
            contacts.add(contact);

        }
        //[9]所有联系人都保存完了可以遍历集合查看保存的结果
        for(Contact contact:contacts){
            System.out.println(contact);
        }
        return contacts;
    }

}
