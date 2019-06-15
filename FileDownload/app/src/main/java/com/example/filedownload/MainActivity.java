package com.example.filedownload;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText et_path;
    private EditText et_threadCount;
    private LinearLayout ll_pb_layout;
    private String path;
    private static int runningThread; //正在进行的线程
    private int threadCount;
    private List<ProgressBar> pblists; //用来存进度条的引用

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //1.找到关心控件
        et_path = findViewById(R.id.et_path);
        et_threadCount = findViewById(R.id.et_threadCount);
        ll_pb_layout = findViewById(R.id.ll_pb);
        //[2]添加一个集合用来存进度条的引用
        pblists = new ArrayList<ProgressBar>();
    }


//点击按钮实现下载
    public void click1(View v) {
        //2.获取下载路径
        path = et_path.getText().toString().trim();
        //3.获取线程数量
        String threadcountt = et_threadCount.getText().toString().trim();
        threadCount = Integer.parseInt(threadcountt);
        //3.0先移除进度条,再添加
        ll_pb_layout.removeAllViews();
        pblists.clear();
        for (int i = 0; i< threadCount; i++){
            //3.1 把我定义的item布局转换成一个view对象
            ProgressBar pbview= (ProgressBar) View.inflate(getApplicationContext(),R.layout.item,null);

            //3.2把pbView添加到集合中
            pblists.add(pbview);
            //4.动态的添加进度条
            ll_pb_layout.addView(pbview);
        }

        //5.开始从java移植代码到android
        new Thread(){
            @Override
            public void run() {
                //第一步:获取服务器文件的大小 ,要计算每个线程下载的开始位置和结束位置

                try {

                    //连接
                    //2.2创建URL 指定我们要访问的网址(路径)
                    URL url = new URL(path);
                    //2.3拿到httpurlconnection对象,用于发送或者接收数据
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    //2.5设置请求超时时间
                    conn.setConnectTimeout(5000);

                    //2.6获取服务器返回的状态码
                    int code =conn.getResponseCode();
                    //2.7如果code=200说明返回成功
                    if(code==200)
                    {
                        //获取服务器文件大小
                        int length=conn.getContentLength();

                        //把线程的数量赋值给正在运行的线程
                        runningThread= threadCount;
                        System.out.println("length="+length);

                        //第二步:在客户端创建一个大小和服务器一模一样的文件,目的是提前申请好空间

                        //随机访问存储
                        RandomAccessFile  rafa=new RandomAccessFile(getFilename(path), "rw");
                        rafa.setLength(length);

                        //7算出每个线程下载的大小
                        int blockSize=length/ threadCount;

                        //第三步:计算机每个线程的开始和结束位置
                        for (int i = 0; i < threadCount; i++) {

                            int startIndex=i*blockSize; //每个线程下载的开始位置
                            int endIndex=(i+1)*blockSize-1;

                            //特殊情况,就是最后一个线程
                            if(i== threadCount -1){
                                //说明是最后一个线程
                                endIndex=length-1;
                            }

                            System.out.println("线程ID:"+i+"理论下载的位置"+startIndex+"---"+endIndex);

                            //第四步:开启线程去服务器下载
                            new DownLoadThread(startIndex, endIndex, i).start();


                        }



                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }.start();


    }

    private class DownLoadThread extends Thread{
        //通过构造把每个线程下载的开始位置和结束位置传递进来
        private int startIndex;
        private int endIndex;
        private int threadId;

        private int PbMaxSize; //代表当前线程的最大值

        //如果中断过,获取上次下载的位置
        private int pblastPosition;

        public DownLoadThread(int startIndex, int endIndex, int threadId) {
            super();
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.threadId = threadId;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();

            try {
                //0.PbMaxSize
                PbMaxSize=endIndex-startIndex;

                //2.2创建URL 指定我们要访问的网址(路径)
                URL url = new URL(path);
                //2.3拿到httpurlconnection对象,用于发送或者接收数据
                HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                //2.4设置请求超时时间
                conn.setConnectTimeout(5000);

                //4.0如果中间中断过,继续上次的位置下载,从文件中读取上次下载的位置
                File file = new File(getFilename(path)+threadId+".txt");
                if(file.exists()&&file.length()>0){
                    FileInputStream fis = new FileInputStream(file);
                    BufferedReader bufr=new BufferedReader(new InputStreamReader(fis));
                    String lastPositionn=bufr.readLine();  //读他对出来的就是上一次下载的位置
                    int lastPosition=Integer.parseInt(lastPositionn);


                    //4.0 给我们定义的进度条位置赋值
                    pblastPosition=lastPosition-startIndex;
                    //4.0.1要改变一下startIndex的位置
                    startIndex=lastPosition;
                    System.out.println("线程ID:"+threadId+"真实下载的位置"+startIndex+"---"+endIndex);
                    fis.close();

                }

                //2.4.1 设置一个请求头Range(告诉服务器每个线程下载的开始位置和结束位置)
                conn.setRequestProperty("Range", "bytes="+startIndex+"-"+endIndex);

                //2.6获取服务器返回的状态码
                int code =conn.getResponseCode();
                //2.7如果code=200说明返回成功
                if(code==206)   //200代表服务器全部资源成功,206请求部分资源成功
                {
                    //创建随机读写文件对象
                    RandomAccessFile  raf=new RandomAccessFile(getFilename(path), "rw");

                    //每个线程要从自己的位置开始写
                    raf.seek(startIndex);
                    InputStream in =conn.getInputStream(); //存在的是python.exe

                    //把数据写到文件中
                    int len=-1;
                    byte[] buffer=new byte[1024*1024];

                    int total =0; //代表当前线程下载的大小
                    while((len=in.read(buffer))!=-1){
                        raf.write(buffer, 0, len);

                        total+=len;
                        //8.实现断点续传,就是把当前线程下载的位置给存起来,下次再下载的时候
                        //就是按照上次下载的位置继续下载就可以了
                        int currentThreadPosition=startIndex+total;  //把这个位置存到普通的txt中

                        //9.用来存当前线程下载的位置
                        RandomAccessFile  raff=new RandomAccessFile(getFilename(path)+threadId+".txt", "rwd"); //直接写入硬盘
                        raff.write(String.valueOf(currentThreadPosition).getBytes());
                        raff.close();

                        //10.设置一下当前进度条的最大值 和当前进度
                        pblists.get(threadId).setMax(PbMaxSize); //设置进度条的最大值
                        pblists.get(threadId).setProgress(pblastPosition+total); //设置进度条的当前进度




                    }
                    raf.close();
                    System.out.println("线程ID"+threadId+"下载完毕");

                    //把.txt文件删除,每个线程具体什么时候下载完毕
                    //带锁
                    synchronized (DownLoadThread.class) {
                        runningThread--;
                        if(runningThread==0){
                            //说明所有的线程都执行完毕了,就把.txt删除
                            for (int i = 0; i < threadCount; i++) {
                                File deleteFile = new File(getFilename(path)+i+".txt");
                                deleteFile.delete();
                            }

                        }
                    }


                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }


    }
    //获取文件的名字 http://10.168.2.101:8080/python.exe
    //定义一个方法,截取字符串

    public static String  getFilename(String path) {

        int start = path.lastIndexOf("/")+1;

        String subString=path.substring(start);

        String fileName= Environment.getExternalStorageDirectory().getPath()+"/"+subString;
        return  fileName;
    }
}
