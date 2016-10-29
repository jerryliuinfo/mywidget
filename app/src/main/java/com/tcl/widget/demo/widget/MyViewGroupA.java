package com.tcl.widget.demo.widget;

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

public class MyViewGroupA extends LinearLayout {
    public static final String  TAG = MyViewGroupA.class.getSimpleName();
    public MyViewGroupA(Context context) {
        super(context);
    }

    public MyViewGroupA(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewGroupA(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = super.dispatchTouchEvent(ev);
        return result;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        super.onInterceptTouchEvent(ev);
       /* boolean result = super.onInterceptTouchEvent(ev);
        NLog.d(TAG, "MyViewGroupA onInterceptTouchEvent action = %s, return result = %b", ViewUtil.getMotionEventDesc(ev), result);
        return result;*/
        NLog.d(TAG, "MyViewGroupA onInterceptTouchEvent action = %s", ViewUtil.getMotionEventDesc(ev));
        return false;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //boolean result = super.onTouchEvent(event);
        NLog.d(TAG, "MyViewGroupA onTouchEvent action = %s", ViewUtil.getMotionEventDesc(event));
        return false;
    }
}
