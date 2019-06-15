package com.example.newsclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.loopj.android.image.SmartImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<News> newsLists;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       //1.找到我们关心的控件
        lv = (ListView)findViewById(R.id.lv);
        //2.准备listview要显示的数据,去服务器取数据,进行封装
        initListData();
    }
    //准备listview要显示的数据
    private void initListData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //2.1创建路径
                String path="http://10.168.2.101/news.xml";
                //1.去服务器去数据
                try {
                    //2.2创建URL 指定我们要访问的网址(路径)
                    URL url = new URL(path);
                    //2.3拿到httpurlconnection对象,用于发送或者接收数据
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    //2.4 发送GET请求
                    conn.setRequestMethod("GET");
                    //2.5设置请求超时时间
                    conn.setConnectTimeout(5000);
                    //2.6获取服务器返回的状态码
                    int code =conn.getResponseCode();
                    //2.7如果code=200说明返回成功
                    if(code==200)
                    {
                        //2.8获取服务器返回的数据是以流的形式返回的
                        InputStream in=conn.getInputStream();
                        //2.9解析xml 抽出一个业务方法
                        newsLists = XmlParseUtils.parserXml(in);
                        //3.更新 UI
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                lv.setAdapter(new MyAdapter());
                            }
                        });

                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            };
        }).start();

    }

    //定义数据适配器
    private class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return newsLists.size();
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
           if(convertView==null)
           {
               view=View.inflate(getApplicationContext(),R.layout.item,null);
           }else{
               view =convertView;
           }
           //1.找到控件,显示集合里面的数据
           SmartImageView iv_icon =(SmartImageView) view.findViewById(R.id.iv_icon);
            TextView tv_title=view.findViewById(R.id.tv_title);
            TextView tv_desc = view.findViewById(R.id.tv_desc);
            TextView tv_type= view.findViewById(R.id.tv_type);

            //1.1展示图片的数据
            String imageUrl=  newsLists.get(position).getImage();

            iv_icon.setImageUrl(imageUrl);
            //2.显示控件
            tv_title.setText(newsLists.get(position).getTitle());
            tv_desc.setText(newsLists.get(position).getDescription());
          String typee=newsLists.get(position).getType();
          String comment=newsLists.get(position).getComment();
          int type=Integer.parseInt(typee);
          switch (type)
          {
              case 1:
                  tv_type.setText("国内");
                  break;
              case 2:
                  tv_type.setText("娱乐");
                  break;
              case 3:
                  tv_type.setText("国际");
                  break;
                  default:
                      break;
          }

            return view;
        }
    }
}
