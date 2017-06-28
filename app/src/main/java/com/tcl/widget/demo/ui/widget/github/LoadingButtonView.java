package com.tcl.widget.demo.ui.widget.github;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;
import com.tcl.widget.demo.uti.view.MeasureUtil;
import com.tcl.widget.demo.uti.view.PaintConfigUtil;

/**
 * Created by jerryliu on 2017/6/28.
 */

public class LoadingButtonView extends android.support.v7.widget.AppCompatButton {
    public static final String TAG = LoadingButtonView.class.getSimpleName();
    private boolean isLoading;

    private int mDuration = 3000;
    private ValueAnimator mCircle1Animator;
    private ValueAnimator mCircle2Animator;
    private ValueAnimator mCircle3Animator;
    private AnimatorSet mAnimatorSet;

    private Paint mCircle1Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mCircle2Paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mCircle3Paint = new Paint(Paint.ANTI_ALIAS_FLAG);




    public LoadingButtonView(Context context) {
        super(context,null);
        init();
    }

    public LoadingButtonView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public LoadingButtonView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        PaintConfigUtil.configFillPaint(mCircle1Paint, ResUtil.getColor(R.color.white));
        mCircle1Paint.setAlpha(255);


        PaintConfigUtil.configFillPaint(mCircle2Paint, ResUtil.getColor(R.color.white));
        mCircle2Paint.setAlpha(165);

        PaintConfigUtil.configFillPaint(mCircle3Paint, ResUtil.getColor(R.color.white));
        mCircle3Paint.setAlpha(76);


        initAnimator1();
        initAnimator2();
        initAnimator3();

        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.setDuration(mDuration);
        mAnimatorSet.play(mCircle1Animator).with(mCircle2Animator).with(mCircle3Animator);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isLoading){
            drawLoading(canvas);
        }else {
            super.onDraw(canvas);
        }
    }

    int mCenterX;
    int mCenterY;
    int mRadis;

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureUtil.getMeasuredLength(widthMeasureSpec,ResUtil.dp2px(200)),MeasureUtil.getMeasuredLength(heightMeasureSpec,ResUtil.dp2px(45)));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mCenterX = getMeasuredWidth() / 2;
        mCenterY = getMeasuredHeight() / 2;
        mRadis = getMeasuredHeight() / 8;

    }

    private void drawLoading(Canvas canvas){
        canvas.drawCircle(mCenterX - mRadis * 4,mCenterY,mRadis,mCircle1Paint);
        canvas.drawCircle(mCenterX,mCenterY,mRadis,mCircle2Paint);
        canvas.drawCircle(mCenterX + mRadis * 4,mCenterY,mRadis,mCircle3Paint);
    }


    private void initAnimator1(){
        mCircle1Animator = ValueAnimator.ofInt(255,76,165);
        mCircle1Animator.setRepeatCount(ValueAnimator.INFINITE);
        mCircle1Animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int alpha = (int) animation.getAnimatedValue();
                mCircle1Paint.setAlpha(alpha);
                invalidate();
            }
        });
    }

    private void initAnimator2(){
        mCircle2Animator = ValueAnimator.ofInt(165,255,76);
        mCircle2Animator.setRepeatCount(ValueAnimator.INFINITE);
        mCircle2Animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int alpha = (int) animation.getAnimatedValue();
                mCircle2Paint.setAlpha(alpha);
                invalidate();
            }
        });
    }

    private void initAnimator3(){
        mCircle3Animator = ValueAnimator.ofInt(76,165,255);
        mCircle3Animator.setRepeatCount(ValueAnimator.INFINITE);
        mCircle3Animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int alpha = (int) animation.getAnimatedValue();
                mCircle3Paint.setAlpha(alpha);
                invalidate();
            }
        });
    }


    public void startLoading(){
        if (mAnimatorSet != null){
            isLoading = true;
            mAnimatorSet.start();
        }
    }

    public boolean isLoading(){
        return isLoading;
    }


    public void stopLoading(){
        if (mAnimatorSet != null){
            isLoading = false;
            mAnimatorSet.end();
            postInvalidate();
        }
    }
}
