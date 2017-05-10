package com.tcl.widget.demo.ui.widget.threestep.animator;

import android.animation.TypeEvaluator;

/**
 * Created by jerryliu on 2017/5/10.
 */

public class PointEvaluator implements TypeEvaluator<Point> {
    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        Point point = new Point();
        point.mCenterX = startValue.mCenterX + ((endValue.mCenterX - startValue.mCenterX) * fraction);
        point.mCenterY = startValue.mCenterY + ((endValue.mCenterY - startValue.mCenterY) * fraction);

        return point;
    }
}