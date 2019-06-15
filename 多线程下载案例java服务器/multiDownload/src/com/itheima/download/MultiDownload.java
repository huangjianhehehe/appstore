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
	
	//1.�������ص�·��
	private static String path="http://10.168.2.101:8080/python.exe";
	
	private static final int threadCount=3; //�̸߳���
	
	private static int runningThread; //���ڽ��е��߳�
	
	public static void main(String[] args) {
		
		//��һ��:��ȡ�������ļ��Ĵ�С ,Ҫ����ÿ���߳����صĿ�ʼλ�úͽ���λ��
		 
		try {
            
             //����
             //2.2����URL ָ������Ҫ���ʵ���ַ(·��)
             URL url = new URL(path);
             //2.3�õ�httpurlconnection����,���ڷ��ͻ��߽�������
             HttpURLConnection conn= (HttpURLConnection) url.openConnection();
             conn.setRequestMethod("GET");
             //2.5��������ʱʱ��
             conn.setConnectTimeout(5000);

             //2.6��ȡ���������ص�״̬��
             int code =conn.getResponseCode();
             //2.7���code=200˵�����سɹ�
             if(code==200)
             {
            	 //��ȡ�������ļ���С
                int length=conn.getContentLength();
                
                //���̵߳�������ֵ���������е��߳�
                runningThread=threadCount;
                System.out.println("length="+length);

              //�ڶ���:�ڿͻ��˴���һ����С�ͷ�����һģһ�����ļ�,Ŀ������ǰ����ÿռ�
                
                //������ʴ洢
                RandomAccessFile  rafa=new RandomAccessFile(getFilename(path), "rw");
                rafa.setLength(length);
                
                //7���ÿ���߳����صĴ�С
                int blockSize=length/threadCount;
                
                //������:�����ÿ���̵߳Ŀ�ʼ�ͽ���λ��
                for (int i = 0; i < threadCount; i++) {
                	
                	int startIndex=i*blockSize; //ÿ���߳����صĿ�ʼλ��
                	int endIndex=(i+1)*blockSize-1;
                	
                	//�������,�������һ���߳�
                	if(i==threadCount-1){
                		//˵�������һ���߳�
                		endIndex=length-1;
                	}
                	
                	 System.out.println("�߳�ID:"+i+"�������ص�λ��"+startIndex+"---"+endIndex);
                	
                	//���Ĳ�:�����߳�ȥ����������
                	new DownLoadThread(startIndex, endIndex, i).start();
                	
					
				}
           


             }
         }
         catch (Exception e)
         {
             e.printStackTrace();
         }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//��ȡ�ļ������� http://10.168.2.101:8080/python.exe
	//����һ������,��ȡ�ַ���
	
	public static String  getFilename(String path) {
		
		int start = path.lastIndexOf("/")+1;

		return path.substring(start);	
		
	}
	//�����߳�ȥ����������
	private static class DownLoadThread extends Thread{
		//ͨ�������ÿ���߳����صĿ�ʼλ�úͽ���λ�ô��ݽ���
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
	            
	             //����
	             //2.2����URL ָ������Ҫ���ʵ���ַ(·��)
	             URL url = new URL(path);
	             //2.3�õ�httpurlconnection����,���ڷ��ͻ��߽�������
	             HttpURLConnection conn= (HttpURLConnection) url.openConnection();
	             conn.setRequestMethod("GET");
	             //2.4��������ʱʱ��
	             conn.setConnectTimeout(5000);
	             
	             //4.0����м��жϹ�,�����ϴε�λ������,���ļ��ж�ȡ�ϴ����ص�λ��
	             File file = new File(getFilename(path)+threadId+".txt");
	             if(file.exists()&&file.length()>0){
	            	 FileInputStream fis = new FileInputStream(file);
	            	 BufferedReader bufr=new BufferedReader(new InputStreamReader(fis));
	            	 String lastPositionn=bufr.readLine();  //�����Գ����ľ�����һ�����ص�λ��
	            	 int lastPosition=Integer.parseInt(lastPositionn);
	            	 
	            	 //4.0.1Ҫ�ı�һ��startIndex��λ��
	            	 startIndex=lastPosition;
	            	 System.out.println("�߳�ID:"+threadId+"��ʵ���ص�λ��"+startIndex+"---"+endIndex);
	            	 fis.close();
	            	 
	             }
	             
	             //2.4.1 ����һ������ͷRange(���߷�����ÿ���߳����صĿ�ʼλ�úͽ���λ��)
	             conn.setRequestProperty("Range", "bytes="+startIndex+"-"+endIndex);

	             //2.6��ȡ���������ص�״̬��
	             int code =conn.getResponseCode();
	             //2.7���code=200˵�����سɹ�
	             if(code==206)   //200���������ȫ����Դ�ɹ�,206���󲿷���Դ�ɹ�
	             {
	               //���������д�ļ�����
	            	  RandomAccessFile  raf=new RandomAccessFile(getFilename(path), "rw");
	            	  
	            	//ÿ���߳�Ҫ���Լ���λ�ÿ�ʼд
	            	  raf.seek(startIndex);
					InputStream in =conn.getInputStream(); //���ڵ���python.exe
					
					//������д���ļ���
					int len=-1;
					byte[] buffer=new byte[1024*1024];
					
					int total =0; //����ǰ�߳����صĴ�С
					while((len=in.read(buffer))!=-1){
						raf.write(buffer, 0, len);
						
						total+=len;
						//8.ʵ�ֶϵ�����,���ǰѵ�ǰ�߳����ص�λ�ø�������,�´������ص�ʱ��
										//���ǰ����ϴ����ص�λ�ü������ؾͿ�����
						int currentThreadPosition=startIndex+total;  //�����λ�ô浽��ͨ��txt��
						
						//9.�����浱ǰ�߳����ص�λ��
						 RandomAccessFile  raff=new RandomAccessFile(getFilename(path)+threadId+".txt", "rwd"); //ֱ��д��Ӳ��
						 raff.write(String.valueOf(currentThreadPosition).getBytes());
						 raff.close();
						
						
						
					}
					raf.close();
					System.out.println("�߳�ID"+threadId+"�������");
					
					//��.txt�ļ�ɾ��,ÿ���߳̾���ʲôʱ���������
					//����
					synchronized (DownLoadThread.class) {
						runningThread--;
						if(runningThread==0){
							//˵�����е��̶߳�ִ�������,�Ͱ�.txtɾ��
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
