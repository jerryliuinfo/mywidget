package com.tcl.widget.demo.notification;

import android.app.NotificationManager;
import android.content.Context;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.tcl.widget.demo.R;

import static android.content.ContentValues.TAG;

/**
 * @author Jerry
 * @Description:
 * @date 2016/11/18 15:31
 * @copyright TCL-MIG
 */

public class NotificationManagerWrapper {

    private static int numbers = 0;
    public static void show(Context context){
        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.drawable.ic_launcher);
        mBuilder.setContentTitle("Picture Download");
        mBuilder.setContentText("Download in progress");
        final int notifyId = 100;
        mBuilder.setAutoCancel(true);
        final NotificationManager mNotifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        int incr;
                        // Do the "lengthy" operation 20 times
                        for (incr = 0; incr <= 100; incr+=5) {
                            mBuilder.setProgress(100, incr, false);
                            mNotifyManager.notify(notifyId, mBuilder.build());
                            // Sleeps the thread, simulating an operation
                            // that takes time
                            try {
                                // Sleep for 5 seconds
                                Thread.sleep(1*500);
                            } catch (InterruptedException e) {
                                Log.d(TAG, "sleep failure");
                            }
                        }
                        // When the loop is finished, updates the notification
                        mBuilder.setContentText("Download complete")
                                // Removes the progress bar
                                .setProgress(0,0,false);
                        mNotifyManager.notify(notifyId, mBuilder.build());
                    }
                }
        ).start();




    }
}
