package com.tcl.widget.demo.uti.view;

import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by jerryliu on 2017/4/26.
 */

public class AnimationUtil {
    public static ObjectAnimator createScaleXAnimator(View view,float from, float to){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"scaleX",from,to);
        return animator;
    }

    public static ObjectAnimator createScaleYAnimator(View view,float from, float to){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"scaleY",from,to);
        return animator;
    }

    public static ObjectAnimator createTranslationXAnimator(View view,float from, float to){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"translationX",from,to);
        return animator;
    }

    public static ObjectAnimator createTranslationYAnimator(View view,float from, float to){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"translationY",from,to);
        return animator;
    }

    public static ObjectAnimator createAlphaAnimator(View view,float from, float to){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"alpha",from,to);
        return animator;
    }


    public static ObjectAnimator createRotatationAnimator(View view,float from, float to){
        ObjectAnimator animator = ObjectAnimator.ofFloat(view,"rotation",from,to);

        return animator;
    }


}
