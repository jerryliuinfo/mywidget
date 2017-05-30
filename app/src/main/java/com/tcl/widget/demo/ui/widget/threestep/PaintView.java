package com.tcl.widget.demo.ui.widget.threestep;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;

/**
 * Created by jerryliu on 2017/5/29.
 */

public class PaintView extends View {
    private Paint mPaint;
    public PaintView(Context context) {
        super(context,null);
        init();
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(ResUtil.getColor(R.color.red));
        mPaint.setTextSize(ResUtil.sp2px(16));
        mPaint.setShadowLayer(10,15,15, Color.BLUE);
        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String text = "hello world";
        mPaint.setStrikeThruText(true);
        mPaint.setTextSkewX(-0.25f);

        canvas.drawText("hello world", getMeasuredWidth()/2,getMeasuredHeight()/2,mPaint);
    }
}
