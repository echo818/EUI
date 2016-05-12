package com.echo.eui.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import com.echo.eui.MyServiceAIDL;

/**
 * Created by Echo-z on 2016/5/11/0011.
 */
public class CountService extends Service {
    private int i = 0;

    @Override
    public IBinder onBind(Intent intent) {
        System.out.println("onBind:" + intent);
        return new MyService();
    }

    private class MyService extends MyServiceAIDL.Stub {

        @Override
        public String getValue() throws RemoteException {
            return "Android is very powerful";
        }
    }

//
//    @Override
//    public void onCreate() {
//        System.out.println("Service process:" + android.os.Process.myPid());
//        System.out.println("Service Thread:" + Thread.currentThread().getId());
//        System.out.println("onCreate");
//        super.onCreate();
////        int n = 11;
////        while (n > 0) {
////            try {
////                Thread.sleep(1000);
////            } catch (InterruptedException e) {
////                e.printStackTrace();
////            }
////            n--;
////            System.out.println(n);
////        }
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                while (i < 20) {
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(i);
//                    i++;
//                }
//            }
//        }).start();
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
//        System.out.println("onStartCommand:" + intent + "   flags:" + flags + "   startId:" + startId);
//        return super.onStartCommand(intent, flags, startId);
//    }
//
//    @Override
//    public void onStart(Intent intent, int startId) {
//        super.onStart(intent, startId);
//        System.out.println("onStart:" + intent + "   startId:" + startId);
//    }
//
//    @Override
//    public boolean onUnbind(Intent intent) {
//        System.out.println("onUnbind:" + intent);
//        return super.onUnbind(intent);
//    }
//
//    @Override
//    public void onRebind(Intent intent) {
//        super.onRebind(intent);
//        System.out.println("onRebind:" + intent);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        i = 20;
//        System.out.println("onDestroy");
//    }
}
