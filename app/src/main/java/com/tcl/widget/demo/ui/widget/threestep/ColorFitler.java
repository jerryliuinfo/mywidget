package com.tcl.widget.demo.ui.widget.threestep;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.uti.ResUtil;

/**
 * Created by jerryliu on 2017/5/1.
 */

public class ColorFitler extends View {
    Paint mPaint;
    public ColorFitler(Context context) {
        super(context,null);
        init(context);
    }

    public ColorFitler(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init(context);
    }

    public ColorFitler(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(ResUtil.dip2px(2));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
