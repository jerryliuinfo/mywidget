package com.tcl.widget.demo.ui.widget.github;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;
import com.tcl.widget.demo.uti.view.MeasureUtil;
import com.tcl.widget.demo.uti.view.PaintConfigUtil;

/**
 * Created by jerryliu on 2017/6/26.
 */

public class SubmitButtonView extends View {
    private TextPaint mTextPaint;
    private Paint mHookPaint;

    private Paint mRectCirclePaint;

    private RectF mRectF;
    //由矩形编程圆形的过程中 mRectF的left移动的最大距离
    private int mMaxRectToCircleMoveDistance;
    //当前mRectF的left移动的距离
    private int mCurrentMoveDistance;
    private int mAnimDuraiton = 1000;

    /**
     * 由矩形变成圆形的过程中圆角变换的动画
     */
    private ValueAnimator mAngleAnimator;
    private int mCurrentRouncAngel;

    /**
     * 由矩形变成圆形的动画
     */
    private ValueAnimator mRectToCircleAnmatior;

    /**
     * View向上的移动的动画
     */
    private ValueAnimator mMoveUpAnimator;
    /**
     * view向上移动距离
     */
    private int mMoveDistance = 300;

    private AnimatorSet mAnimatorSet;

    private Path mOkPath = new Path();
    private PathMeasure mPathMeasure;
    private PathEffect mPahtEffect;
    private Paint mOkPaint;
    private ValueAnimator mOkAnimator;
    private boolean mStartDrawOk;



    public SubmitButtonView(Context context) {
        super(context,null);
        init();
    }

    public SubmitButtonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public SubmitButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(ResUtil.getColor(R.color.white));
        mTextPaint.setTextSize(ResUtil.sp2px(12));
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mHookPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        PaintConfigUtil.configNormal(mHookPaint,ResUtil.getColor(R.color.white),ResUtil.dip2px(1), Paint.Style.STROKE);

        mRectCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        PaintConfigUtil.configNormal(mRectCirclePaint,ResUtil.getColor(R.color.holo_orange_dark),ResUtil.dip2px(1), Paint.Style.FILL);

        mOkPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        PaintConfigUtil.configNormal(mOkPaint,ResUtil.getColor(R.color.white),ResUtil.dip2px(2), Paint.Style.STROKE);

        mPathMeasure = new PathMeasure();
        mRectF = new RectF();



    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //可以移动的最大距离为 (长度 - 宽度) / 2
        mMaxRectToCircleMoveDistance = (getMeasuredWidth() - getMeasuredHeight()) / 2;
        initOk();
        initAnimation();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRectToCircle(canvas);
        drawText(canvas);
        if (mStartDrawOk){
            canvas.drawPath(mOkPath, mOkPaint);
        }
    }


    private void initAnimation(){
        initRectToCircleAnimation();
        initRectToAngleAnimation();
        initMoveUpAnimation();
        initOkAnimtaion();

        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.setDuration(mAnimDuraiton);
        mAnimatorSet.playTogether(mRectToCircleAnmatior,mAngleAnimator);
        mAnimatorSet.playSequentially(mAngleAnimator,mMoveUpAnimator,mOkAnimator );
    }

    public void start(){
        mAnimatorSet.start();
    }

    private void initOkAnimtaion(){
        mOkAnimator = ValueAnimator.ofFloat(1,0);
        mOkAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mStartDrawOk = true;
                float value = (float) animation.getAnimatedValue();
                float phase = value * mPathMeasure.getLength();
                mPahtEffect = new DashPathEffect(new float[]{mPathMeasure.getLength(),mPathMeasure.getLength()}, phase);
                mOkPaint.setPathEffect(mPahtEffect);
                invalidate();
            }

        });
    }

    private void initOk(){
        mOkPath.moveTo(mMaxRectToCircleMoveDistance + getMeasuredHeight() / 8 * 3, getMeasuredHeight() / 2);
        mOkPath.lineTo(mMaxRectToCircleMoveDistance + getMeasuredHeight() / 2, getMeasuredHeight() / 5 * 3);
        mOkPath.lineTo(mMaxRectToCircleMoveDistance + getMeasuredHeight() / 3 * 2, getMeasuredHeight() / 5 * 2);

        mPathMeasure = new PathMeasure(mOkPath, true);
    }


    private void initMoveUpAnimation(){
        float currentY = getTranslationY();
        mMoveUpAnimator = ObjectAnimator.ofFloat(this,"translationY", currentY, currentY - mMoveDistance);
    }

    private void initRectToCircleAnimation(){
        mRectToCircleAnmatior = ValueAnimator.ofInt(0,mMaxRectToCircleMoveDistance);
        mRectToCircleAnmatior.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentMoveDistance = (int) animation.getAnimatedValue();
                int mAlpha = 255 - mCurrentMoveDistance * 255 / mMaxRectToCircleMoveDistance;
                mTextPaint.setAlpha(mAlpha);
                invalidate();
            }
        });
    }


    private void initRectToAngleAnimation(){
        mAngleAnimator = ValueAnimator.ofInt(0, getMeasuredHeight() / 2);
        mAngleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentRouncAngel = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
    }

    /**
     * 由举行编程圆
     * @param canvas
     */
    private void drawRectToCircle(Canvas canvas){
        mRectF.left = mCurrentMoveDistance;
        mRectF.top = 0;
        mRectF.right = getMeasuredWidth() - mCurrentMoveDistance;
        mRectF.bottom = getMeasuredHeight();
        canvas.drawRoundRect(mRectF,mCurrentRouncAngel,mCurrentRouncAngel, mRectCirclePaint);
    }

    private void drawText(Canvas canvas){
        canvas.drawText("确认完成",getMeasuredWidth()/2, getMeasuredHeight() / 2 - MeasureUtil.getTextBaseY(mTextPaint), mTextPaint);
    }
}
