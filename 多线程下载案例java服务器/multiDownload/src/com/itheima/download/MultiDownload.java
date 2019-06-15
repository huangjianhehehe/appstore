package com.itheima.download;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

public class MultiDownload {
	
	//1.定义下载的路径
	private static String path="http://10.168.2.101:8080/python.exe";
	
	private static final int threadCount=3; //线程个数
	
	private static int runningThread; //正在进行的线程
	
	public static void main(String[] args) {
		
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
                runningThread=threadCount;
                System.out.println("length="+length);

              //第二步:在客户端创建一个大小和服务器一模一样的文件,目的是提前申请好空间
                
                //随机访问存储
                RandomAccessFile  rafa=new RandomAccessFile(getFilename(path), "rw");
                rafa.setLength(length);
                
                //7算出每个线程下载的大小
                int blockSize=length/threadCount;
                
                //第三步:计算机每个线程的开始和结束位置
                for (int i = 0; i < threadCount; i++) {
                	
                	int startIndex=i*blockSize; //每个线程下载的开始位置
                	int endIndex=(i+1)*blockSize-1;
                	
                	//特殊情况,就是最后一个线程
                	if(i==threadCount-1){
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//获取文件的名字 http://10.168.2.101:8080/python.exe
	//定义一个方法,截取字符串
	
	public static String  getFilename(String path) {
		
		int start = path.lastIndexOf("/")+1;

		return path.substring(start);	
		
	}
	//定义线程去服务器下载
	private static class DownLoadThread extends Thread{
		//通过构造把每个线程下载的开始位置和结束位置传递进来
		private int startIndex;
		private int endIndex;
		private int threadId;
		
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
	            
	             //连接
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




}
