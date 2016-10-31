package com.tcl.widget.demo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.tcl.widget.demo.uti.NLog;

/**
 * @author Jerry
 * @Description:
 * @date 2016/10/29 14:49
 * @copyright TCL-MIG
 */

public class MyButton extends Button {
    public static final String  TAG = MyViewGroupA.TAG;
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean result = super.dispatchTouchEvent(ev);
        NLog.d(TAG, "MyButton dispatchTouchEvent action = %s, return result = %b", ViewUtil.getMotionEventDesc(ev), result);
        return result;
    }




    @Override
    public boolean onTouchEvent(MotionEvent event) {
        /*boolean result = super.onTouchEvent(event);
        NLog.d(TAG, "MyButton onTouchEvent action = %s, return result = %b", ViewUtil.getMotionEventDesc(event), result);
        return result;*/
        NLog.d(TAG, "MyButton onTouchEvent action = %s", ViewUtil.getMotionEventDesc(event));
        //return super.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}
