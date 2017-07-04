package com.tcl.widget.demo.ui.widget.github.floatview.effect;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;

import com.tcl.widget.demo.ui.widget.github.floatview.transition.FloatingTransition;
import com.tcl.widget.demo.ui.widget.github.floatview.transition.YumFloating;

/**
 * Created by jerryliu on 2017/7/4.
 */

public class StartFloatingTransition implements FloatingTransition {
    private long mDuration = 3000;
    @Override
    public void applyFloating(final YumFloating yumFloating) {
        ValueAnimator translateAnimator = ValueAnimator.ofFloat(0, -500);
        translateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                yumFloating.setTranslationY((Float) animation.getAnimatedValue());
            }
        });
        translateAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                yumFloating.setAlpha(0);
                yumFloating.clear();
            }
        });

        ValueAnimator rotateAnimator = ValueAnimator.ofFloat(0,360);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setRepeatMode(ValueAnimator.RESTART);
        rotateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                yumFloating.setRotation((Float) animation.getAnimatedValue());
            }
        });

        ValueAnimator scaleAnimator = ValueAnimator.ofFloat(0,1);
        scaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                yumFloating.setScaleX((Float) animation.getAnimatedValue());
                yumFloating.setScaleY((Float) animation.getAnimatedValue());
            }
        });


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(mDuration);
        animatorSet.play(translateAnimator).with(rotateAnimator).with(scaleAnimator);
        animatorSet.start();
    }
}
