package com.tcl.widget.demo.uti;

import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by lenovo on 2016/11/17.
 */

public class ViewUtil {
    // 获取文字的高度
    public static float getTextHeight(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return fontMetrics.bottom-fontMetrics.descent-fontMetrics.ascent;
    }

    /**
     * 获得text所占用的宽度
     * @param paint
     * @param text
     * @return
     */
    public static int getTextWidth(Paint paint,String text){
        Rect rect = new Rect();
        paint.getTextBounds(text, 0, text.length(),rect);
        return rect.width();
    }

    /**
     * 获取text的baseY
     * @param paint
     * @param viewHeight
     * @return
     */
    public static float geTexttCenterY(Paint paint, int viewHeight){
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        float fontHeight = fontMetrics.bottom - fontMetrics.top;
        return viewHeight - (viewHeight - fontHeight ) /2 -fontMetrics.bottom;
    }

}
