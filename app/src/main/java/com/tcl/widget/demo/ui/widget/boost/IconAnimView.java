package com.tcl.widget.demo.ui.widget.boost;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by jerryliu on 2017/4/28.
 */

public class IconAnimView extends BoostAnimator {
    public static final String TAG = IconGatherView.TAG;
    private Bitmap bitmap;
    private int r = 70;
    public void setBitmap(Bitmap bitmap){
        this.bitmap = bitmap;
        srcRect = new Rect(0,0,bitmap.getWidth(),bitmap.getHeight());
    }
    private Rect srcRect = new Rect();
    private Rect desRect = new Rect();

    @Override
    protected void onDraw(Canvas canvas, Paint paint, float x, float y, float fraction) {
        int originalAlpha = paint.getAlpha();
        int currentAlpha = (int) (255 * fraction);
        paint.setAlpha(originalAlpha);
        //NLog.d(TAG, "IconAnimView fraction = %s", fraction);
        int xx = (int) x;
        int yy = (int) y;
        int scaler = (int) (r * fraction);
        desRect.set(xx,yy-scaler,xx + scaler,yy+scaler);
        canvas.drawBitmap(bitmap, srcRect,desRect,paint);
        paint.setAlpha(originalAlpha);
    }
}
