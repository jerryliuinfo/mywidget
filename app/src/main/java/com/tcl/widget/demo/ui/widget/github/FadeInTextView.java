package com.tcl.widget.demo.ui.widget.github;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * Created by jerryliu on 2017/6/28.
 */

public class FadeInTextView extends android.support.v7.widget.AppCompatTextView {
    private int mTxtCount;
    private StringBuffer mStringBuffer = new StringBuffer();
    private String[] array;
    private ValueAnimator mValueAnimator;
    //单个字符显示的时长
    private int mSingleTextDuraiton = 100;


    public FadeInTextView(Context context) {
        super(context);
    }

    public FadeInTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FadeInTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public FadeInTextView setTextString(String text){
        if (!TextUtils.isEmpty(text)){
            mCurrentIndex = -1;
            mStringBuffer.delete(0,mStringBuffer.length());
            mTxtCount = text.length();
            array = new String[mTxtCount];
            for (int i = 0; i < mTxtCount; i++){
                array[i] = text.substring(i,i+1);
            }
            initAnimaiton();
        }
        return this;
    }

    int mCurrentIndex = -1;

    private void initAnimaiton(){
        if (mValueAnimator != null){
            mValueAnimator.cancel();
            mValueAnimator = null;
        }
        mValueAnimator = ValueAnimator.ofInt(0,mTxtCount - 1);
        mValueAnimator.setDuration(mTxtCount * mSingleTextDuraiton);
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int index = (int) animation.getAnimatedValue();
                if (mCurrentIndex != index){
                    mCurrentIndex = index;
                    mStringBuffer.append(array[mCurrentIndex]);
                    setText(mStringBuffer.toString());
                }

            }
        });
    }

    public void startAnimation(){
        if (mValueAnimator != null){
            mStringBuffer.setLength(0);
            mCurrentIndex = -1;
            mValueAnimator.start();
        }
    }
}
