package com.tcl.widget.demo.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.tcl.widget.demo.uti.NLog;

/**
 * @author Jerry
 * @Description:
 * @date 2016/10/29 14:48
 * @copyright TCL-MIG
 */

public class MyViewGroupB extends LinearLayout {
    public static final String  TAG = MyViewGroupA.TAG;
    public MyViewGroupB(Context context) {
        super(context);
    }

    public MyViewGroupB(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroupB(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = super.dispatchTouchEvent(ev);
        NLog.d(TAG, "MyViewGroupB dispatchTouchEvent action = %s，return result = %b", WidgetUtil.getMotionEventDesc(ev), result);
        return result;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
//        boolean result = super.onInterceptTouchEvent(ev);
//        NLog.d(TAG, "MyViewGroupB onInterceptTouchEvent action = %s, return result = %b", WidgetUtil.getMotionEventDesc(ev), result);
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*boolean result = super.onTouchEvent(event);
        NLog.d(TAG, "MyViewGroupB onTouchEvent action = %s, ", WidgetUtil.getMotionEventDesc(event));
        return result;*/
        NLog.d(TAG, "MyViewGroupB onTouchEvent action = %s, ", WidgetUtil.getMotionEventDesc(event));
        return false;
    }
}
