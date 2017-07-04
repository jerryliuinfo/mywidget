package com.tcl.widget.demo.ui.widget.github.floatview.effect;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Path;

import com.tcl.widget.demo.ui.widget.github.floatview.transition.BaseFloatingPathTraniton;
import com.tcl.widget.demo.ui.widget.github.floatview.transition.FloatingPath;
import com.tcl.widget.demo.ui.widget.github.floatview.transition.PathPostion;
import com.tcl.widget.demo.ui.widget.github.floatview.transition.YumFloating;

/**
 * Created by jerryliu on 2017/7/4.
 */

public class BearFloating extends BaseFloatingPathTraniton {
    private int mDuraiton = 5000;
    @Override
    public void applyFloating(final YumFloating yumFloating) {
        ValueAnimator translateAnimator = ValueAnimator.ofFloat(0,500);
        translateAnimator.setDuration(mDuraiton);
        translateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                PathPostion pathPostion = getFloatingPathPosition(value);
                yumFloating.setTranslationX(pathPostion.x);
                yumFloating.setTranslationY(pathPostion.y);
            }
        });


        ValueAnimator alphaAnimator = ValueAnimator.ofFloat(1,0);
        alphaAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                yumFloating.setAlpha((Float) animation.getAnimatedValue());
            }
        });
        alphaAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                yumFloating.clear();
            }
        });
        alphaAnimator.setStartDelay(mDuraiton / 2);
        alphaAnimator.setDuration(mDuraiton / 2);
        alphaAnimator.start();


        translateAnimator.start();
    }

    @Override
    public FloatingPath getFloatingPath() {
        Path path = new Path();
        path.rLineTo(-100,0);
        path.quadTo(0,-200,100,0);
        path.quadTo(0,200,-100,0);
        FloatingPath floatingPath = FloatingPath.create(path,false);
        return floatingPath;
    }
}
