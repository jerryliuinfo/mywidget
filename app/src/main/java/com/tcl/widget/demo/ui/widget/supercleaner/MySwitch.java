package com.tcl.widget.demo.ui.widget.supercleaner;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;

/**
 * Created by jerryliu on 2017/5/27.
 */

public class MySwitch extends View {
    private Paint mBgPaint;
    private Paint mCirclePaint;

    private RectF mBgRect;
    private RectF mCircleRect;

    private int mRadis;
    private static final int GAP_SIZE = 6;


    private int mMaxCircleLeft;
    private int mMinCircleLeft;

    private int mThemeColor;
    private int mBgAlpha;


    public MySwitch(Context context) {
        super(context,null);
        init();
    }

    public MySwitch(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public MySwitch(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBgPaint.setStyle(Paint.Style.FILL);
        mBgPaint.setColor(ResUtil.getColor(R.color.gray));

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(ResUtil.getColor(R.color.white));

        mThemeColor = ResUtil.getColor(R.color.green);


        mBgRect = new RectF();
        mCircleRect = new RectF();



    }

    private float mCircleLeft;
    private float mCircleLeftBegin = GAP_SIZE;//记录下一次触摸开始时的初始位置

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBgRect.set(0,0,getMeasuredWidth() ,getMeasuredHeight());
        mRadis = getMeasuredHeight() / 2 - GAP_SIZE;


        mMinCircleLeft = GAP_SIZE;
        mMaxCircleLeft = getMeasuredWidth() - GAP_SIZE - mRadis * 2;

        initDrawing();

    }

    private void initDrawing(){
        if (isOpen){
            mCircleLeft = mMaxCircleLeft;
            mBgAlpha = 255;
        }else {
            mCircleLeft = mMinCircleLeft;
            mBgAlpha = 0;
        }
        mCircleLeftBegin = mCircleLeft;

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBg(canvas);
        drawCircle(canvas);
    }

    private boolean isOpen;

    private void drawBg(Canvas canvas){
        mBgPaint.setColor(ResUtil.getColor(R.color.gray));
        canvas.drawRoundRect(mBgRect,mRadis, mRadis, mBgPaint);

        mBgPaint.setColor(mThemeColor);
        mBgPaint.setAlpha(mBgAlpha);
        canvas.drawRoundRect(mBgRect,mRadis, mRadis, mBgPaint);
    }
    private void drawCircle(Canvas canvas){

        mCircleRect.set(mCircleLeft,GAP_SIZE,mCircleLeft+ mRadis * 2,getMeasuredHeight() -GAP_SIZE);
        canvas.drawRoundRect(mCircleRect,mRadis,mRadis,mCirclePaint);
    }

    float mLastX;
    float mOffsetX;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mLastX = x;
                break;
            case MotionEvent.ACTION_MOVE:
                //从手指接触屏幕开始到移动的过程中 滑动的距离
                mOffsetX = x  - mLastX;
                float mTempX = mOffsetX + mCircleLeftBegin;
                if (mTempX < mMinCircleLeft){
                    mTempX = mMinCircleLeft;
                }
                if (mTempX > mMaxCircleLeft){
                    mTempX = mMaxCircleLeft;
                }
                if (mTempX >= mMinCircleLeft && mTempX <= mMaxCircleLeft){
                    mCircleLeft = mTempX;
                    mBgAlpha = (int) (255 * mTempX / mMaxCircleLeft);
                    invalidate();
                }


                break;
            case MotionEvent.ACTION_UP:
                mCircleLeftBegin = mCircleLeft;
                //圆心小于宽度一半,说明开关应该要处于关闭状态
                boolean toLeft = mCircleLeft + mRadis < getMeasuredWidth() / 2;

                //判断如果滑动距离小于3 说明没有滑动，是点击事件
                int wholeOffset = (int) (event.getX() - mLastX);
                if (Math.abs(wholeOffset) < 3){
                    toLeft = !toLeft;
                }
                beginMove(toLeft);
                break;
        }
        return true;
    }

    private void beginMove(final boolean toLeft){
        ValueAnimator animator = ValueAnimator.ofFloat(mCircleLeft, toLeft ? mMinCircleLeft: mMaxCircleLeft);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCircleLeft = (float) animation.getAnimatedValue();
                mBgAlpha = (int) (255 * mCircleLeft / mMaxCircleLeft);
                invalidate();
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (toLeft){
                    if (mListenr != null){
                        mListenr.onClose();
                    }
                    mCircleLeftBegin = mMinCircleLeft;
                }else {
                    if (mListenr != null){
                        mListenr.onOpen();
                    }
                    mCircleLeftBegin = mMaxCircleLeft;
                }
            }
        });
        animator.start();
    }

    private ISwitchListenr mListenr;

    public void setListenr(ISwitchListenr listenr) {
        this.mListenr = listenr;
    }

    public interface ISwitchListenr{
        void onClose();
        void onOpen();
    }


    public void setOpen(boolean open){
        this.isOpen = open;
        initDrawing();
        invalidate();
        if (mListenr != null)
            if (isOpen) {
                mListenr.onOpen();
            } else {
                mListenr.onClose();
            }
        }
}
