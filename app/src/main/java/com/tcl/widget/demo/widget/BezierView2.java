package com.tcl.widget.demo.widget;

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

public class BezierView2 extends View {
    private static final String TAG = BezierView2.class.getSimpleName();
    int mWidth,mHeight;
    private int mCenterX, mCenterY;
    private Paint mDotPaint;
    private Paint mAssistPaint;
    private Paint mBezierPaint;
    private PointF mStart,mEnd, mControl1,mControl2;
    private boolean mode = true;

    public BezierView2(Context context) {
        super(context);
    }

    public BezierView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BezierView2(Context context, AttributeSet attrs, int defStyleAttr) {
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
        mControl1 = new PointF(0,0);
        mControl2 = new PointF(0,0);
    }

    public void setMode(boolean mode) {
        this.mode = mode;
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

        mControl1.x = mCenterX;
        mCenterY = mCenterY - 100;

        mControl2.x = mCenterX;
        mControl2.y = mCenterY - 100;

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        if (mode){
            mControl1.x = x;
            mControl1.y = y;
        }else {
            mControl2.x = x;
            mControl2.y = y;
        }

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
        canvas.drawPoint(mControl1.x, mControl1.y, mDotPaint);
        canvas.drawPoint(mControl2.x, mControl2.y, mDotPaint);
    }

    private void drawAssistLine(Canvas canvas){
        canvas.drawLine(mStart.x,mStart.y, mControl1.x, mControl1.y,mAssistPaint);
        canvas.drawLine(mEnd.x,mEnd.y, mControl2.x, mControl2.y,mAssistPaint);
        canvas.drawLine(mControl1.x, mControl1.y, mControl2.x, mControl2.y,mAssistPaint);
    }

    private void drawBeizerLine(Canvas canvas){
        Path path = new Path();
        path.moveTo(mStart.x,mStart.y);
        path.cubicTo(mControl1.x, mControl1.y, mControl2.x,mControl2.y, mEnd.x, mEnd.y);
        canvas.drawPath(path, mBezierPaint);

    }


}
