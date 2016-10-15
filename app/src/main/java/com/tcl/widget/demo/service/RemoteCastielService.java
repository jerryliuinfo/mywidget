package com.tcl.widget.demo.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.aidl.CastielProgressConnection;
import com.tcl.widget.demo.uti.NLog;

import static com.tencent.bugly.crashreport.inner.InnerAPI.context;

/**
 * @author Jerry
 * @Description:
 * @date 2016/10/12 20:51
 * @copyright TCL-MIG
 */

public class RemoteCastielService extends Service {
    private static final String TAG = LocalCastielService.class.getSimpleName();
    MyBinder myBinder;
    private PendingIntent pintent;
    MyServiceConnection myServiceConnection;

    @Override
    public void onCreate() {
        super.onCreate();
        if (myBinder == null) {
            myBinder = new MyBinder();
        }
        myServiceConnection = new MyServiceConnection();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.bindService(new Intent(this,LocalCastielService.class), myServiceConnection, Context.BIND_IMPORTANT);
//        Notification notification = new Notification(android.R.mipmap.sym_def_app_icon,
//                "猴子服务启动中",
//                System.currentTimeMillis());
//        pintent=PendingIntent.getService(this, 0, intent, 0);
        //notification.setLatestEventInfo(this, "猴子服务", "防止被杀掉！", pintent);

        //设置service为前台进程，避免手机休眠时系统自动杀掉该服务
        startForeground(startId, getNotification(intent));
        return START_STICKY;
    }

    class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName arg0, IBinder arg1) {
            Log.i("castiel", "本地服务连接成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            // 连接出现了异常断开了，LocalCastielService被杀死了
            Toast.makeText(RemoteCastielService.this, "本地服务Local被干掉", Toast.LENGTH_LONG).show();
            NLog.d(TAG, " LocalCastielServicel被干掉, 启动LocalCastielService");
            // 启动LocalCastielService
            RemoteCastielService.this.startService(new Intent(RemoteCastielService.this,LocalCastielService.class));
            RemoteCastielService.this.bindService(new Intent(RemoteCastielService.this,LocalCastielService.class), myServiceConnection, Context.BIND_IMPORTANT);
        }

    }

    class MyBinder extends CastielProgressConnection.Stub {

        @Override
        public String getProName() throws RemoteException {
            return "Remote猴子搬来的救兵 http://blog.csdn.net/mynameishuangshuai";
        }

    }

    @Override
    public IBinder onBind(Intent arg0) {
        return myBinder;
    }

    private Notification getNotification(Intent intent){
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("远程守护服务");
        builder.setContentText("防止本地服务被杀掉");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(PendingIntent.getService(this, 0, intent, 0));
        builder.setAutoCancel(true);
        Notification notification = builder.getNotification();
        return notification;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NLog.d(TAG, "RemoteCastielService onDestroy");
        if (myServiceConnection != null){
            unbindService(myServiceConnection);
        }
    }
}