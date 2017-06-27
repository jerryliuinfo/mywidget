package com.tcl.widget.demo.ui.widget.github;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;
import com.tcl.widget.demo.uti.view.MeasureUtil;

import java.text.DecimalFormat;

/**
 * Created by jerryliu on 2017/6/23.
 */

public class WaveProgressView extends View {
    public static final String TAG = WaveProgressView.class.getSimpleName();
    private TextPaint mValuePaint;

    private Paint mArcPaint;

    private Paint mArcBgPaint;

    private float mArcStrokeWidth = 10;

    private float mRadis;


    private int mTextSize = 16;


    private Point mCenterPoint;
    private RectF mArcRectF;


    private float mValue = 50;
    private float mMaxValue = 100;
    private float mPercent;

    private Paint mWavePaint;


    public WaveProgressView(Context context) {
        super(context,null);
        init(null);
    }

    public WaveProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init(attrs);
    }

    public WaveProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }



    private void init(AttributeSet attrs){
        mValuePaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mValuePaint.setColor(ResUtil.getColor(R.color.black));
        mValuePaint.setTextSize(ResUtil.sp2px(mTextSize));
        mValuePaint.setFakeBoldText(true);
        mValuePaint.setTextAlign(Paint.Align.CENTER);

        mArcBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcBgPaint.setStyle(Paint.Style.STROKE);
        mArcBgPaint.setStrokeWidth(mArcStrokeWidth);
        mArcBgPaint.setColor(ResUtil.getColor(R.color.white));
        mArcBgPaint.setStrokeCap(Paint.Cap.ROUND);

        mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(mArcStrokeWidth);
        mArcPaint.setStrokeCap(Paint.Cap.ROUND);
        mArcPaint.setColor(ResUtil.getColor(R.color.green));

        mCenterPoint = new Point();
        mArcRectF = new RectF();

        setValue(mValue);


    }

    public void setValue(float value){
        if (value > mMaxValue){
            value = mMaxValue;
        }
        float start = mPercent;
        float end = value / mMaxValue;
        startAnimator(start,end);
    }

    public void startAnimator(float start, float end){
        animator = ValueAnimator.ofFloat(start,end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPercent = (float) animation.getAnimatedValue();
                mValue = mPercent * mMaxValue;
                invalidate();
            }
        });
        animator.setDuration(1000);
        animator.start();
    }




    private ValueAnimator animator;



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int defaultSize = ResUtil.dp2px(150);
        setMeasuredDimension(MeasureUtil.getMeasuredLength(widthMeasureSpec,defaultSize),MeasureUtil.getMeasuredLength(heightMeasureSpec,defaultSize));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        float minSize = Math.min((w - getPaddingLeft() - getPaddingRight() - 2 * mArcStrokeWidth),h-getPaddingTop()-getPaddingBottom() - 2 * mArcStrokeWidth);
        mRadis = minSize / 2;

        mCenterPoint.set(w /2, h /2);
        mArcRectF.left = mCenterPoint.x - mRadis - mArcStrokeWidth / 2;
        mArcRectF.right = mCenterPoint.x + mRadis + mArcStrokeWidth / 2;
        mArcRectF.top = mCenterPoint.y - mRadis - mArcStrokeWidth /2;
        mArcRectF.bottom = mCenterPoint.y + mRadis + mArcStrokeWidth /2;


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawText(canvas);
        drawArc(canvas);
    }
    DecimalFormat decimalFormat = new DecimalFormat("#");
    private void drawText(Canvas canvas){
        canvas.drawText(decimalFormat.format(mValue) + "%", mCenterPoint.x, mCenterPoint.y + getBaselineOffsetY(mValuePaint), mValuePaint);
    }

    private void drawArc(Canvas canvas){
        canvas.save();
        canvas.rotate(270, mCenterPoint.x,mCenterPoint.y);
        float mCurrentAngel = 360 * mPercent;
        //画背景
        canvas.drawArc(mArcRectF, mCurrentAngel, 360 - mCurrentAngel,false,mArcBgPaint);
        //画进度
        canvas.drawArc(mArcRectF, 0, mCurrentAngel,false,mArcPaint);
    }

    private void drawDarkWave(Canvas canvas){

    }


    public float getMaxValue() {
        return mMaxValue;
    }

    private float getBaselineOffsetY(Paint paint){
        return MeasureUtil.measureTextHeight(paint) / 2;
    }
}
