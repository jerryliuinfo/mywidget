package com.tcl.widget.demo.uti;

import android.content.Context;

import com.tcl.widget.demo.bean.PieBean;

import java.util.ArrayList;

/**
 * Created by jerryliu on 2017/5/4.
 */

public class ConfigManage {
    private static ConfigManage instance = null;
    private static Context mContext;
    private ConfigManage(){}
    public static ConfigManage getInstance(Context context) {
        mContext = context;
            if (instance == null) {
                synchronized (ConfigManage.class) {
                    if (instance == null){
                        instance = new ConfigManage();
                    }
            }
        }
        return instance;
    }

    public ArrayList<PieBean> list = new ArrayList<>();

    public ArrayList<PieBean> getList() {
        return list;
    }
}
