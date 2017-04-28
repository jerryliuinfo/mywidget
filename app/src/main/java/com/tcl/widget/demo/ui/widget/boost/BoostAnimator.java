package com.tcl.widget.demo.ui.widget.boost;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by jerryliu on 2017/4/28.
 */

public abstract class BoostAnimator {
    private int mCenterX;
    private int mCenterY;
    private int mOriginX;
    private int mOriginY;
    private float mScaleAlphaFraction;
    private float mTransitionFraction;



    private volatile boolean isAnimStoped=false;
    protected BoostAnimator(){
        ValueAnimator scaleAndAlphaAnimator =ValueAnimator.ofFloat(0,1);
        scaleAndAlphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mScaleAlphaFraction =animation.getAnimatedFraction();
            }
        });
        scaleAndAlphaAnimator.setInterpolator(new FirstBitLatorSmallInterceptor(0.1f));


        ValueAnimator transitionAnimator =ValueAnimator.ofFloat(0,1);
        transitionAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mTransitionFraction =animation.getAnimatedFraction();
            }
        });

        set=new AnimatorSet();
        set.setDuration(1000);
        set.playTogether(scaleAndAlphaAnimator,transitionAnimator);
        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isAnimStoped=true;
            }
        });
    }
    AnimatorSet set;
    public boolean isAnimStoped(){
        return isAnimStoped;
    }

    public void reset(int centerX,int centerY,int originX,int originY){
        mCenterX=centerX;
        mCenterY=centerY;
        mOriginX=originX;
        mOriginY=originY;
        set.setStartDelay(0);
        isAnimStoped=false;

    }

    public void start(){
        set.start();
    }

    public void onDraw(Canvas canvas, Paint paint){
        float x=mOriginX+(mCenterX-mOriginX)* mTransitionFraction;
        float y=mOriginY+(mCenterY-mOriginY)* mTransitionFraction;
        onDraw(canvas,paint,x,y, mScaleAlphaFraction);
    }

    protected abstract void onDraw(Canvas canvas, Paint paint, float x, float y, float fraction);


}
