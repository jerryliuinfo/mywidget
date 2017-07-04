package com.tcl.widget.demo.ui.widget.github.floatview.effect;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;

import com.tcl.widget.demo.ui.widget.github.floatview.transition.FloatingTransition;
import com.tcl.widget.demo.ui.widget.github.floatview.transition.YumFloating;

/**
 * Created by jerryliu on 2017/7/4.
 */

public class TranslationFloatingTransition implements FloatingTransition {
    public static final String TAG = TranslationFloatingTransition.class.getSimpleName();
    private long mDuraion = 5000;
    private float mTranslationY = -200;




    public TranslationFloatingTransition() {

    }

    @Override
    public void applyFloating(final YumFloating yumFloating) {
        ValueAnimator animator = ValueAnimator.ofFloat(0,mTranslationY);
        animator.setDuration(mDuraion);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                yumFloating.setTranslationY(value);
                yumFloating.setAlpha((1 - value / mTranslationY));
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                yumFloating.setTranslationY(0);

            }
        });
        animator.start();
    }
}
