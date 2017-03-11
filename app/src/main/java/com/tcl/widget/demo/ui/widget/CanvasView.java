package com.tcl.widget.demo.ui.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.uti.NLog;

/**
 * Created by lenovo on 2016/9/16.
 */

public class CanvasView extends View {
    private static final String TAG = CanvasView.class.getSimpleName();
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int mWidth, mHeight;
    private Bitmap mBitmap;
    public CanvasView(Context context) {
        super(context, null);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
        initPaint();
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
        initPaint();
    }

    private void init(Context context, AttributeSet attrs){


    }
    private void initPaint(){
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        NLog.d(TAG, "onMeasure widthMode = %d, widthSiz = %d, heightMode = %d, heightSize = %d",
                widthMode,widthSize, heightMode,heightSize );
        switch (widthMode){
            case MeasureSpec.EXACTLY:
                NLog.d(TAG, "onMeasure widthMode MeasureSpec.EXACTLY");
                break;
            case MeasureSpec.AT_MOST:
                NLog.d(TAG, "onMeasure widthMode MeasureSpec.AT_MOST");
                break;
            case MeasureSpec.UNSPECIFIED:
                NLog.d(TAG, "onMeasure widthMode MeasureSpec.UNSPECIFIED");
                break;

        }
        switch (heightMode){
            case MeasureSpec.EXACTLY:
                NLog.d(TAG, "onMeasure heightMode MeasureSpec.EXACTLY");
                break;
            case MeasureSpec.AT_MOST:
                NLog.d(TAG, "onMeasure heightMode MeasureSpec.AT_MOST");
                break;
            case MeasureSpec.UNSPECIFIED:
                NLog.d(TAG, "onMeasure heightMode MeasureSpec.UNSPECIFIED");
                break;

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setStyle(Paint.Style.STROKE);
        canvas.translate(mWidth /2, mHeight/2);

        /*canvas.drawCircle(0, 0, 200, mPaint);
        canvas.drawCircle(0, 0, 180, mPaint);

        for (int i = 0; i <= 360; i+= 10){
            canvas.drawLine(0,180, 0,200, mPaint);
            canvas.rotate(360 / LINE_COUNT);
        }*/

        /*if (mBitmap == null){
            mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.poly_test);
        }
        Rect srcRect = new Rect(0, 0 , mBitmap.getWidth() / 2, mBitmap.getHeight() / 2);
        Rect destRect = new Rect(0, 0, 200, 400);
        canvas.drawBitmap(mBitmap, srcRect, destRect, null);*/

        canvas.scale(1,-1);
        /*Path path = new Path();
        path.addRect(-200,-200,200,200, Path.Direction.CW);

        Path src = new Path();
        src.addCircle(0,0,100, Path.Direction.CW);
        path.addPath(src, 0 ,200);

        canvas.drawPath(path, mPaint);*/

        Path path2 = new Path();
        path2.lineTo(100,100);

        RectF rect = new RectF(0,0,150,150);
        //path2.addArc(mTextRect, 0 ,270);
        //path2.arcTo(mTextRect,0,270,true);
        path2.arcTo(rect,0,270);

        canvas.drawPath(path2, mPaint);
    }

    private static final int LINE_COUNT = 36;
}
