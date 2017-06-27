package com.tcl.widget.demo.ui.widget.github;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
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
import com.tcl.widget.demo.uti.MathUtil;
import com.tcl.widget.demo.uti.ResUtil;
import com.tcl.widget.demo.uti.view.MeasureUtil;
import com.tcl.widget.demo.uti.view.PaintConfigUtil;

/**
 * Created by jerryliu on 2017/6/27.
 */

public class CustomProgressView extends View {
    public static final String TAG = CircleProgressView.class.getSimpleName();
    private Paint mCircleBgPaint;
    private Paint mProgressPaint;
    private TextPaint mTextPaint;
    private float mValue = 50;

    private float minValue = 0;
    private float maxValue = 100;

    private float mRadis;
    private Point mCenterPoint;

    private float mPercent;//当前进度百分比

    private RectF mRectF;
    //起始角度
    private float mStartAngel = 135;
    private int maxWeepAngel = 270;

    private ProgressAnimationListener mListener;

    public CustomProgressView(Context context) {
        super(context,null);
        init();
    }

    public CustomProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public CustomProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mCircleBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        PaintConfigUtil.configNormal(mCircleBgPaint, ResUtil.getColor(R.color.gray),ResUtil.dp2px(2), Paint.Style.STROKE);

        mProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        PaintConfigUtil.configNormal(mProgressPaint, ResUtil.getColor(R.color.green),ResUtil.dp2px(2), Paint.Style.STROKE);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        PaintConfigUtil.configText(mTextPaint,ResUtil.getColor(R.color.red),ResUtil.sp2px(14));

        mRectF = new RectF();

        mCenterPoint = new Point();
        setValue(mValue);
    }


    public void setValue(float value){
        value = value > maxValue? maxValue:value;
        value = value < minValue? minValue:value;
        float start = mPercent;
        float end = (value - minValue)/ (maxValue - minValue);
        startAnimator(start,end);
    }

    private ValueAnimator mValueAnimator;
    private void startAnimator(float start, float end){
        mValueAnimator = ValueAnimator.ofFloat(start,end);
        mValueAnimator.setDuration(1000);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mPercent = (float) animation.getAnimatedValue();
                mValue = mPercent * (maxValue - minValue);
                if (mListener != null){
                    mListener.onProgressUpdate(MathUtil.round(mValue));
                }
                invalidate();
            }
        });
        mValueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (mListener != null){
                    mListener.onFinish();
                }
            }
        });
        mValueAnimator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int defaultSize = 500;
        setMeasuredDimension(MeasureUtil.getMeasuredLength(widthMeasureSpec,defaultSize),MeasureUtil.getMeasuredLength(widthMeasureSpec,defaultSize));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        int minSize = (int) Math.min((getMeasuredWidth() - getPaddingLeft() - getPaddingRight() - 2 * mCircleBgPaint.getStrokeWidth()),(getMeasuredHeight() - getPaddingTop() - getPaddingBottom() - 2 * mCircleBgPaint.getStrokeWidth()));
        mRadis = minSize / 2;
        mCenterPoint.set(w/2,h/2);
        mRectF.left = mCenterPoint.x - mRadis - mCircleBgPaint.getStrokeWidth() / 2;
        mRectF.right = mCenterPoint.x + mRadis + mCircleBgPaint.getStrokeWidth() /2;
        mRectF.top = mCenterPoint.y - mRadis - mCircleBgPaint.getStrokeWidth() / 2;
        mRectF.bottom = mCenterPoint.y + mRadis + mCircleBgPaint.getStrokeWidth() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawText(canvas);
        drawArc(canvas);


    }

    private void drawArc(Canvas canvas){
        float sweepAngel = maxWeepAngel * mPercent;
        canvas.rotate(mStartAngel,mCenterPoint.x,mCenterPoint.y);
        //画背景
        canvas.drawArc(mRectF, sweepAngel, maxWeepAngel - sweepAngel,false,mCircleBgPaint);

        //画进度
        canvas.drawArc(mRectF,2,sweepAngel,false,mProgressPaint);

        canvas.restore();
    }

    private void drawText(Canvas canvas){
        canvas.drawText(String.valueOf(MathUtil.round(mValue)), mCenterPoint.x, mCenterPoint.y - MeasureUtil.getTextBaseY(mTextPaint), mTextPaint);
    }

    public float getMaxValue() {
        return maxValue;
    }

    public void setListener(ProgressAnimationListener listener) {
        this.mListener = listener;
    }

    public interface ProgressAnimationListener {
        void onProgressUpdate(int value);
        void onFinish();
    }
}
