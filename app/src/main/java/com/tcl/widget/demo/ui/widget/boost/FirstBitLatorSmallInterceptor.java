package com.tcl.widget.demo.ui.widget.boost;

import android.animation.TimeInterpolator;

/**
 * Created by jerryliu on 2017/4/28.
 */

public class FirstBitLatorSmallInterceptor implements TimeInterpolator {
    float reachmax = 0.3f;
    float scale1;
    float scale2;

    public FirstBitLatorSmallInterceptor(float reachMax) {
        this.reachmax = reachMax;
        scale1 = 1 / reachMax;
        scale2 = 1 / (1- reachMax);
    }

    @Override
    public float getInterpolation(float input) {
        if (input > reachmax) {
            return 1 - ((input - reachmax) * scale2);
        } else {
            return input * scale1;
        }
    }
}
