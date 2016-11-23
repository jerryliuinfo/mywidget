package com.tcl.widget.demo.ui.widget.guolin;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.DisplayUtil;
import com.tcl.widget.demo.uti.NLog;

import static com.tcl.widget.demo.R.styleable.HealthTable_bgColor;
import static com.tcl.widget.demo.R.styleable.HealthTable_lineColor;

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

    /**
     * 绘制类型,比如,画步数,画心率,画睡眠等
     */
    private String mDrawType;

    /**
     * 大圆点填充色
     */
    private int mMaxcircleColor;



    public HealthTableView(Context context) {
        this(context,null);
    }

    public HealthTableView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public HealthTableView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }


    private Paint xyPaint;

    private void init(Context context, AttributeSet attrs, int defStyleAttr){
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.HealthTable);
        mBgColor = array.getColor(HealthTable_bgColor, Color.WHITE);

        mCoordinatesLineWidth = array.getDimensionPixelSize(R.styleable.HealthTable_coordinatesLineWidth, 2);
        mCoordinatesTextColor = array.getColor(R.styleable.HealthTable_coordinatesTextColor,Color.BLACK);
        mCoordinatesTextSize = array.getDimensionPixelSize(R.styleable.HealthTable_coordinatesTextSize, DisplayUtil.sp2px(11));

        mLineColor = array.getColor(HealthTable_lineColor, Color.BLUE);
        mLineWidth = array.getDimensionPixelSize(R.styleable.HealthTable_lineWidth,
                DisplayUtil.dp2px(2));

        mCircleradius = array.getDimensionPixelSize(R.styleable.HealthTable_averageCircleradius,
               DisplayUtil.dp2px(10));

        mMaxcircleColor = array.getColor(R.styleable.HealthTable_maxcircleColor, Color.GREEN);
        mMincircleColor = array.getColor(R.styleable.HealthTable_mincircleColor, Color.WHITE);
        mDrawType = array.getString(R.styleable.HealthTable_tableType);
        array.recycle();

        xyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        xyPaint.setStrokeWidth(mCoordinatesLineWidth);
        xyPaint.setColor(mCoordinatesTextColor);
        xyPaint.setStyle(Paint.Style.FILL);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
         mHeight = MeasureSpec.getSize(heightMeasureSpec);
        NLog.d(TAG, "mWidth = %d, mHeith = %d", mWidth,mHeight);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY){
            mWidth = widthSize;
        }else {
            mWidth = 300;
        }
        if (heightMode == MeasureSpec.EXACTLY){
            mHeight = heightSize;
        }else {
            mHeight = (mWidth * 3) / 5;
        }
        setMeasuredDimension(mWidth,mHeight);




    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCoodinates(canvas);
    }


    private void drawCoodinates(Canvas canvas){
        //画x方向轴
        canvas.drawLine(getPaddingLeft(), mHeight - getPaddingBottom(), mWidth - getPaddingRight(),mHeight - getPaddingBottom(), xyPaint);
       //画x轴箭头
        int endPointX = mWidth - getPaddingRight();
        int endPointY = mHeight - getPaddingBottom();
        canvas.drawLine(mWidth - getPaddingRight() - 20, mHeight - getPaddingBottom() - 10, endPointX,endPointY, xyPaint);
        canvas.drawLine(mWidth - getPaddingRight() - 20, mHeight - getPaddingBottom() + 10, endPointX,endPointY, xyPaint);

        //画y方向轴
        canvas.drawLine(getPaddingLeft(), mHeight - getPaddingBottom(), getPaddingLeft(),getPaddingTop(), xyPaint);
        //画y轴箭头
        int yendPointX = getPaddingLeft();
        int yendPointY = getPaddingTop();
        canvas.drawLine(getPaddingLeft() - 10, getPaddingTop() + 20, yendPointX,yendPointY, xyPaint);
        canvas.drawLine(getPaddingLeft() + 10, getPaddingTop() + 20, yendPointX,yendPointY, xyPaint);
    }





}
