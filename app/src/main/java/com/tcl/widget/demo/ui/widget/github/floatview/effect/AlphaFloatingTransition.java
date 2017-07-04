package com.tcl.widget.demo.ui.widget.github.floatview.effect;

import android.animation.ValueAnimator;

import com.tcl.widget.demo.ui.widget.github.floatview.transition.FloatingTransition;
import com.tcl.widget.demo.ui.widget.github.floatview.transition.YumFloating;

/**
 * Created by jerryliu on 2017/7/4.
 */

public class AlphaFloatingTransition implements FloatingTransition {
    @Override
    public void applyFloating(final YumFloating yumFloating) {
        ValueAnimator animator = ValueAnimator.ofFloat(1,0);
        animator.setDuration(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                yumFloating.setAlpha((Float) animation.getAnimatedValue());
            }
        });
        animator.start();
    }
}
