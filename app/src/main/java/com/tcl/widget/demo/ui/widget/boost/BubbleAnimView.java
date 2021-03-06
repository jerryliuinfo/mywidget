package com.tcl.widget.demo.ui.widget.boost;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by jerryliu on 2017/4/28.
 */

public class BubbleAnimView extends BoostAnimator {
    public int mRadis;

    public BubbleAnimView(int mRadis) {
        this.mRadis = mRadis;
    }

    @Override
    protected void onDraw(Canvas canvas, Paint paint, float x, float y, float fraction) {
        int originalAlpha = paint.getAlpha();
        int currentAlpha = (int) (255 * fraction);
        paint.setAlpha(255);

        canvas.drawCircle(x,y,mRadis * fraction,paint);
        //恢复画笔透明度
        paint.setAlpha(originalAlpha);
    }
}
