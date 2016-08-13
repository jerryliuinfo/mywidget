package com.tcl.widget.demo.uti;

import com.tcl.widget.demo.MyApplicaiton;

public class UnitUtils {
    public static int dip2px(float dpValue) {
        final float scale = MyApplicaiton.getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
