package com.tcl.widget.demo.ui.widget.threestep;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;
import com.tcl.widget.demo.uti.view.ViewUtil;

/**
 * Created by jerryliu on 2017/5/1.
 */

public class DogViewSrcOut extends View{
    private Paint mPaint;
    private Path mPath;
    private Paint mTextPaint;
    public DogViewSrcOut(Context context) {
        super(context,null);
        init(context);
    }

    public DogViewSrcOut(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init(context);
    }

    public DogViewSrcOut(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(60);
        mPaint.setColor(ResUtil.getColor(R.color.red));

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setStrokeWidth(ResUtil.sp2px(16));
        mTextPaint.setColor(ResUtil.getColor(R.color.red));
        mTextPaint.setTextAlign(Paint.Align.CENTER);


        mPath = new Path();


        mSrcBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dog);
        mDstBitmap = Bitmap.createBitmap(mSrcBitmap.getWidth(),mSrcBitmap.getHeight(), Bitmap.Config.ARGB_8888);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawText("别傻了", getMeasuredWidth() / 2, getMeasuredHeight() / 2 - ViewUtil.getBaseY(mTextPaint),mTextPaint);
        int layerId = canvas.saveLayer(0, 0, getWidth(), getHeight(), null, Canvas.ALL_SAVE_FLAG);



        Canvas c = new Canvas(mDstBitmap);
        c.drawPath(mPath, mPaint);

        canvas.drawBitmap(mDstBitmap,0,0,mPaint);



        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));
        canvas.drawBitmap(mSrcBitmap,0,0,mPaint);

        mPaint.setXfermode(null);

        canvas.restoreToCount(layerId);



    }



    private Bitmap mDstBitmap;
    private Bitmap mSrcBitmap;

    private float preX;
    private float preY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                preX = event.getX();
                preY = event.getY();
                mPath.moveTo(event.getX(),event.getY());
                return true;

            case MotionEvent.ACTION_MOVE:
                float endX = (preX + event.getX()) / 2;
                float endY = (preY + event.getY()) / 2;

                mPath.quadTo(preX,preY,endX,endY);

                preX = event.getX();
                preY = event.getY();
                break;
            case MotionEvent.ACTION_UP:
                break;

        }
        postInvalidate();
        return super.onTouchEvent(event);
    }
}
