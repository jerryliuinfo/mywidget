package com.tcl.widget.demo.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.tcl.widget.demo.uti.NLog;

import static android.content.ContentValues.TAG;

/**
 * Created by lenovo on 2016/11/3.
 */

public class NotificationBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = NotificationBroadcastReceiver.class.getSimpleName();
    public static final String TYPE = "type";
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        NLog.d(TAG, "NotificationBroadcastReceiver onReceive action = %s", action);
    }
}
