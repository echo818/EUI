package com.echo.eui.views;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.*;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.echo.eui.R;
import com.echo.eui.TestAidl;
import com.echo.eui.service.CountService;
import com.echo.eui.service.RemoteService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button start, stop, bind, unbind, remote, unRemote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        System.out.println("Activity process:" + android.os.Process.myPid());
//        System.out.println("Activity Thread:" + Thread.currentThread().getId());

        start = (Button) findViewById(R.id.start_service);
        stop = (Button) findViewById(R.id.stop_service);
        bind = (Button) findViewById(R.id.bind_service);
        unbind = (Button) findViewById(R.id.unbind_service);

        remote = (Button) findViewById(R.id.bind_remote_service);
        unRemote = (Button) findViewById(R.id.unbind_remote_service);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
        bind.setOnClickListener(this);
        unbind.setOnClickListener(this);

        remote.setOnClickListener(this);
        unRemote.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, CountService.class);
        Intent mIntent = new Intent(this, RemoteService.class);
        switch (v.getId()) {
            case R.id.start_service:
                startService(intent);
                break;
            case R.id.stop_service:
                stopService(intent);
                break;
            case R.id.bind_service:
                bindService(intent, conn, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_service:
                unbindService(conn);
                break;
            case R.id.bind_remote_service:
                bindService(mIntent, mConnection, BIND_AUTO_CREATE);
                break;
            case R.id.unbind_remote_service:
                unbindService(mConnection);
                break;
        }
    }

    TestAidl mTestAidl;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mTestAidl = TestAidl.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mTestAidl = null;
        }
    };

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
//            System.out.println("ComponentName:" + name + "   IBinder:" + service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
//            System.out.println("ComponentName:" + name);
        }
    };
}
