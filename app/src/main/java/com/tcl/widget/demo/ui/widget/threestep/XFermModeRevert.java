package com.tcl.widget.demo.ui.widget.threestep;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;

/**
 * Created by jerryliu on 2017/5/1.
 */

public class XFermModeRevert extends View{
    private Paint mPaint;
    public XFermModeRevert(Context context) {
        super(context,null);
        init(context);
    }

    public XFermModeRevert(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init(context);
    }

    public XFermModeRevert(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mDstBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
        mDstBitmap = Bitmap.createBitmap(mDstBitmap,0,0,mDstBitmap.getWidth() /2, mDstBitmap.getHeight()/2);
        Matrix matrix = new Matrix();
        matrix.setScale(1,-1);
        mRevertBitmap = Bitmap.createBitmap(mDstBitmap,0,0,mDstBitmap.getWidth() ,mDstBitmap.getHeight(),matrix,true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //先画出小狗图片
        canvas.drawBitmap(mDstBitmap,0,0,mPaint);

        //再画出倒影
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);
        canvas.translate(0,mDstBitmap.getHeight());


        canvas.drawBitmap(mRevertBitmap,0,0,mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);


    }




    private Bitmap mDstBitmap;
    private Bitmap mRevertBitmap;



}
