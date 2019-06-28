package example.com.servicetestaidl;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.service.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {


    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
            try {
                String str=iMyAidlInterface.getString();
                tv_text.setText(str);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            iMyAidlInterface=null;
        }
    };
    private IMyAidlInterface iMyAidlInterface;
    private  TextView tv_text;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_text= (TextView) findViewById(R.id.tv_text);
    }

    //绑定服务
    public void click1(View view) {
        Intent intent = new Intent();
        intent.setAction("com.example.service.MyService");
        intent.setPackage("com.example.service");
        bindService(intent,conn,BIND_AUTO_CREATE);

    }
    //解除绑定
    public void click2(View view) {
        unbindService(conn);

    }
}














































