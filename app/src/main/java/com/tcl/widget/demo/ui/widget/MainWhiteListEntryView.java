package com.tcl.widget.demo.ui.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;


/**
 * Created by jerryliu on 2017/4/19.
 */

public class MainWhiteListEntryView extends View {
    public MainWhiteListEntryView(Context context) {
        super(context,null);
        init(context,null);
    }

    public MainWhiteListEntryView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init(context,attrs);
    }

    public MainWhiteListEntryView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private Paint mLinePaint;
    private Paint mBgPaint;
    private RectF mBgRect;
    private void init(Context context,AttributeSet attrs){
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(ResUtil.dip2px(1));
        mLinePaint.setColor(ResUtil.getColor(R.color.red));

        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBgPaint.setStyle(Paint.Style.FILL);
        mLinePaint.setColor(ResUtil.getColor(R.color.green));

        mBgRect = new RectF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBgRect.left = getPaddingLeft();
        mBgRect.right = w - getPaddingRight();
        mBgRect.top = getPaddingTop();
        mBgRect.bottom = h - getPaddingBottom();

        mStartX = mBgRect.left + ResUtil.dip2px(mLinePadding);

    }

    AnimatorSet animatorSet;
    boolean mShortAnimEnd = false;
    public void initAnim(){
        ValueAnimator longLineAnimator = ValueAnimator.ofFloat(mStartX, mBgRect.right - ResUtil.dip2px(mLinePadding));
        longLineAnimator.setDuration(500);
        longLineAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mLongLineEndX = (float) animation.getAnimatedValue();
                invalidate();
            }
        });

        ValueAnimator shortLineAnimator = ValueAnimator.ofFloat(mStartX, mBgRect.left + mBgRect.width() / 2);
        shortLineAnimator.setDuration(500);
        shortLineAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mShortLineEndX = (float) animation.getAnimatedValue();
                invalidate();
            }

        });
        shortLineAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                mShortAnimEnd = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        ValueAnimator hook1Animator = ValueAnimator.ofFloat();

        animatorSet = new AnimatorSet();
        animatorSet.play(shortLineAnimator).after(longLineAnimator);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBg(canvas);
        drawLongLine(canvas);
        drawShortLine(canvas);
        //drawHook(canvas);
        drawHookV2(canvas);
    }

    private void drawBg(Canvas canvas){
        canvas.drawRect(mBgRect, mBgPaint);
    }

    int mLinePadding = 2;//两条横线的距离
    float mStartX;//横线起点坐标
    float mLongLineEndX;
    float mShortLineEndX;



    private void drawLongLine(Canvas canvas){
        if (mLongLineEndX != 0){
            canvas.drawLine(mStartX,(mBgRect.top + ResUtil.dip2px(mLinePadding)),
                    mLongLineEndX ,(mBgRect.top + ResUtil.dip2px(mLinePadding)), mLinePaint);
        }


    }

     private void drawShortLine(Canvas canvas){
         if (mShortLineEndX != 0){
             canvas.drawLine(mStartX,(mBgRect.top + ResUtil.dip2px(mLinePadding * 3)),
                     mShortLineEndX ,(mBgRect.top + ResUtil.dip2px(mLinePadding * 3)), mLinePaint);
         }

     }

    //线1的x轴增量
    private int line1X = 0;

    //线1的y轴增量
    private int line1Y = 0;

    //线2的x轴增量
    private int line2X = 0;

    //线2的y轴增量
    private int line2Y = 0;

    /**
     * 画勾
     */
    private void drawHook(Canvas canvas){
        if (!mShortAnimEnd){
            return;
        }
        float startx = mStartX + ResUtil.dip2px(mLinePadding);
        float startY = getMeasuredHeight() * 3 / 5.0f;
        float endX = startx + ResUtil.dip2px(4);
        float endY = mBgRect.bottom - ResUtil.dip2px(4);
        canvas.drawLine(startx,startY,endX,endY, mLinePaint);


        float startx2 = endX;
        float startY2 = endY;
        float endX2 = (mBgRect.right - ResUtil.dip2px(mLinePadding));
        float endY2 = getMeasuredHeight() / 2;
        canvas.drawLine(startx2,startY2,endX2,endY2, mLinePaint);

     }


    float mLine1AddedEndX;//划线增加的长度
    float mLine1AddedEndY;


    float LINE1_MAX_LENGTH = 4; //线条1在x方向上的最大长度
    int step = 2;


    float line2EndX;
    float line2EndY;
    private void drawHookV2(Canvas canvas){
        if (!mShortAnimEnd){
            return;
        }

        float startx = mStartX + ResUtil.dip2px(mLinePadding);
        float startY = getMeasuredHeight() * 3 / 5.0f;
        if (mLine1AddedEndX < ResUtil.dip2px(LINE1_MAX_LENGTH)){
            mLine1AddedEndX += step;
            mLine1AddedEndY += step;
        }
        canvas.drawLine(startx,startY,startx + mLine1AddedEndX, startY + mLine1AddedEndY, mLinePaint);
        /*if (mLine1AddedEndX < ResUtil.dip2px(LINE1_MAX_LENGTH)){
            postInvalidateDelayed(10);
        }*/

        //第一根线条已经画完了
        float startx2 = 0;
        if (mLine1AddedEndX >= ResUtil.dip2px(LINE1_MAX_LENGTH)){
            startx2 = startx + mLine1AddedEndX;

            float startY2 = startY + mLine1AddedEndY;
            if (!line2PositionInited){
                line2EndX = startx2;
                line2EndY = startY2;
                line2PositionInited = true;
            }
            line2EndX += step;
            line2EndY -= step;

            canvas.drawLine(startx2,startY2,line2EndX, line2EndY, mLinePaint);
        }
        if (line2EndX < mBgRect.right - ResUtil.dip2px(mLinePadding)){
            postInvalidateDelayed(10);
        }
    }

    private boolean line2PositionInited = false;

     public void startAnimation(long delayMilles){
         postDelayed(new Runnable() {
             @Override
             public void run() {
                if (animatorSet == null){
                    initAnim();
                }
                animatorSet.start();
             }
         }, delayMilles);
     }


}
