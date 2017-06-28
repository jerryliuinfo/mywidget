package com.tcl.widget.demo.uti.view;

import android.graphics.Paint;

/**
 * Created by jerryliu on 2017/6/26.
 */

public class PaintConfigUtil {

    public static void configNormal(Paint paint, int color, float strokeWidth, Paint.Style style) {
        paint.setColor(color);
        paint.setStyle(style);
        paint.setStrokeWidth(strokeWidth);
    }

    public static void configStrokePaint(Paint paint, int color,float strokeWidth) {
        paint.setColor(color);
        paint.setStrokeWidth(strokeWidth);
        paint.setStyle(Paint.Style.STROKE);
    }

    public static void configFillPaint(Paint paint, int color) {
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
    }
    public static void configText(Paint paint, int color, int textSize) {
        paint.setColor(color);
        paint.setTextSize(textSize);
    }

    public static void configRound(Paint paint) {
        paint.setDither(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeJoin(Paint.Join.ROUND);
    }
}
