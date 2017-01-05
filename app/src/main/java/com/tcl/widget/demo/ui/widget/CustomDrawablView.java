package com.tcl.widget.demo.ui.widget;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;

/**
 * @author Jerry
 * @Description:
 * @date 2017/1/3 17:39
 * @copyright TCL-MIG
 */

public class CustomDrawablView extends Drawable {
    private ShapeDrawable mDrawable;

    public CustomDrawablView() {
        init();
    }

    private void init(){
        int x = 10;
        int y = 10;
        int width = 300;
        int height = 50;
        mDrawable = new ShapeDrawable(new OvalShape());
        mDrawable.setBounds(x,y,x+width,y+height);
    }
    @Override
    public void draw(Canvas canvas) {
        mDrawable.draw(canvas);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }


    @Override
    public int getOpacity() {
        return 0;
    }
}
