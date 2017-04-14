package com.tcl.widget.demo.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.tcl.widget.demo.uti.NLog;

/**
 * Created by lenovo on 2016/9/24.
 */

public class BezierView extends View {
    private static final String TAG = BezierView.class.getSimpleName();
    int mWidth,mHeight;
    private int mCenterX, mCenterY;
    private Paint mDotPaint;
    private Paint mAssistPaint;
    private Paint mBezierPaint;
    private PointF mStart,mEnd,mControl;
    public BezierView(Context context) {
        super(context);
    }

    public BezierView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezierView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mDotPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDotPaint.setStyle(Paint.Style.STROKE);
        mDotPaint.setColor(Color.GRAY);
        mDotPaint.setStrokeWidth(20);

        mAssistPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAssistPaint.setStyle(Paint.Style.STROKE);
        mAssistPaint.setColor(Color.GRAY);
        mAssistPaint.setStrokeWidth(4);


        mBezierPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBezierPaint.setStyle(Paint.Style.STROKE);
        mBezierPaint.setColor(Color.RED);
        mBezierPaint.setStrokeWidth(8);


        mStart = new PointF(0,0);
        mEnd = new PointF(0,0);
        mControl = new PointF(0,0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w ;
        mHeight = h ;
        mCenterX = w / 2;
        mCenterY = h / 2;
        NLog.d(TAG, "onSizeChanged w = %d, h = %d", w,h);

        //
        mStart.x = mCenterX - 200;
        mStart.y = mCenterY;

        mEnd.x = mCenterX + 200;
        mEnd.y = mCenterY;

        mControl.x = mCenterX;
        mCenterY = mCenterY - 100;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        mControl.x = x;
        mControl.y = y;
        invalidate();
        return true;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawPoint(canvas);
        drawAssistLine(canvas);
        drawBeizerLine(canvas);
    }

    private void drawPoint(Canvas canvas){
        canvas.drawPoint(mStart.x, mStart.y, mDotPaint);
        canvas.drawPoint(mEnd.x,mEnd.y, mDotPaint);
        canvas.drawPoint(mControl.x,mControl.y, mDotPaint);
    }

    private void drawAssistLine(Canvas canvas){
        canvas.drawLine(mStart.x,mStart.y,mControl.x,mControl.y,mAssistPaint);
        canvas.drawLine(mEnd.x,mEnd.y,mControl.x,mControl.y,mAssistPaint);
    }

    private void drawBeizerLine(Canvas canvas){
        Path path = new Path();
        path.moveTo(mStart.x,mStart.y);
        path.quadTo(mControl.x, mControl.y, mEnd.x,mEnd.y);
        canvas.drawPath(path, mBezierPaint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));

    }

    private int measureWidth(int widthMeasureSpec){
        int result = 500;
        int specMode = MeasureSpec.getMode(widthMeasureSpec);
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else if (specMode == MeasureSpec.AT_MOST){
            result = specSize;
        }
        return result;
    }

    private int measureHeight(int heightMeasureSpec){
        int result = 500;
        int specMode = MeasureSpec.getMode(heightMeasureSpec);
        int specSize = MeasureSpec.getSize(heightMeasureSpec);
        if (specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else if (specMode == MeasureSpec.AT_MOST){
            result = specSize;
        }
        return result;
    }



}
