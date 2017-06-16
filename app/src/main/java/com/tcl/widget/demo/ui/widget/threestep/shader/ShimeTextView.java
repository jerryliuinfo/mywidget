package com.tcl.widget.demo.ui.widget.threestep.shader;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

/**
 * Created by jerryliu on 2017/6/16.
 */

public class ShimeTextView extends android.support.v7.widget.AppCompatTextView {
    private Paint mPaint;
    public ShimeTextView(Context context) {
        super(context,null);
        init();
    }

    public ShimeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public ShimeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = getPaint();
        mMatrix = new Matrix();
    }

    private int mDx;
    private LinearGradient mLinearGradient;
    private Matrix mMatrix;
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        ValueAnimator animator = ValueAnimator.ofInt(0, 2 * getMeasuredWidth());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mDx = (int) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(2000);
        animator.start();

        mLinearGradient = new LinearGradient(-getMeasuredWidth(),0,0,0,new int[]{getCurrentTextColor(),0xff00ff00,getCurrentTextColor()},
                new float[]{0,0.5f,1.0f}, Shader.TileMode.CLAMP);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        setText("Hello world");
        mMatrix.setTranslate(mDx,0);
        mLinearGradient.setLocalMatrix(mMatrix);
        mPaint.setShader(mLinearGradient);
        super.onDraw(canvas);
    }
}
