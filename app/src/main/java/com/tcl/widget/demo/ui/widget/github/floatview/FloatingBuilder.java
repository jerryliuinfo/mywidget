package com.tcl.widget.demo.ui.widget.github.floatview;

import android.view.View;

import com.tcl.widget.demo.ui.widget.github.floatview.effect.TranslationFloatingTransition;
import com.tcl.widget.demo.ui.widget.github.floatview.transition.FloatingTransition;

/**
 * Created by jerryliu on 2017/7/4.
 */

public class FloatingBuilder {
    private FloatingElement mFloatingElement;

    public FloatingBuilder() {
        this.mFloatingElement = new FloatingElement();
        this.mFloatingElement.mTargetViewLayResId = -1;
    }

    public FloatingBuilder floatTransiton(FloatingTransition floatingTransition){
        mFloatingElement.mFloatingTransition =  floatingTransition;
        return this;
    }

    public FloatingBuilder offsetX(int offsetX){
        mFloatingElement.mOffsetX = offsetX;
        return this;
    }

    public FloatingBuilder offsetY(int offsetY){
        mFloatingElement.mOffsetY = offsetY;
        return this;
    }

    public FloatingBuilder anchorView(View anchorView){
        mFloatingElement.mAnchorView = anchorView;
        return this;
    }

    public FloatingBuilder targetView(View targetView){
        mFloatingElement.mTargetView = targetView;
        return this;
    }

    public FloatingBuilder targetView(int targetViewLayResId){
        mFloatingElement.mTargetViewLayResId = targetViewLayResId;
        return this;
    }


    public FloatingElement build(){
        if (mFloatingElement.mAnchorView == null){
            throw new NullPointerException("AnchorView should not be null");
        }
        if (mFloatingElement.mTargetView == null && mFloatingElement.mTargetViewLayResId == -1){
            throw new NullPointerException("TargetView should not be null");
        }
        if (mFloatingElement.mFloatingTransition == null){
            mFloatingElement.mFloatingTransition = new TranslationFloatingTransition();
        }
        return mFloatingElement;
    }
}
