package com.tcl.widget.demo.ui.widget.github;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.SweepGradient;
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

public class CircleProgressView extends View {
    public static final String TAG = CircleProgressView.class.getSimpleName();
    private TextPaint mValuePaint;
    private TextPaint mHintPaint;
    private TextPaint mUnitPaint;

    private Paint mArcPaint;

    private Paint mArcBgPaint;

    private float mArcStrokeWidth = 10;

    private float mRadis;

    private float mValueOffset;

    private int mTextSize = 16;


    private Point mCenterPoint;
    private RectF mArcRectF;

    private float mStartAngel;
    private float mSweepAngle;
    private int mArcBgColor;


    private float mMaxValue;

    private float mValue = 50;
    private float mPercent;
    private SweepGradient mSweepGradient;


    public CircleProgressView(Context context) {
        super(context,null);
        init(null);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init(attrs);
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }



    private void init(AttributeSet attrs){

        initAttrs(attrs);

        mValuePaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mValuePaint.setColor(ResUtil.getColor(R.color.black));
        mValuePaint.setTextSize(ResUtil.sp2px(mTextSize));
        mValuePaint.setFakeBoldText(true);
        mValuePaint.setTextAlign(Paint.Align.CENTER);

        mHintPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mHintPaint.setColor(ResUtil.getColor(R.color.black));
        mHintPaint.setTextSize(ResUtil.sp2px(mTextSize));
        mHintPaint.setTextAlign(Paint.Align.CENTER);

        mUnitPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mUnitPaint.setColor(ResUtil.getColor(R.color.black));
        mUnitPaint.setTextSize(ResUtil.sp2px(mTextSize));
        mUnitPaint.setTextAlign(Paint.Align.CENTER);


        mArcBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcBgPaint.setStyle(Paint.Style.STROKE);
        mArcBgPaint.setStrokeWidth(mArcStrokeWidth);
        mArcBgPaint.setColor(mArcBgColor);
        mArcBgPaint.setStrokeCap(Paint.Cap.ROUND);

        mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(mArcStrokeWidth);
        mArcPaint.setStrokeCap(Paint.Cap.ROUND);

        mCenterPoint = new Point();
        mArcRectF = new RectF();

        setArcPaintColor();

        setValue(mValue);
    }

    private void initAttrs(AttributeSet attrs){
        if (attrs != null){
            TypedArray array = null;
            try {
                array = getContext().obtainStyledAttributes(attrs,R.styleable.circle_color_progress);
                mArcBgColor = array.getColor(R.styleable.circle_color_progress_bgArcColor, Color.WHITE);
                mMaxValue = array.getFloat(R.styleable.circle_color_progress_max_value,100);
                mStartAngel = array.getFloat(R.styleable.circle_color_progress_start_angel,135);
                mSweepAngle = array.getFloat(R.styleable.circle_color_progress_sweep_angel,270);
            }finally {
                if (array != null){
                    array.recycle();
                }
            }
        }
    }


    private ValueAnimator animator;



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int defaultSize = ResUtil.dip2px(150);
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

        mValueOffset = mCenterPoint.y + getBaselineOffsetY(mValuePaint);

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawText(canvas);
        drawArc(canvas);
    }
    DecimalFormat decimalFormat = new DecimalFormat("#");
    private void drawText(Canvas canvas){

        canvas.drawText(decimalFormat.format(mValue), mCenterPoint.x, mValueOffset, mValuePaint);

        float mHintOffset = mValueOffset - mRadis * 0.33f;
        canvas.drawText("截止目前已走",mCenterPoint.x, mHintOffset,mHintPaint);

        float mUnitOffset = mValueOffset + mRadis * 0.33f;
        canvas.drawText("步",mCenterPoint.x, mUnitOffset,mUnitPaint);
    }

    private void drawArc(Canvas canvas){
        //画进度
        float currentAngel = mSweepAngle * mPercent;
        canvas.rotate(mStartAngel, mCenterPoint.x, mCenterPoint.y);
        //画背景
        canvas.drawArc(mArcRectF,currentAngel,mSweepAngle - currentAngel,false,mArcBgPaint);
        //画当前进度
        canvas.drawArc(mArcRectF,2,currentAngel,false, mArcPaint);
        canvas.restore();
    }


    public void setValue(float value){
        if (value > mMaxValue){
            value = mMaxValue;
        }
        float start = mPercent;
        float end = value / mMaxValue;
        startAnimator(start,end);
    }




    private void setArcPaintColor(){
        int[] mGradientColors = {Color.BLUE, Color.YELLOW, Color.GREEN};
        mSweepGradient = new SweepGradient(mCenterPoint.x, mCenterPoint.y, mGradientColors, null);
        mArcPaint.setShader(mSweepGradient);
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



    public void reset(){
        startAnimator(mPercent,0);
    }


    public float getMaxValue() {
        return mMaxValue;
    }

    private float getBaselineOffsetY(Paint paint){
        return MeasureUtil.measureTextHeight(paint) / 2;
    }
}
