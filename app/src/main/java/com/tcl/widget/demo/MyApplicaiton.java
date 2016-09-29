package com.tcl.widget.demo;

import android.app.Application;
import android.content.Context;

import com.tcl.widget.demo.uti.Logger;
import com.tcl.widget.demo.uti.NLog;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * @author Jerry
 * @Description:
 * @date 2016/8/13 14:08
 * @copyright TCL-MIG
 */

public class MyApplicaiton extends Application {
    private static final String TAG = MyApplicaiton.class.getSimpleName();
    private static MyApplicaiton instance;
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        mContext = getApplicationContext();
        NLog.setDebug(true, Logger.VERBOSE);
        initBugly();

    }

    private void initBugly(){
        try{
            NLog.d(TAG, "bugly app id = %s, debug = %b", BuildConfig.BUGLY_APP_ID,BuildConfig.DEBUG);
            CrashReport.initCrashReport(getApplicationContext(), BuildConfig.BUGLY_APP_ID, BuildConfig.DEBUG);
        }catch (Exception e){

        }

    }


    public static Application getInstance(){
        return instance;
    }

    public static Context getContext(){
        return mContext;
    }

}
