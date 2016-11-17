package com.tcl.widget.demo.widget;

import android.view.MotionEvent;

/**
 * @author Jerry
 * @Description:
 * @date 2016/10/29 15:09
 * @copyright TCL-MIG
 */

public class WidgetUtil {
    public static String getMotionEventDesc(MotionEvent event){
        if (event.getAction() == MotionEvent.ACTION_DOWN){
            return "ACTION_DOWN";
        }else if (event.getAction() == MotionEvent.ACTION_UP){
            return "ACTION_UP";
        }else if (event.getAction() == MotionEvent.ACTION_MOVE){
            return "ACTION_MOVE";
        }else if (event.getAction() == MotionEvent.ACTION_CANCEL){
            return "ACTION_CANCEL";
        }

        return "";
    }
}
