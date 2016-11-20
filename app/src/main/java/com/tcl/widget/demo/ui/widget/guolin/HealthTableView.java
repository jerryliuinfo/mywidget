package com.tcl.widget.demo.ui.widget.guolin;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.NLog;

/**
 * Created by lenovo on 2016/11/20.
 */

public class HealthTableView extends View {
    private static final String TAG = HealthTableView.class.getSimpleName();
    int mWidth;
    int mHeight;

    /**
     * 坐标轴宽度
     */
    private int mCoordinatesLineWidth;

    /**
     * 坐标旁边文字颜色
     */
    private int mCoordinatesTextColor;

    /**
     * 坐标旁边文字大小
     */
    private int mCoordinatesTextSize;

    /**
     * 折线颜色
     */
    private int mLineColor;

    /**
     * 圆点半径
     */
    private int mCircleradius;

    /**
     * 背景色
     */
    private int mBgColor;

    /**
     * 折线宽度
     */
    private int mLineWidth;

    /**
     * 小圆填充色
     */
    private int mMincircleColor;

    public HealthTableView(Context context) {
        super(context,null);
    }

    public HealthTableView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
    }

    public HealthTableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr){
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.HealthTable);
        mCoordinatesLineWidth = array.getDimensionPixelSize(R.styleable.HealthTable_coordinatesLineWidth, 2);
        mCoordinatesTextColor = array.getColor(R.styleable.HealthTable_coordinatesTextColor,Color.BLACK);


        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mWidth = MeasureSpec.getSize(widthMeasureSpec);
        int mHeight = MeasureSpec.getSize(heightMeasureSpec);
        NLog.d(TAG, "mWidth = %d, mHeith = %d", mWidth,mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCoodinates(canvas);
    }


    private void drawCoodinates(Canvas canvas){

    }



}
