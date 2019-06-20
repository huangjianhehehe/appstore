package com.itheima.dialog;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click1(View view) {//普通对话框\
        //通过builder构建器来构造
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("警告");
        builder.setMessage("世界上最遥远的距离是没有网络");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("dialog", "onClick: 点击了确定按钮");
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("dialog", "onClick: 点击了取消按钮");
            }
        });
        //最后一步一定要show
        builder.show();

    }

    public void click2(View view) {//单选对话框
        //通过builder构建器来构造
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择你喜欢的课程:");
        final String [] items={
                "java","C++","python","C#"
        };
        builder.setSingleChoiceItems(items, 3, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this,"您点击了"+items[which],Toast.LENGTH_SHORT).show();

            }
        });



        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("dialog", "onClick: 点击了确定按钮");
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("dialog", "onClick: 点击了取消按钮");
            }
        });
        //最后一步一定要show
        builder.show();
    }

    public void click3(View view) { //多选对话框
        //通过builder构建器来构造
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("请选择你喜欢的水果:");
        final String [] items={
                "苹果","香焦","草霉","西瓜"
        };
       final boolean [] checkedItems={
             true,false,true,false
        };
        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                           StringBuffer sb =new StringBuffer();
                            for (int i=0;i<checkedItems.length;i++){
                                if(checkedItems[i]){  //如果为真,取出来
                                    String item = items[i];
                                    sb.append(item+" ");
                                }
                            }
                            Toast.makeText(MainActivity.this,sb,Toast.LENGTH_SHORT).show();
                            
            }
        });



        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("dialog", "onClick: 点击了确定按钮");
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d("dialog", "onClick: 点击了取消按钮");
            }
        });
        //最后一步一定要show
        builder.show();
    }


    public void click4(View view) {  //进度条对话框

        final ProgressDialog dialog = new ProgressDialog(this);

        dialog.setTitle("正在玩命加载中...");
        //设置进度条样式
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        dialog.show();

        //开启子线程显示
        new Thread() {
            @Override
            public void run() {
                //设置进度条最大值
                dialog.setMax(100);
                //设置当前进度
                for (int i=0;i<=100;i++){
                   dialog.setProgress(i);
                    //睡眠一会
                    SystemClock.sleep(50);
                }
                dialog.dismiss();
            }
        }.start();
    }
}

