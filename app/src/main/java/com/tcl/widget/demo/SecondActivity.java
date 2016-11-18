package com.tcl.widget.demo;

import android.os.Bundle;

import com.tcl.widget.demo.container.BaseActivity;
import com.tcl.widget.demo.uti.NLog;

/**
 * @author Jerry
 * @Description:
 * @date 2016/11/18 11:11
 * @copyright TCL-MIG
 */

public class SecondActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_view);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        NLog.e(TAG, "SecondActivity onDestroy");
    }
}
