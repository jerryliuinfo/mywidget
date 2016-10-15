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
 * @date 2016/10/12 20:50
 * @copyright TCL-MIG
 */

public class LocalCastielService extends Service{
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
        this.bindService(new Intent(this, RemoteCastielService.class), myServiceConnection, Context.BIND_IMPORTANT);
//        Notification notification = new Notification(android.R.mipmap.sym_def_app_icon, "猴子服务启动中", System.currentTimeMillis());
//        pintent = PendingIntent.getService(this, 0, intent, 0);
//        notification.setLatestEventInfo(this, "猴子服务", "防止被杀掉！", pintent);

        // 设置service为前台进程，避免手机休眠时系统自动杀掉该服务
        startForeground(startId, getNotification(intent));
        return START_STICKY;
    }

    class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName arg0, IBinder arg1) {
            Log.i("castiel", "远程服务连接成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            // 连接出现了异常断开了，RemoteService被杀掉了
            NLog.d(TAG, "RemoteCastielService 被干掉, 启动RemoteCastielService");
            Toast.makeText(LocalCastielService.this, "远程服务Remote被干掉", Toast.LENGTH_LONG).show();
            // 启动RemoteCastielService
            LocalCastielService.this.startService(new Intent(LocalCastielService.this, RemoteCastielService.class));
            LocalCastielService.this.bindService(new Intent(LocalCastielService.this, RemoteCastielService.class),
                    myServiceConnection, Context.BIND_IMPORTANT);
        }

    }

    class MyBinder extends CastielProgressConnection.Stub {

        @Override
        public String getProName() throws RemoteException {
            return "Local猴子搬来的救兵 http://blog.csdn.net/mynameishuangshuai";
        }

    }

    @Override
    public IBinder onBind(Intent arg0) {
        return myBinder;
    }

    int REQUEST_CODE = 0;

    private Notification getNotification(Intent intent){
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("本地守护服务");
        builder.setContentText("防止远程服务被杀掉");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setAutoCancel(true);
        builder.setContentIntent(PendingIntent.getService(this, REQUEST_CODE, intent, 0));
        Notification notification = builder.getNotification();
        return notification;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        NLog.d(TAG, "LocalCastielService onDestroy");
        if (myServiceConnection != null){
            unbindService(myServiceConnection);
        }

    }
}
