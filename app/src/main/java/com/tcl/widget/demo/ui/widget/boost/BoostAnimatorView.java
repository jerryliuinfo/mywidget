package com.tcl.widget.demo.ui.widget.boost;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.SystemUtility;

/**
 * Created by jerryliu on 2017/4/28.
 */

public class BoostAnimatorView extends FrameLayout {
    private BoostView boost_view;
    private IconGatherView icon_gather_view;

    public BoostAnimatorView(@NonNull Context context) {
        super(context);
        init(context);
    }

    public BoostAnimatorView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        View rootView = LayoutInflater.from(context).inflate(R.layout.boostengine_lay_animator_view_v2,this);
        boost_view = (BoostView) rootView.findViewById(R.id.boost_view);
        icon_gather_view = (IconGatherView) rootView.findViewById(R.id.icon_gather_view);
    }

    public void startAnim(){
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator translationAnimator = ObjectAnimator.ofFloat(boost_view,View.TRANSLATION_Y,boost_view.getCenterY()+ SystemUtility.getScreenHeight()/2,0);
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(boost_view,View.ALPHA,0,1);
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(boost_view,View.SCALE_X,0,1);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(boost_view,View.SCALE_Y,0,1);
        animatorSet.setDuration(1000);
        animatorSet.playTogether(translationAnimator,alphaAnimator,scaleXAnimator,scaleYAnimator);
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                boost_view.startAnim();
                icon_gather_view.start();
            }
        });
        animatorSet.start();
    }

    public void stopAnim(){
        boost_view.stopAnim();
    }

    public boolean isAnimRunning(){
       return boost_view.isAnimRunning();
    }



}
