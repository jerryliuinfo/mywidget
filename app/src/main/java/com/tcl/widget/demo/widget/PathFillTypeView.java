package com.tcl.widget.demo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.uti.NLog;

/**
 * @author Jerry
 * @Description:
 * @date 2016/10/15 10:28
 * @copyright TCL-MIG
 */

public class PathFillTypeView extends View {
    private static final String TAG = PathFillTypeView.class.getSimpleName();
    private Paint mPaint;


    public PathFillTypeView(Context context) {
        super(context);
        init();
    }

    public PathFillTypeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public PathFillTypeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(android.R.color.black));
    }

    private int mWiewWdith, mViewHeight;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        NLog.d(TAG, "onSizeChanged w = %d, h = %d", w, h);
        mWiewWdith = w;
        mViewHeight = h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        NLog.d(TAG, "onMeasure widthMode = %d, widthSize = %d, heightMode = %d, heightSize = %d", widthMode,widthSize,heightMode,heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWiewWdith / 2, mViewHeight /2);
        Path path = new Path();

        //path.setFillType(Path.FillType.EVEN_ODD);
        path.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        path.addRect(-100,-100,100,100, Path.Direction.CW);


        canvas.drawPath(path, mPaint);
    }
}
