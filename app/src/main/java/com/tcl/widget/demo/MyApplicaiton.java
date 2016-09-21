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
    private static MyApplicaiton instance;
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();

        instance = this;
        mContext = getApplicationContext();
        NLog.setDebug(true, Logger.VERBOSE);
        CrashReport.initCrashReport(getApplicationContext(), "900053955", false);

    }

    public static Application getInstance(){
        return instance;
    }

    public static Context getContext(){
        return mContext;
    }

}
