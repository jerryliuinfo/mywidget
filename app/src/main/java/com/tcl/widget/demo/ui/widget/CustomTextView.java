package com.tcl.widget.demo.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.NLog;
import com.tcl.widget.demo.uti.ResUtil;

/**
 * Created by jerryliu on 2017/4/15.
 */

public class CustomTextView extends android.support.v7.widget.AppCompatTextView {
    private static final String TAG = CustomTextView.class.getSimpleName();

    public CustomTextView(Context context) {
        super(context,null);
        init();
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private Paint mOuterPaint;
    private Paint mInnerPaint;
    private RectF mOuterRect,mInnerRect;
    private int mOutPaintWidth= 2;
    private int mInnerPaintWidth =5;
    private int gap = 10;



    private void init(){
        mOuterPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOuterPaint.setColor(getResources().getColor(R.color.blue));
        mOuterPaint.setStyle(Paint.Style.STROKE);
        mOuterPaint.setStrokeWidth(ResUtil.dip2px(mOutPaintWidth));

        mInnerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInnerPaint.setColor(getResources().getColor(R.color.red));
        mInnerPaint.setStyle(Paint.Style.STROKE);
        mInnerPaint.setStrokeWidth(ResUtil.dip2px(mInnerPaintWidth));

        gap = (int) mOuterPaint.getStrokeWidth()+20;

        mOuterRect = new RectF();
        mInnerRect = new RectF();
        NLog.d(TAG, "init");
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        NLog.d(TAG, "onSizeChanged");
        if (mOuterRect != null){
            mOuterRect.left = 0;
            mOuterRect.top = 0;
            mOuterRect.right = getMeasuredWidth();
            mOuterRect.bottom = getMeasuredHeight();
        }

        if (mInnerRect != null){
            mInnerRect.left = gap;
            mInnerRect.top = gap;
            mInnerRect.right = getMeasuredWidth() - gap;
            mInnerRect.bottom = getMeasuredHeight() - gap;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        NLog.d(TAG, "onDraw");
        if (mOuterRect != null){
            canvas.drawRect(mOuterRect, mOuterPaint);
        }
        if (mInnerRect != null){
            canvas.drawRect(mInnerRect,mInnerPaint);
        }

        super.onDraw(canvas);
    }
}
