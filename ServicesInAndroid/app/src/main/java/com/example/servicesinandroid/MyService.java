package com.example.servicesinandroid;

import android.app.Service;
import android.content.Intent;
import android.nfc.Tag;
import android.os.IBinder;
import android.util.Log;

import javax.security.auth.login.LoginException;

public class MyService extends Service {
  //  @androidx.annotation.Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("onDestroy","service destroyed");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // this function will be executed when MyService is called
        Log.i("in onstartcommand","threadcount"+Thread.currentThread().getId());
        // stopself() method is used to stop the thread
        return super.onStartCommand(intent, flags, startId);
    }
}
