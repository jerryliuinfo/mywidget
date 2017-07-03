package com.tcl.widget.demo.ui.widget.github;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;
import com.tcl.widget.demo.uti.view.MeasureUtil;
import com.tcl.widget.demo.uti.view.PaintConfigUtil;

/**
 * Created by jerryliu on 2017/6/29.
 * 雷达水波纹
 */

public class RadarWaveView extends View {
    public static final String TAG = RadarWaveView.class.getSimpleName();
    private Paint mCircleBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mCircleWavePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private ValueAnimator[] mValueAnimators;
    private AnimatorSet mAnimatorSet;


    private ValueAnimator circleAnimator;

    private int mDuration = 3000;

    private float mCircleBgRadis;
    private float mCircleRadis;

    private float mDefaultStartRadis; //从半径为多少开始画波纹
    /**
     * 扩散水波的颜色
     */
    private int mCircleEdgeColor_green = 0x2fffd1;
    private int mCircleEdgeColor_red = 0xFF4081;
    private int mCircleEdgeColor_yellow = 0xfeff38;

    private int[] colors = {mCircleEdgeColor_green, mCircleEdgeColor_yellow, mCircleEdgeColor_red};

    public RadarWaveView(Context context) {
        super(context,null);
        init();
    }

    public RadarWaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public RadarWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        PaintConfigUtil.configStrokePaint(mCircleBgPaint, ResUtil.getColor(R.color.black),ResUtil.dp2px(2));
        PaintConfigUtil.configStrokePaint(mCircleWavePaint, colors[0],ResUtil.dp2px(2));
        initAnimator();
    }

    private void initAnimator(){
        mValueAnimators = new ValueAnimator[colors.length];
        for (int i = 0; i < mValueAnimators.length; i++){
            initAnimators(i);
        }

        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mAnimatorSet.start();
            }
        });
        mAnimatorSet.playSequentially(mValueAnimators[0],mValueAnimators[1],mValueAnimators[2]);
        mAnimatorSet.play(circleAnimator);
        mAnimatorSet.start();
    }



    private void initAnimators(final int position){
        mValueAnimators[position] = ValueAnimator.ofFloat(0,1);
        mValueAnimators[position].setDuration(mDuration);
        mValueAnimators[position].addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                float value = (float) animation.getAnimatedValue();
                mCircleRadis = (mCircleBgRadis - mDefaultStartRadis) * value;
                mCircleWavePaint.setColor(colors[position]);
                if (value > 0.9) {
                    mCircleWavePaint.setAlpha((int) (((1 - value) + 0.4) * 100));
                } else {
                    mCircleWavePaint.setAlpha((int) ((value + 0.2) * 100));
                }
                mCircleWavePaint.setStrokeWidth(2 + 3 * value);

                invalidate();
            }
        });

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureUtil.getMeasuredLength(widthMeasureSpec,ResUtil.dp2px(200));
        setMeasuredDimension(width,width);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int minSize = (int) Math.min((w - getPaddingLeft()- getPaddingRight() - 2 * mCircleBgPaint.getStrokeWidth()),
                h - getPaddingTop() - getPaddingBottom() - 2 * mCircleBgPaint.getStrokeWidth());
        mCircleBgRadis = minSize / 2;


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircleBg(canvas);
        drawCircle(canvas);
    }

    private void drawCircleBg(Canvas canvas){
        canvas.drawCircle(getMeasuredWidth()/2, getMeasuredHeight()/2, mCircleBgRadis,mCircleBgPaint);
    }

    private void drawCircle(Canvas canvas){
        canvas.drawCircle(getMeasuredWidth()/2, getMeasuredHeight()/2, mCircleRadis, mCircleWavePaint);
    }


    public void start(){
        //mAnimatorSet.start();
    }

    public void stop(){
        //mAnimatorSet.end();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mAnimatorSet.cancel();
    }
}

