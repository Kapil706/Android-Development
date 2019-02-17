package com.example.orderedbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class BroadCastReceiver1 extends BroadcastReceiver {
    private static final String TAG=BroadCastReceiver1.class.getSimpleName();

    private static String BREAD_CRUMB = "Breadcrumb";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle=getResultExtras(true);
        String trail=bundle.getString(BREAD_CRUMB);
        trail=(trail==null?"Start->"+TAG:trail+"->"+TAG);
        bundle.putString(BREAD_CRUMB, trail);
        Log.i(TAG, "BroadCastReceiver1 triggered: "+trail);
    }


}
