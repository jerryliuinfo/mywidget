package com.tcl.widget.demo.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.NLog;
import com.tcl.widget.demo.uti.ResUtil;

/**
 * Created by jerry on 2017/3/12.
 */

public class WatchBord extends View {

    private static final String TAG = WatchBord.class.getSimpleName();
    private Canvas mCanvas;
    private int mPadding;
    private int scalinePadding;//时针和分针与圆圈的距离
    private int mWidth,mHeight;
    private int mRadis;
    //圆环画笔
    private Paint mCirclePaint;
    private Paint mScaleLinePaint;//刻度线画笔
    private Paint mTxtPaint;
    private int mHourScaleLineWidth = 40; //小时刻度线的长度
    private int mMinuteScaleLineWidth = 30; //分钟刻度线的长度
    public WatchBord(Context context) {
        this(context,null);
    }

    public WatchBord(Context context, AttributeSet attrs) {
        this(context, attrs,0);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(ResUtil.getColor(R.color.white));

        mScaleLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTxtPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTxtPaint.setTextSize(ResUtil.sp2px(10));
        mTxtPaint.setColor(ResUtil.getColor(R.color.black));

        scalinePadding = ResUtil.dip2px(10);

    }

    public WatchBord(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        NLog.d(TAG, "onSizeChanged w = %s, h = %s", w,h);
        mWidth = w;
        mHeight = h;
        mPadding = getPaddingLeft();
        mRadis = Math.min(getWidth() - getPaddingLeft() - getPaddingRight(), getHeight() - getPaddingTop() - getPaddingBottom()) / 2;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas = canvas;
        drawCircle();
        drawScaleLine();
    }

    private void drawCircle(){
        mCanvas.drawCircle(getWidth() / 2, getHeight() / 2, mRadis, mCirclePaint);
    }

    /**
     * 画小时和分钟刻度
     */
    private void drawScaleLine(){
        NLog.d(TAG, "drawScaleLine mPadding = %s, mRadis = %s, paddingTope = %s", mPadding, mRadis, getPaddingTop());
        mCanvas.save();
        for (int i = 0; i < 60; i++){
            int lineWidth = 0;
            int textSize;
            if (i  % 5 == 0){
                lineWidth = mHourScaleLineWidth;
                textSize = ResUtil.sp2px(2);
                mScaleLinePaint.setColor(ResUtil.getColor(R.color.black));
                //draw text

                String txt = String.valueOf(i == 0? 12:i / 5);
                Rect txtRect = new Rect();
                mCanvas.save();
                mTxtPaint.getTextBounds(txt,0,txt.length(),txtRect);
                mCanvas.drawText(txt, getWidth() / 2, scalinePadding + lineWidth, mTxtPaint);
                mCanvas.restore();
            }else {
                lineWidth = mMinuteScaleLineWidth;
                textSize = ResUtil.sp2px(1);
                mScaleLinePaint.setColor(ResUtil.getColor(R.color.gray));
            }
            mScaleLinePaint.setTextSize(textSize);
            mCanvas.drawLine(getWidth() / 2, scalinePadding,getWidth() / 2, scalinePadding + lineWidth,mScaleLinePaint);
            mCanvas.rotate(6, getWidth() / 2, getHeight() / 2);
        }
        mCanvas.restore();
    }

}
