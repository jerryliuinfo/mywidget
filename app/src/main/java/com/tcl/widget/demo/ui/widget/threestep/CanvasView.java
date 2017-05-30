package com.tcl.widget.demo.ui.widget.threestep;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.uti.ResUtil;

/**
 * Created by jerryliu on 2017/5/29.
 */

public class CanvasView extends View {
    private Paint mPaint;
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
        mPaint.setStrokeWidth(ResUtil.dip2px(3));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(0,0,400,200, mPaint);

        //canvas.translate(100,100);
        //canvas.rotate(30);
        canvas.scale(0.5f,0.5f);
        canvas.drawRect(0,0,400,200, mPaint);
    }
}
