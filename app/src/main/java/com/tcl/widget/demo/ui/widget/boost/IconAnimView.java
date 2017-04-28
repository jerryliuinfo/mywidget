package com.tcl.widget.demo.ui.widget.boost;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by jerryliu on 2017/4/28.
 */

public class IconAnimView extends BoostAnimator {
    private Bitmap bitmap;
    private int r = 100;
    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
    }

    @Override
    protected void onDraw(Canvas canvas, Paint paint, float x, float y, float fraction) {
        int originalAlpha = paint.getAlpha();
        int currentAlpha = (int) (255 * fraction);
        paint.setAlpha(currentAlpha);
        canvas.drawBitmap(bitmap,x,y,paint);
        paint.setAlpha(originalAlpha);
    }
}
