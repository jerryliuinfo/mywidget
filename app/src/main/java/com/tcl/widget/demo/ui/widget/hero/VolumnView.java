package com.tcl.widget.demo.ui.widget.hero;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;

import java.util.Random;

/**
 * Created by jerryliu on 2017/4/22.
 */

public class VolumnView extends View{
    private Paint mRectPaint;
    private Paint mCoordinatePaint;
    public VolumnView(Context context) {
        super(context,null);
        init();
    }

    public VolumnView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public VolumnView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectPaint.setStyle(Paint.Style.FILL);
        mRectPaint.setColor(ResUtil.getColor(R.color.blue));

        mCoordinatePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCoordinatePaint.setStyle(Paint.Style.STROKE);
        mCoordinatePaint.setStrokeWidth(ResUtil.dip2px(2));
        mCoordinatePaint.setColor(ResUtil.getColor(R.color.white));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    private int mRectCount = 10;
    public static final int GAP = 10;
    private int mRectWidth = 30;
    public static final int RECT_HEGITH = 500;
    private int mCurrentRectHeight;
    private int mPadingLeft = 20;
    private Random random = new Random();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(getPaddingLeft(),getPaddingTop());
        drawXCoordinate(canvas);
        drawYCoordinate(canvas);
        for (int i = 0; i < mRectCount; i++){
            float left = mPadingLeft + (mRectWidth + GAP) * i;

            mCurrentRectHeight = random.nextInt(RECT_HEGITH);
            float top = getMeasuredHeight() - mCurrentRectHeight;
            float right = left + mRectWidth;
            float bottom = getMeasuredHeight() - mCoordinatePaint.getStrokeWidth() / 2;


            canvas.drawRect(left,top,right,bottom,mRectPaint);
        }
        canvas.restore();
        postInvalidateDelayed(500);

    }

    private void drawXCoordinate(Canvas canvas){
        canvas.drawLine(0, getMeasuredHeight(),getMeasuredWidth() - getPaddingRight() - ResUtil.dip2px(20), getMeasuredHeight(), mCoordinatePaint );
    }

    private void drawYCoordinate(Canvas canvas){
        canvas.drawLine(0, getMeasuredHeight(),0, 0, mCoordinatePaint );
    }

}
