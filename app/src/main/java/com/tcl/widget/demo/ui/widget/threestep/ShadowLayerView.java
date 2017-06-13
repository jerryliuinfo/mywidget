package com.tcl.widget.demo.ui.widget.threestep;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;

/**
 * Created by jerryliu on 2017/6/13.
 */

public class ShadowLayerView extends View {
    private Paint mPaint;
    private Bitmap mDogBitmap;
    private int mRadis;
    private int mDx,mDy;
    private boolean mSetShadow = true;
    public ShadowLayerView(Context context) {
        super(context,null);
        init();
    }

    public ShadowLayerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public ShadowLayerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        //禁用硬件加速
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(ResUtil.getColor(R.color.green));
        mPaint.setTextSize(ResUtil.sp2px(16));

        //mPaint.setShadowLayer(1,10,10,ResUtil.getColor(R.color.gray));

        mDogBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.dog_small);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mSetShadow){
            mPaint.setShadowLayer(mRadis, mDx, mDy, ResUtil.getColor(R.color.gray));
        }else {
            mPaint.clearShadowLayer();
        }

        canvas.drawText("Hello World", 100,100, mPaint);
        canvas.drawCircle(200,200,50,mPaint);
        canvas.drawBitmap(mDogBitmap,null,new RectF(200,300, 200+mDogBitmap.getWidth(),300+mDogBitmap.getHeight()),mPaint);
    }

    public void changeRaidis(){
        mRadis ++;
        postInvalidate();
    }

    public void changeDx(){
        mDx += 5;
        postInvalidate();
    }

    public void changeDy(){
        mDy += 5;
        postInvalidate();
    }

    public void clearShadow() {
        this.mSetShadow = false;
        postInvalidate();
    }
}
