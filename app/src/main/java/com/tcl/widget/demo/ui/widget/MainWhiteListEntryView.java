package com.tcl.widget.demo.ui.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.MyAnimatorListener;
import com.tcl.widget.demo.uti.NLog;
import com.tcl.widget.demo.uti.ResUtil;


/**
 * Created by jerryliu on 2017/4/19.
 */

public class MainWhiteListEntryView extends View {
    public static final String TAG = MainWhiteListEntryView.class.getSimpleName();
    private Paint mLinePaint;
    private Paint mBgPaint;
    private RectF mBgRect;


    AnimatorSet animatorSet;


    float mLinePadding = 1.65f;//两条横线的距离
    float mLineTopPadding = 1.95f;//两条横线的距离

    float mHorizentalLineStartX;//横线起点x坐标


    float mHorizentalLongLineEndX;//长条横线的终点x坐标
    float mHorizontalLongLineY;//长条横线y坐标


    float mHorizentalShortLineEndX;//短条横线的终点x坐标
    float mHorizontalShortLineY;//短条横线y坐标



    float LINE1_MAX_LENGTH = 4; //勾的线条1在x方向上的最大长度



    public static final int ANIM_DURATION = 200;

    private int mAnimDuration;

    //打勾第一条线的x起点坐标
    float mHookLine1StartX;
    //打勾第一条线的Y起点坐标
    float mHookLine1StartY;

    private Point firtstPoint;
    private Point secondPoint;
    private Point thirdPoint;

    Point firstLineCurrentPoint;
    Point secondLineCurrentPoint;
    private boolean mHookLine1AnimStarted;

    private boolean mHookLine2AnimStarted;

    private boolean mAnimEnd = true;

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


    private void init(Context context,AttributeSet attrs){
        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setStyle(Paint.Style.STROKE);
        mLinePaint.setStrokeWidth(ResUtil.dp2px(2));
        mLinePaint.setColor(ResUtil.getColor(R.color.black));

        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBgPaint.setStyle(Paint.Style.FILL);
        mBgPaint.setColor(ResUtil.getColor(R.color.white));

        mBgRect = new RectF();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBgRect.left = getPaddingLeft();
        mBgRect.right = w - getPaddingRight();
        mBgRect.top = getPaddingTop();
        mBgRect.bottom = h - getPaddingBottom();

        mHorizentalLineStartX = mBgRect.left + ResUtil.dp2px(mLinePadding);
        //打勾第一条线的x起点坐标
        mHookLine1StartX = mHorizentalLineStartX + ResUtil.dp2px(mLinePadding);
        mHookLine1StartY = getMeasuredHeight() * 3 / 5.0f;

        firtstPoint = new Point((int)mHookLine1StartX, (int)mHookLine1StartY);
        secondPoint = new Point((int)mHookLine1StartX + ResUtil.dp2px(LINE1_MAX_LENGTH), (int)mHookLine1StartY + ResUtil.dp2px(LINE1_MAX_LENGTH));
        thirdPoint = new Point((int) (mBgRect.right - ResUtil.dp2px(mLinePadding)), (int) (mHorizontalShortLineY + ResUtil.dp2px(mLinePadding)));
        NLog.d(TAG, "first point x = %s, y = %s, second point x = %s, y = %s, third point x = %s, y = %s",
                firtstPoint.x,firtstPoint.y,secondPoint.x,secondPoint.y,thirdPoint.x,thirdPoint.y
        );


    }


    public void initAnim(){
        ValueAnimator longLineAnimator = ValueAnimator.ofFloat(mHorizentalLineStartX, mBgRect.right - ResUtil.dp2px(mLinePadding));
        longLineAnimator.setDuration(mAnimDuration);
        longLineAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mHorizentalLongLineEndX = (float) animation.getAnimatedValue();
                invalidate();
            }
        });



        ValueAnimator shortLineAnimator = ValueAnimator.ofFloat(mHorizentalLineStartX, mBgRect.left + mBgRect.width() / 2);
        shortLineAnimator.setDuration(mAnimDuration);
        shortLineAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mHorizentalShortLineEndX = (float) animation.getAnimatedValue();
                invalidate();
            }

        });



        ValueAnimator hookLine1Anim = ValueAnimator.ofObject(new MyPointEvaluator(), firtstPoint, secondPoint);
        hookLine1Anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                firstLineCurrentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        hookLine1Anim.addListener(new MyAnimatorListener(){
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mHookLine1AnimStarted = true;
            }


        });

        ValueAnimator hookAnim2 = ValueAnimator.ofObject(new MyPointEvaluator(), secondPoint, thirdPoint);
        hookAnim2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                secondLineCurrentPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        hookAnim2.addListener(new MyAnimatorListener(){
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mHookLine2AnimStarted = true;
            }
        });


        animatorSet = new AnimatorSet();
        animatorSet.addListener(new MyAnimatorListener(){
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                mAnimEnd = false;
                mHookLine1AnimStarted = false;
                mHookLine2AnimStarted = false;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mAnimEnd = true;
            }
        });
        animatorSet.playSequentially(longLineAnimator,shortLineAnimator,hookLine1Anim,hookAnim2);

    }

    class MyPointEvaluator implements TypeEvaluator<Point>{

        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            Point point = new Point();
            float x = startValue.x + fraction * (endValue.x - startValue.x);
            float y = startValue.y + fraction * (endValue.y - startValue.y);
            point.set((int)x, (int)y);
            return point;
        }
    }








    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBg(canvas);
        drawLongLine(canvas);
        drawShortLine(canvas);
        drawHookV2(canvas);
    }

    private void drawBg(Canvas canvas){
        canvas.drawRoundRect(mBgRect,ResUtil.dp2px(1),ResUtil.dp2px(1),mBgPaint);
    }






    private void drawLongLine(Canvas canvas){
        if (mHorizentalLongLineEndX != 0){
            mHorizontalLongLineY = mBgRect.top + ResUtil.dp2px(mLineTopPadding) + mLinePaint.getStrokeWidth() / 2;
            canvas.drawLine(mHorizentalLineStartX, mHorizontalLongLineY, mHorizentalLongLineEndX, mHorizontalLongLineY, mLinePaint);
        }


    }

     private void drawShortLine(Canvas canvas){
         if (mHorizentalShortLineEndX != 0){
             mHorizontalShortLineY = mHorizontalLongLineY + ResUtil.dp2px(mLineTopPadding) + mLinePaint.getStrokeWidth() / 2;
             thirdPoint.y = (int) (mHorizontalShortLineY +ResUtil.dp2px(mLineTopPadding));
             canvas.drawLine(mHorizentalLineStartX, mHorizontalShortLineY, mHorizentalShortLineEndX, mHorizontalShortLineY, mLinePaint);
         }

     }





    /**
     * 画勾
     * @param canvas
     */
    private void drawHookV2(Canvas canvas){
        if (mHookLine1AnimStarted ){
            if (firstLineCurrentPoint != null){
                canvas.drawLine(mHookLine1StartX,mHookLine1StartY, firstLineCurrentPoint.x, firstLineCurrentPoint.y, mLinePaint);
            }
        }
        //第一根线画完了
        if (mHookLine2AnimStarted){
            if (secondLineCurrentPoint != null){
                canvas.drawLine(firstLineCurrentPoint.x-2,firstLineCurrentPoint.y, secondLineCurrentPoint.x, secondLineCurrentPoint.y, mLinePaint);
            }
        }
    }

     public void startAnimation(long delayMilles){
         postDelayed(new Runnable() {
             @Override
             public void run() {
                 if (!mAnimEnd){
                         NLog.d(TAG, "startAnimation mAnimationEnd = false, return");
                     return;
                 }
                if (animatorSet == null){
                    initAnim();
                }
                animatorSet.start();
             }
         }, delayMilles);
     }



    public void setAnimDuration(int animDuration) {
        if (this.mAnimDuration != animDuration){
            mAnimDuration = animDuration;
            initAnim();

        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
