package com.tcl.widget.demo.ui.widget.github;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
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
    private Paint mCircleProgressPaint;
    private TextPaint mTextPaint;
    private float mValue = 50;

    private float minValue = 0;
    private float maxValue = 100;

    private float mRadis;

    //圆心坐标
    private Point mCenterPoint;

    private float mPercent;//当前进度百分比

    private RectF mRectF;
    //起始角度
    private float mStartAngel = 135;
    private int maxWeepAngel = 270;

    //横向进度条整个控件的高度
    private int mHorizontalProgressbarHeight = 100;


    private ProgressAnimationListener mListener;


    private Paint mHorizontalProgressBgPaint;
    private Paint mHorizontalProgressPaint;
    private Paint mTipRoundPaint;
    private Paint mTipTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //百分比提示框的高度
    private int mTipHeight = ResUtil.dp2px(15);
    private int mTipeWidth = ResUtil.dp2px(30);
    //三角形的高
    private int mTriangleHeight = ResUtil.dp2px(3);
    private int mHorizontalWidth;

    private RectF mProgressBgRect;
    private RectF mProgressRect;

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
        PaintConfigUtil.configRound(mCircleBgPaint);
        PaintConfigUtil.configNormal(mCircleBgPaint, ResUtil.getColor(R.color.gray),ResUtil.dp2px(2), Paint.Style.STROKE);

        mCircleProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        PaintConfigUtil.configRound(mCircleProgressPaint);
        PaintConfigUtil.configNormal(mCircleProgressPaint, ResUtil.getColor(R.color.green),ResUtil.dp2px(2), Paint.Style.STROKE);

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        PaintConfigUtil.configText(mTextPaint,ResUtil.getColor(R.color.red),ResUtil.sp2px(14));

        mHorizontalProgressBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        PaintConfigUtil.configRound(mHorizontalProgressBgPaint);
        PaintConfigUtil.configFillPaint(mHorizontalProgressBgPaint, ResUtil.getColor(R.color.horizontal_progress_bg));

        mHorizontalProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        PaintConfigUtil.configRound(mHorizontalProgressPaint);
        PaintConfigUtil.configFillPaint(mHorizontalProgressPaint, ResUtil.getColor(R.color.horizontal_progressr));

        mTipRoundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        PaintConfigUtil.configRound(mTipRoundPaint);
        PaintConfigUtil.configFillPaint(mTipRoundPaint, ResUtil.getColor(R.color.horizontal_progressr));

        mTipTextPaint.setTextAlign(Paint.Align.CENTER);
        PaintConfigUtil.configText(mTipTextPaint,ResUtil.getColor(R.color.white),ResUtil.sp2px(10));


        mRectF = new RectF();
        mProgressBgRect = new RectF();
        mProgressRect = new RectF();

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
                mCurrentHorizontalProgress = mPercent * mHorizontalWidth;
                mMoveDes = mPercent *mHorizontalWidth;
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

        int minSize = (int) Math.min((getMeasuredWidth() - getPaddingLeft() - getPaddingRight() - 2 * mCircleBgPaint.getStrokeWidth()),(getMeasuredHeight() - getPaddingTop() - getPaddingBottom() - 2 * mCircleBgPaint.getStrokeWidth() - mHorizontalProgressbarHeight));
        mRadis = minSize / 2;
        mCenterPoint.set(w/2,h/2 - mHorizontalProgressbarHeight / 2);
        mRectF.left = mCenterPoint.x - mRadis - mCircleBgPaint.getStrokeWidth() / 2;
        mRectF.right = mCenterPoint.x + mRadis + mCircleBgPaint.getStrokeWidth() /2;
        mRectF.top = mCenterPoint.y - mRadis - mCircleBgPaint.getStrokeWidth() / 2;
        mRectF.bottom = mCenterPoint.y + mRadis + mCircleBgPaint.getStrokeWidth() / 2;

        mHorizontalWidth = getMeasuredWidth() - 2 * marginLeft;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawHorizontalProgressView(canvas);
        drawText(canvas);
        drawArc(canvas);




    }

    private void drawArc(Canvas canvas){
        float sweepAngel = maxWeepAngel * mPercent;
        canvas.rotate(mStartAngel,mCenterPoint.x,mCenterPoint.y);
        //画背景
        canvas.drawArc(mRectF, sweepAngel, maxWeepAngel - sweepAngel,false,mCircleBgPaint);

        //画进度
        canvas.drawArc(mRectF,2,sweepAngel,false, mCircleProgressPaint);

        canvas.restore();
    }


    //横向进度条距离左边的距离
    private int marginLeft = ResUtil.dp2px(8);
    private float mCurrentHorizontalProgress;
    private void drawHorizontalProgressView(Canvas canvas){
        drawHorizontalProgress(canvas);
        drawTip(canvas);
    }


    private void drawHorizontalProgress(Canvas canvas){
        //进度条的高度
        int horizontalBarStrokeSize = ResUtil.dp2px(4);
        int horizontalProgressY = getMeasuredHeight() - getPaddingBottom() /2 - horizontalBarStrokeSize ;
        //画背景
        mProgressBgRect.set(marginLeft,horizontalProgressY, marginLeft + mHorizontalWidth,horizontalProgressY + horizontalBarStrokeSize);
        canvas.drawRoundRect(mProgressBgRect,ResUtil.dp2px(2),ResUtil.dp2px(2), mHorizontalProgressBgPaint);
        //画进度
        mProgressRect.set(marginLeft,horizontalProgressY, marginLeft + mCurrentHorizontalProgress,horizontalProgressY + horizontalBarStrokeSize);
        canvas.drawRoundRect(mProgressRect,ResUtil.dp2px(2),ResUtil.dp2px(2),mHorizontalProgressPaint);
    }

    private RectF mRoundRect = new RectF();
    private Path mTrianglePath = new Path();

    private float mMoveDes;
    private void drawTip(Canvas canvas){
        //矩形距离横向进度条的距离
        float gap = ResUtil.dp2px(3);
        float y = mProgressBgRect.top - gap - mTipHeight - mTriangleHeight;
        canvas.save();
        canvas.translate(-marginLeft,0);
        mRoundRect.set(mMoveDes,y,mMoveDes + mTipeWidth, y + mTipHeight);
        canvas.drawRoundRect(mRoundRect,ResUtil.dp2px(2),ResUtil.dp2px(2), mTipRoundPaint);

        //画三角
        mTrianglePath.reset();
        mTrianglePath.moveTo(mMoveDes + mTipeWidth / 2 - mTriangleHeight, mRoundRect.bottom);
        mTrianglePath.lineTo(mMoveDes + mTipeWidth / 2, mRoundRect.bottom + mTriangleHeight);
        mTrianglePath.lineTo(mMoveDes + mTipeWidth / 2 + mTriangleHeight,mRoundRect.bottom);
        canvas.drawPath(mTrianglePath, mTipRoundPaint);


        //画文字
        canvas.drawText(MathUtil.round(mValue) + "%", mRoundRect.centerX(), mRoundRect.centerY() - MeasureUtil.getTextBaseY(mTipTextPaint),mTipTextPaint);
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
