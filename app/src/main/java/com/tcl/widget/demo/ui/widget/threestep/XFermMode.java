package com.tcl.widget.demo.ui.widget.threestep;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by jerryliu on 2017/5/1.
 */

public class XFermMode extends View{
    private Paint mPaint;
    private Bitmap mBitmap;
    public XFermMode(Context context) {
        super(context,null);
        init(context);
    }

    public XFermMode(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init(context);
    }

    public XFermMode(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDstPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mDstPaint.setStyle(Paint.Style.FILL);
        mDstPaint.setColor(Color.parseColor("#FFFFCC44"));


        mSrcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSrcPaint.setStyle(Paint.Style.FILL);
        mSrcPaint.setColor(Color.parseColor("#FF66AAFF"));

        mDstBitmap = makeDstBitmap(mWidth,mHeight);
        mSrcBitmap = makeSrcBitmap(mWidth,mHeight);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        int layerID = canvas.saveLayer(0, 0, mWidth*2, mHeight*2,mPaint,Canvas.ALL_SAVE_FLAG);
        canvas.drawBitmap(mDstBitmap,0,0,mPaint);



        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));

        canvas.drawBitmap(mSrcBitmap,mWidth / 2, mHeight / 2, mPaint);

        mPaint.setXfermode(null);

        canvas.restoreToCount(layerID);


    }


    private int mWidth = 400;
    private int mHeight = 400;
    private Paint mDstPaint;
    private Paint mSrcPaint;

    private Bitmap mDstBitmap;
    private Bitmap mSrcBitmap;

    private Bitmap makeDstBitmap(int width, int height){
        Bitmap bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawCircle(mWidth / 2, mHeight / 2,mWidth / 2, mDstPaint);
        return bitmap;
    }

    private Bitmap makeSrcBitmap(int width, int height){
        Bitmap bitmap = Bitmap.createBitmap(width,height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawRect(new RectF(0,0,width,height), mSrcPaint);
        return bitmap;
    }

}
