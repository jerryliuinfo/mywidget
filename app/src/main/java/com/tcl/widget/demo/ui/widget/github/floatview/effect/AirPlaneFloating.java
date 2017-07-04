package com.tcl.widget.demo.ui.widget.github.floatview.effect;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.graphics.Path;

import com.tcl.widget.demo.MyApplicaiton;
import com.tcl.widget.demo.ui.widget.github.floatview.transition.BaseFloatingPathTraniton;
import com.tcl.widget.demo.ui.widget.github.floatview.transition.FloatingPath;
import com.tcl.widget.demo.ui.widget.github.floatview.transition.PathPostion;
import com.tcl.widget.demo.ui.widget.github.floatview.transition.YumFloating;
import com.tcl.widget.demo.uti.ScreenUtil;

/**
 * Created by jerryliu on 2017/7/4.
 */

public class AirPlaneFloating extends BaseFloatingPathTraniton {
    @Override
    public void applyFloating(final YumFloating yumFloating) {
        ValueAnimator translateAnimator = ValueAnimator.ofFloat(getStartPosition(),getEndPostion());
        translateAnimator.setDuration(5000);
        translateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                PathPostion pathPostion = getFloatingPathPosition(value);

                yumFloating.setTranslationX(pathPostion.x);
                yumFloating.setTranslationY(pathPostion.y);

            }
        });
        translateAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                yumFloating.setTranslationY(0);
                yumFloating.setTranslationY(0);
                yumFloating.setAlpha(0);
                yumFloating.clear();

            }
        });
        translateAnimator.start();
    }

    @Override
    public FloatingPath getFloatingPath() {
        Path path = new Path();
        path.quadTo(100,-300,0,-600);
        path.rLineTo(0, -ScreenUtil.getScreenHeight(MyApplicaiton.getContext()));
        return FloatingPath.create(path,false);
    }
}
