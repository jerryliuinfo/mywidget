package com.tcl.widget.demo.uti;

import android.graphics.Paint;

/**
 * Created by lenovo on 2016/11/17.
 */

public class ViewUtil {
    // 获取文字的高度
    public static float getTextHeight(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return fontMetrics.bottom-fontMetrics.descent-fontMetrics.ascent;
    }
}
