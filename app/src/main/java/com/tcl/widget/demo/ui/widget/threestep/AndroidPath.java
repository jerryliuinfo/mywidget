package com.tcl.widget.demo.ui.widget.threestep;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;

/**
 * Created by jerryliu on 2017/4/29.
 */

public class AndroidPath extends View {
    private Path mPath;
    private Paint mPaint;
    public static final int WAVE_ITEM_LENGTH = 1000;
    public AndroidPath(Context context) {
        super(context,null);
        init(context);
    }

    public AndroidPath(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init(context);
    }

    public AndroidPath(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private int originalY;

    private void init(Context context){
        mPath = new Path();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(ResUtil.getColor(R.color.green));
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPaint.setStrokeWidth(ResUtil.dp2px(3));

        originalY = 300;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //mPath.moveTo(100,300);

        /*mPath.quadTo(200,200,300,300);
        mPath.quadTo(400,400,500,300);*/


        /*mPath.rQuadTo(100,-100,200,0);
        mPath.rQuadTo(100,100,200,0);*/

        mPath.reset();

        mPath.moveTo(- WAVE_ITEM_LENGTH + dx, originalY);
        int halfWaveWidth = WAVE_ITEM_LENGTH / 2;
        for (int i = -WAVE_ITEM_LENGTH; i < getMeasuredWidth() + WAVE_ITEM_LENGTH; i+= WAVE_ITEM_LENGTH){
            mPath.rQuadTo(halfWaveWidth / 2, -90,halfWaveWidth,0);
            mPath.rQuadTo(halfWaveWidth / 2,90,halfWaveWidth,0);
        }
        mPath.lineTo(getMeasuredWidth(),getMeasuredHeight());
        mPath.lineTo(0,getMeasuredHeight());
        mPath.close();


        canvas.drawPath(mPath, mPaint);
    }

    private float mPrex,mPreY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            /*case MotionEvent.ACTION_DOWN:
                mPrex = event.getX();
                mPreY = event.getY();
                mPath.moveTo(mPrex,mPreY);
                return true;
            case MotionEvent.ACTION_MOVE:
                float endX = (mPrex + event.getX() ) /2;
                float endY = (mPreY + event.getY()) / 2;
                mPath.quadTo(mPrex,mPreY,endX,endY);
                mPrex = event.getX();
                mPreY = event.getY();
                invalidate();
                break;*/
        }
        return super.onTouchEvent(event);
    }

    public void reset(){
        mPath.reset();
        invalidate();
    }
    private int dx;

    public void startAnim(){
        ValueAnimator animatior = ValueAnimator.ofInt(0, WAVE_ITEM_LENGTH);
        animatior.setRepeatCount(ValueAnimator.INFINITE);
        animatior.setInterpolator(new LinearInterpolator());
        animatior.setDuration(2000);
        animatior.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        animatior.start();
    }


}
