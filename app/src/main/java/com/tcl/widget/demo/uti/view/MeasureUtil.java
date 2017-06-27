package com.tcl.widget.demo.uti.view;

import android.graphics.Paint;
import android.view.View;

/**
 * Created by jerryliu on 2017/6/14.
 */

public class MeasureUtil {
    /**
     * 自定义View支持wrap属性
     * @param measureSpec
     * @param defaultSize
     * @return
     */
    public static int getMeasuredLength(int measureSpec, int defaultSize){
        int result = defaultSize;
        int specSize = View.MeasureSpec.getSize(measureSpec);
        int specMode = View.MeasureSpec.getMode(measureSpec);
        if (specMode == View.MeasureSpec.EXACTLY){
            result = specSize;
        }else if (specMode == View.MeasureSpec.AT_MOST){
            result = Math.min(result,specSize);
        }
        return result;
    }

    /**
     * 测量文字高度
     * @param paint
     * @return
     */
    public static float measureTextHeight(Paint paint) {
        Paint.FontMetrics fontMetrics = paint.getFontMetrics();
        return (Math.abs(fontMetrics.ascent) - fontMetrics.descent);
    }

    public static float getTextBaseY(Paint textPaint){
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        float top = fontMetrics.top;//为基线到字体上边框的距离,即上图中的top
        float bottom = fontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
        return (top + bottom) / 2;
    }
}
