package com.example.smssend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContactActivity extends AppCompatActivity {

    private ListView lv;
    private List<Person> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        //1.找到关心的控件
        lv = findViewById(R.id.lv);

        //数据最好从通讯录获取,现在用假数据
        list = new ArrayList<Person>();
        for (int i=0;i<10;i++){
            Person person = new Person();
            person.setName("张三"+i);
            person.setPhone("1391122331"+i);
            list.add(person);
        }
        lv.setAdapter(new MyAdapter());

        //给Listview设置点击事件
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //获取点中条目的数据,数据在哪里存着,在哪里取
                String phone=list.get(position).getPhone();
                //把数据返回给调用者
                Intent intent = new Intent();
                intent.putExtra("phone",phone);
                //把结果返回给调用者
                setResult(10,intent);
                //关闭当前页面
                finish();
                //把phone返回给mainActivity

            }
        });

    }

    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           View view;
           if(convertView==null){
               view=View.inflate(getApplicationContext(),R.layout.contact_item,null);
           }else
           {
               view=convertView;
           }
            //1.找到我们在item中定义的控件
            TextView tv_name=view.findViewById(R.id.tv_name);
           TextView tv_phone=view.findViewById(R.id.tv_number);

           tv_name.setText(list.get(position).getName());
           tv_phone.setText(list.get(position).getPhone());
            return view;
        }
    }
}
