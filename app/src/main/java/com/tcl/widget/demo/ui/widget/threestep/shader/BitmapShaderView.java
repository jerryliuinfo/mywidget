package com.tcl.widget.demo.ui.widget.threestep.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;

/**
 * Created by jerryliu on 2017/6/13.
 */

public class BitmapShaderView extends View {
    private Paint mPaint;
    private Bitmap mBitmap;
    public BitmapShaderView(Context context) {
        super(context,null);
        init();
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public BitmapShaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog_edge);
        mPaint.setShader(new BitmapShader(mBitmap, Shader.TileMode.MIRROR, Shader.TileMode.CLAMP));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //canvas.drawRect(0,0,getWidth(),getHeight(), mPaint);
        canvas.drawRect(100,20,200,200, mPaint);
        //canvas.drawRect(100,20,100+mBitmap.getWidth(),20+mBitmap.getHeight(), mPaint);
    }
}
