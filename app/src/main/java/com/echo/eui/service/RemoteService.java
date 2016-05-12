package com.echo.eui.service;

import android.app.Service;
import android.content.Intent;
import android.os.*;

import com.echo.eui.TestAidl;

/**
 * Created by Echo-z on 2016/5/12/0012.
 */
public class RemoteService extends Service {

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private final TestAidl.Stub mBinder = new TestAidl.Stub() {
        @Override
        public int getPid() throws RemoteException {
            return android.os.Process.myPid();
        }

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float afloat, double aDouble, String aString) throws RemoteException {

        }
    };
}
