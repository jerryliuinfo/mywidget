package com.tcl.widget.demo.uti;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;

import com.tcl.widget.demo.MyApplicaiton;

/**
 * @author liangni
 * @Description:资源类Util
 * @date 2016/4/11 15:36
 * @copyright TCL-MIG
 */
public class ResUtil {

    /**
     * 获取本地资源字符串
     * @param  resId
     */
    public static final String getString(int resId) {
        Context context = MyApplicaiton.getInstance();
        if (context == null || context.getResources() == null){
            return  "";
        }
        String string = "";
        try {
            string =  context.getResources().getString(resId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return string;
    }

    /**
     * 获取本地字符串
     * @param resID
     * @param obj
     * @return
     */
    public static final String getString(int resID, Object... obj){
        Context context = MyApplicaiton.getInstance();
        if (context == null || context.getResources() == null){
            return  "";
        }
        String string = "";
        try {
            string =  context.getResources().getString(resID, obj);
        } catch (Exception e) {
        }
        return string;
    }

    /**
     * 获取本地Color
     *
     * @param  resId
     */
    public static final int getColor(int resId) {
        Context context = MyApplicaiton.getInstance();
        int color = 0x00000000;
        try {
            color = context.getResources().getColor(resId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return color;
    }

    public static final ColorStateList getColorStateList(int resId) {
        Context context = MyApplicaiton.getInstance();
        ColorStateList result = null;
        try {
            result = context.getResources().getColorStateList(resId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取本地Color
     *
     * @param  resId
     */
    public static final Drawable getDrawable(int resId) {
        Context context = MyApplicaiton.getInstance();
        Drawable drawable = null;
        try {
            drawable = context.getResources().getDrawable(resId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return drawable;
    }

    /**
     * getDimensionPixelOffset 获取dimension，如果resId为sp或者dp，就进行换算，如果是px，直接使用
     * @param resId
     * @return
     */
    public static int getDimensionPixelOffset(int resId) {
        Context context = MyApplicaiton.getInstance();
        int dimension = 0;
        try {
            dimension = context.getResources().getDimensionPixelOffset(resId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dimension;
    }



    public static int dp2px(float dp){
        Context context = MyApplicaiton.getInstance();
        int px = (int) (dp + 0.5f);
        try {
            px = (int) (context.getResources().getDisplayMetrics().density * dp + 0.5f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return px;
    }

    public static int sp2px(int sp){
        Context context = MyApplicaiton.getInstance();
        int px = (int)(sp + 0.5f);
        try {
            px = (int)(context.getResources().getDisplayMetrics().scaledDensity * sp + 0.5f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return px;
    }
}
