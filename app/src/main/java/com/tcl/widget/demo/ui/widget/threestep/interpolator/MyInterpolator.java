package com.tcl.widget.demo.ui.widget.threestep.interpolator;

import android.animation.TimeInterpolator;

import com.tcl.widget.demo.uti.NLog;

/**
 * Created by jerryliu on 2017/5/9.
 */

public class MyInterpolator implements TimeInterpolator {
    @Override
    public float getInterpolation(float input) {
        float value = 1-input;
        NLog.d("MyInterpolator", "getInterpolation value = %s",value);
        return value;
    }
}
