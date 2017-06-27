package com.tcl.widget.demo.uti.view;

import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by lenovo on 2016/11/17.
 */

public class ViewUtil {


    public static float getBaseY(Paint textPaint){
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
        return (top + bottom) / 2;
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




}
