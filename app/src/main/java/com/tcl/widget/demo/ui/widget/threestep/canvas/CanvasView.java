package com.tcl.widget.demo.ui.widget.threestep.canvas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;

/**
 * Created by jerryliu on 2017/6/10.
 */

public class CanvasView extends View {
    private Paint mPaint;
    private Bitmap mBitamp;
    private Canvas mBmpCanvas;
    public CanvasView(Context context) {
        super(context,null);
        init();
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public CanvasView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(ResUtil.getColor(R.color.red));
        mPaint.setTextSize(ResUtil.sp2px(16));

        mBitamp = Bitmap.createBitmap(500,500, Bitmap.Config.ARGB_8888);
        mBmpCanvas = new Canvas(mBitamp);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //文字是画在bitmap上
        mBmpCanvas.drawText("Haha",0,100,mPaint);

        canvas.drawBitmap(mBitamp,0,0,mPaint);
    }
}
