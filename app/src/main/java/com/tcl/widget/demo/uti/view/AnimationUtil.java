package com.tcl.widget.demo.uti.view;

import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by jerryliu on 2017/4/26.
 */

public class AnimationUtil {
    public static ObjectAnimator createScaleXAnimator(View view,float from, float to){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,View.SCALE_X,from,to);
        return animator;
    }

    public static ObjectAnimator createScaleYAnimator(View view,float from, float to){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,View.SCALE_Y,from,to);
        return animator;
    }

    public static ObjectAnimator createTranslationXAnimator(View view,float from, float to){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,View.TRANSLATION_X,from,to);
        return animator;
    }

    public static ObjectAnimator createTranslationYAnimator(View view,float from, float to){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,View.TRANSLATION_Y,from,to);
        return animator;
    }

    public static ObjectAnimator createAlphaAnimator(View view,float from, float to){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,View.ALPHA,from,to);
        return animator;
    }


    public static ObjectAnimator createRotatationAnimator(View view,float from, float to){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,View.ROTATION,from,to);

        return animator;
    }


}
