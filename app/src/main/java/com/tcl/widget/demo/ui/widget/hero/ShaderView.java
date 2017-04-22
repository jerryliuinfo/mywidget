package com.tcl.widget.demo.ui.widget.hero;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.util.AttributeSet;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;

/**
 * Created by jerryliu on 2017/4/22.
 */

public class ShaderView extends android.support.v7.widget.AppCompatTextView {
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    public ShaderView(Context context) {
        super(context,null);
        init();
    }

    public ShaderView(Context context, AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public ShaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){

    }

    int mViewWidth;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (mViewWidth == 0){
            mViewWidth = getMeasuredWidth();
            if (mViewWidth > 0){
                mLinearGradient = new LinearGradient(0,0,mViewWidth,0,
                        new int[]{ResUtil.getColor(R.color.blue),ResUtil.getColor(R.color.white),ResUtil.getColor(R.color.blue)
                }, null, Shader.TileMode.CLAMP);
                getPaint().setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    private float mTranslate;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mGradientMatrix != null){
            mTranslate += mViewWidth / 10;
            if (mTranslate > mViewWidth){
                mTranslate -= mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate,0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(500);
        }

    }
}
