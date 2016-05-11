package com.echo.eui.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Echo-z on 2016/5/11/0011.
 */
public class CountService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 20) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(i);
                    i++;
                }
            }
        }).start();
    }
}
