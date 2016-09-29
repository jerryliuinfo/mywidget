package com.tcl.widget.demo.uti;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * @author liangni
 * @Description:
 * @date 2016/4/23 20:02
 * @copyright TCL-MIG
 */
public class SharePreferenceUtil {

    private static boolean mDebug = false;
    //上次SD读权限状态
    private static final  String PREVIEW_SD_PERMISSION = "preview_read_sd_permission";

    @SuppressLint("NewApi")
    public static void applyToEditor(SharedPreferences.Editor editor) {
        if (mDebug)
            Log.d("show", "SDK_INT  = " + android.os.Build.VERSION.SDK_INT);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.GINGERBREAD) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    public static void commitToEditor(SharedPreferences.Editor editor) {
        if (mDebug)
            Log.d("show", "SDK_INT  = " + android.os.Build.VERSION.SDK_INT);

        editor.commit();
    }

}
