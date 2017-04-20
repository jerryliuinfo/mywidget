package com.tcl.widget.demo.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.uti.NLog;

/**
 * Created by jerryliu on 2017/4/14.
 */

public class LineChartView extends View {
    public static final String TAG = LineChartView.class.getSimpleName();
    public LineChartView(Context context) {
        this(context,null);
    }

    public LineChartView(Context context, @Nullable AttributeSet attrs) {
        this(context,null,0);
    }

    public LineChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    RectF rectF;
    private void init(Context context, @Nullable AttributeSet attrs){
        rectF = new RectF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        rectF.left = getPaddingLeft();
        rectF.right = getPaddingLeft() + w;
        rectF.top = getPaddingTop();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        /*int measuredWidth = measureWidth(widthMeasureSpec);
        int measuredHeight = measureHeight(heightMeasureSpec);
        setMeasuredDimension(measuredWidth, measuredHeight);
        NLog.d(TAG, "onMeasure measuredWidth = %s, measuredHeight = %s", measuredWidth,measuredHeight);*/
        NLog.d(TAG, "onMeasure width = %s, height = %s", getMeasuredWidth(),getMeasuredHeight());
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }


}
