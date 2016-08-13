package com.tcl.widget.demo.widget.circleprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.Logger;
import com.tcl.widget.demo.uti.UnitUtils;

/**
 * @author Jerry
 * @Description:
 * @date 2016/8/13 11:16
 * @copyright TCL-MIG
 */

public class SolidCircleProgressBar extends ProgressBar {
    private static final String TAG = SolidCircleProgressBar.class.getSimpleName();
    //背景颜色
    private int mBackgroundColor;
    private int mProgressTextSize;
    private int mProgressTextColor;

    private static final String COLOR_FFF2A670 = "#fff2a670";
    private static final String COLOR_FFD3D3D5 = "#ffe3e3e5";
    //进度条背景颜色
    private int mProgressBackgroundColor;
    //进度条开始颜色
    private int mProgressStartColor;

    //进度条文字的画笔
    private Paint mProgressTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //整个背景的画笔
    private Paint mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    //进度条背景的画笔
    private Paint mProgressBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //进度条画笔
    private Paint mProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private static final float DEFAULT_PROGRESS_TEXT_SIZE = 11.0f;

    private int mCenterX; //圆中心的x坐标
    private int mCenterY; //圆中心的Y坐标
    private int mRadius;  //圆半径

    private RectF mProgressRect = new RectF();
    private Rect mProgressTextRect = new Rect();

    private static final float DEFAULT_START_DEGREE = -90.0f;

    public SolidCircleProgressBar(Context context) {
        super(context);
        init(context, null);
    }

    public SolidCircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
        initPaint();
    }

    private void init(Context context, AttributeSet attrs){
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);
        mBackgroundColor = a.getColor(R.styleable.CircleProgressBar_background_color, Color.TRANSPARENT);
        mProgressTextColor = a.getColor(R.styleable.CircleProgressBar_progress_text_color, Color.parseColor(COLOR_FFF2A670));
        mProgressTextSize = a.getDimensionPixelSize(R.styleable.CircleProgressBar_progress_text_size, UnitUtils.dip2px(DEFAULT_PROGRESS_TEXT_SIZE));

        mProgressBackgroundColor = a.getColor(R.styleable.CircleProgressBar_progress_background_color, Color.parseColor(COLOR_FFD3D3D5));

        mProgressStartColor = a.getColor(R.styleable.CircleProgressBar_progress_background_color, Color.parseColor(COLOR_FFD3D3D5));
        a.recycle();
    }

    private void initPaint(){
        mBackgroundPaint.setStyle(Paint.Style.FILL);
        mBackgroundPaint.setColor(mBackgroundColor);

        mProgressTextPaint.setTextAlign(Paint.Align.CENTER);
        mProgressTextPaint.setColor(mProgressTextColor);
        mProgressTextPaint.setTextSize(mProgressTextSize);

        mProgressBackgroundPaint.setStyle(Paint.Style.FILL);
        mProgressBackgroundPaint.setColor(mProgressBackgroundColor);

        mProgressPaint.setStyle(Paint.Style.FILL);
        mProgressPaint.setColor(mProgressStartColor);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;
        mRadius = Math.min(mCenterX, mCenterY);
        Logger.d(TAG, "onSizeChanged w = "+ w +", h = "+h +", mRadius = "+mRadius);
        mProgressRect.top = mCenterY - mRadius;
        mProgressRect.bottom = mCenterY + mRadius;
        mProgressRect.left = mCenterX - mRadius;
        mProgressRect.right = mCenterX + mRadius;
    }



    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        drawProgress(canvas);
        drawProgressText(canvas);
    }

    private void drawBackground(Canvas canvas){
        if (mBackgroundColor != Color.TRANSPARENT){
            canvas.drawCircle(mCenterX, mCenterY, mRadius, mBackgroundPaint);
        }
    }

    private void drawProgress(Canvas canvas){
        canvas.drawArc(mProgressRect, DEFAULT_START_DEGREE, 360.0f, false, mProgressBackgroundPaint);
        canvas.drawArc(mProgressRect, DEFAULT_START_DEGREE, 360.0f * getProgress() / getMax(), true, mProgressPaint);
    }

    private void drawProgressText(Canvas canvas){
        String text = getProgress() + "%";
        mProgressTextPaint.getTextBounds(text, 0, text.length(), mProgressTextRect);
        canvas.drawText(text, mCenterX, mCenterY + mProgressTextRect.height() / 2, mProgressTextPaint);
    }

}
