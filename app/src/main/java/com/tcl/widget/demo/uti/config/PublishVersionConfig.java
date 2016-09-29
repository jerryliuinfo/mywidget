package com.tcl.widget.demo.uti.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.tcl.widget.demo.MyApplicaiton;

/**
 * @author Jerry
 * @Description:
 * @date 2016/9/29 15:19
 * @copyright TCL-MIG
 */

public class PublishVersionConfig extends BaseSPManager{
    private SharedPreferences sharedPreferences;
    public static final String SP_NAME = "publish_version";

    private static PublishVersionConfig instance = null;
    private PublishVersionConfig(){}
    public static PublishVersionConfig getInstance() {
        synchronized (PublishVersionConfig.class) {
            if (instance == null) {
                instance = new PublishVersionConfig();
            }
        }
        return instance;
    }

    @Override
    public String getSharePrefFileName() {
        return SP_NAME;
    }

    @Override
    public SharedPreferences getSharedPreference() {
        if (sharedPreferences == null){
            sharedPreferences = MyApplicaiton.getContext().getSharedPreferences(getSharePrefFileName(), Context.MODE_PRIVATE);
        }

        return sharedPreferences;
    }




}
