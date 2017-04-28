package com.tcl.widget.demo.ui.widget;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by jerryliu on 2017/4/28.
 */

public class BubbleView extends BoostAnimator {
    public int mRadis;

    public BubbleView(int mRadis) {
        this.mRadis = mRadis;
    }

    @Override
    protected void onDraw(Canvas canvas, Paint paint, float x, float y, float fraction) {
        int originalAlpha = paint.getAlpha();
        int currentAlpha = (int) (255 * fraction);
        paint.setAlpha(currentAlpha);

        canvas.drawCircle(x,y,mRadis * fraction,paint);
        //恢复画笔透明度
        paint.setAlpha(originalAlpha);
    }
}
