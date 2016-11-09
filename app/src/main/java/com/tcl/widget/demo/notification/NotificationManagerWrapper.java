package com.tcl.widget.demo.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.tcl.widget.demo.MyApplicaiton;
import com.tcl.widget.demo.R;

import static android.R.attr.type;

/**
 * Created by lenovo on 2016/11/3.
 */

public class NotificationManagerWrapper {
    private static NotificationManagerWrapper instance;
    public static NotificationManagerWrapper getInstance() {
        if (instance == null ){
            instance = new NotificationManagerWrapper();
        }
        return instance;
    }

    public static final int NOTIFY_ID = 100;
    public void sendNotification(){
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(MyApplicaiton.getContext())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("I am title")
                .setContentText("I am content")
                .setContentIntent(getClickIntent())
                .setDeleteIntent(getCancelIntent());

        NotificationManager notificationManager = (NotificationManager) MyApplicaiton.getContext().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFY_ID, notificationBuilder.build());
    }


    public PendingIntent getClickIntent(){
        Intent intentClick = new Intent(MyApplicaiton.getContext(), NotificationBroadcastReceiver.class);
        intentClick.setAction("notification_clicked");
        intentClick.putExtra(NotificationBroadcastReceiver.TYPE, type);
        PendingIntent pendingIntentClick = PendingIntent.getBroadcast(MyApplicaiton.getContext(),
                0, intentClick, PendingIntent.FLAG_ONE_SHOT);
        return pendingIntentClick;
    }

    public PendingIntent getCancelIntent(){
        Intent intentCancel = new Intent(MyApplicaiton.getContext(), NotificationBroadcastReceiver.class);
        intentCancel.setAction("notification_cancelled");
        intentCancel.putExtra(NotificationBroadcastReceiver.TYPE, type);
        PendingIntent pendingIntentCancel = PendingIntent.getBroadcast(MyApplicaiton.getContext(),
                0, intentCancel, PendingIntent.FLAG_ONE_SHOT);
        return pendingIntentCancel;
    }

}
