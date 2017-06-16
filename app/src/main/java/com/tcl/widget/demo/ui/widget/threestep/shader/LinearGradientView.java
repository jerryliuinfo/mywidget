package com.tcl.widget.demo.ui.widget.threestep.shader;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.uti.view.MeasureUtil;

/**
 * Created by jerryliu on 2017/6/15.
 */

public class LinearGradientView extends View {
    private Paint mPaint;
    private LinearGradient mLinearGradient;
    public LinearGradientView(Context context) {
        super(context,null);
        init();
    }

    public LinearGradientView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public LinearGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec),MeasureUtil.getMeasuredLength(heightMeasureSpec,200));

    }

    private void init(){
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        mLinearGradient = new LinearGradient(0,getHeight()/2,getWidth(),getHeight()/2,0xffff0000,0xff00ff0, Shader
//                .TileMode.CLAMP);

        int[] colors = {0xffff0000,0xff00ff00,0xff0000ff,0xffffff00,0xff00ffff};
        float[] pos = {0f,0.2f,0.4f,0.6f,1.0f};
        mLinearGradient = new LinearGradient(0,getHeight()/2,getWidth(),getHeight()/2,colors,pos, Shader
                .TileMode.CLAMP);


        mPaint.setShader(mLinearGradient);
        canvas.drawRect(0,0,getWidth(),getHeight(),mPaint);
    }
}
