package com.tcl.widget.demo.ui.widget.github.floatview;

import android.view.View;

import java.lang.ref.WeakReference;

/**
 * Created by jerryliu on 2017/7/4.
 */

public class YumFloating implements ITransition {
    private WeakReference<View> mTargetViewRefrence;


    public YumFloating(View view) {
        this.mTargetViewRefrence = new WeakReference<View>(view);
    }


    public View getTargetView(){
        return mTargetViewRefrence.get();
    }

    @Override
    public void setAlpha(float alpha) {
        View targetView;
        if ((targetView = getTargetView()) != null){
            targetView.setAlpha(alpha);
        }
    }

    @Override
    public void setRotation(float rotation) {
        View targetView;
        if ((targetView = getTargetView()) != null){
            targetView.setRotation(rotation);
        }
    }

    @Override
    public void setRotationX(float rotationX) {
        View targetView;
        if ((targetView = getTargetView()) != null){
            targetView.setRotationX(rotationX);
        }
    }

    @Override
    public void setRotationY(float rotationY) {
        View targetView;
        if ((targetView = getTargetView()) != null){
            targetView.setRotationY(rotationY);
        }
    }

    @Override
    public void setScaleX(float scaleX) {
        View targetView;
        if ((targetView = getTargetView()) != null){
            targetView.setScaleX(scaleX);
        }
    }

    @Override
    public void setScaleY(float scaleY) {
        View targetView;
        if ((targetView = getTargetView()) != null){
            targetView.setScaleY(scaleY);
        }
    }

    @Override
    public void setScrollX(int scrollX) {
        View targetView;
        if ((targetView = getTargetView()) != null){
            targetView.setScaleX(scrollX);
        }
    }

    @Override
    public void setScrollY(int scrollY) {
        View targetView;
        if ((targetView = getTargetView()) != null){
            targetView.setScaleY(scrollY);
        }

    }

    @Override
    public void setTranslationX(float translationX) {
        View targetView;
        if ((targetView = getTargetView()) != null){
            targetView.setTranslationX(translationX);
        }
    }

    @Override
    public void setTranslationY(float translationY) {
        View targetView;
        if ((targetView = getTargetView()) != null){
            targetView.setTranslationY(translationY);
        }
    }

    @Override
    public void setX(float x) {
        View targetView;
        if ((targetView = getTargetView()) != null){
            targetView.setX(x);
        }
    }

    @Override
    public void setY(float y) {
        View targetView;
        if ((targetView = getTargetView()) != null){
            targetView.setY(y);
        }
    }
}
