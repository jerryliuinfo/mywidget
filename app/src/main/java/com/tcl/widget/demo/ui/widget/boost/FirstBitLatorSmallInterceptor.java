package com.tcl.widget.demo.ui.widget.boost;

import android.animation.TimeInterpolator;

/**
 * Created by jerryliu on 2017/4/28.
 */

public class FirstBitLatorSmallInterceptor implements TimeInterpolator {
    float reachmax = 0.1f;
    float scale1;
    float scale2;

    public FirstBitLatorSmallInterceptor(float reachMax) {
        this.reachmax = reachMax;
        scale1 = 1 / reachMax;
        scale2 = 1 / (1- reachMax);
    }

    @Override
    public float getInterpolation(float input) {
        //时间比系哦啊与0.1的时候 线性增加
        if (input < reachmax) {
            return input * scale1;
        } else {
            return 1 - ((input - reachmax) * scale2);
        }
    }
}
