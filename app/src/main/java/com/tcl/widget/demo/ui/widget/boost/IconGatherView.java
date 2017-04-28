package com.tcl.widget.demo.ui.widget.boost;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.NLog;
import com.tcl.widget.demo.uti.RandomUtil;
import com.tcl.widget.demo.uti.ResUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by jerryliu on 2017/4/28.
 */

public class IconGatherView extends View {
    public static final String TAG = IconGatherView.class.getSimpleName();

    private int mBubbleRadis;
    private static final float BUBBLE_GEN_MAX_RADIUS = 1f;//气泡生成的最大半径比，相对于整个页面，限制了生成气泡的位置
    private static final float BUBBLE_GEN_MIN_RADIUS = 0.5f;//气泡生成的最小半径比

    private int mBubbleGenMaxRadius; //气泡距离中心点的最大距离
    private int mBubbleGenMinRadius; //气泡距离中心点的最小距离
    private Random mRandom;
    private Paint mPaint;
    private int mCenterX;
    private int mCenterY;



    private List<BoostAnimator> mBubbleAnims = new ArrayList<>();
    private List<BoostAnimator> mIconAnims = new ArrayList<>();

    public IconGatherView(Context context) {
        super(context,null);
        init(context);
    }

    public IconGatherView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(ResUtil.getColor(R.color.white));
        mBubbleRadis = ResUtil.dip2px(7);
        mRandom = new Random(10);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        compute();
    }

    private void compute(){
        mCenterX = getMeasuredWidth() /2 ;
        mCenterY = getMeasuredHeight() / 2;
        mBubbleGenMaxRadius = (int) (Math.min(mCenterX,mCenterY) * BUBBLE_GEN_MAX_RADIUS);
        mBubbleGenMinRadius = (int) (Math.min(mCenterX,mCenterY) * BUBBLE_GEN_MIN_RADIUS);

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        generateBubble();

        clearBubbles();
        if (mBubbleAnims.size() > 0){
            for (BoostAnimator bubbleAnimator: mBubbleAnims){
                bubbleAnimator.onDraw(canvas, mPaint);
            }
        }
        synchronized (mIconAnims){
            if (mIconAnims.size() > 0){
                for (BoostAnimator animator: mIconAnims){
                    animator.onDraw(canvas,mPaint);
                }
            }
        }

    }

    private void clearBubbles(){
        Iterator<BoostAnimator> it = mBubbleAnims.iterator();
        while (it.hasNext()) {
            BoostAnimator anim = it.next();
            if (anim.isAnimStoped()) {
//                pool.recycle(anim);
                it.remove();
            }
        }
    }


    public static final float POP_FACTOR  = 0.02f;
    private void generateBubble(){
        float value = mRandom.nextFloat();
        if (value >= 1 - POP_FACTOR){
            BoostAnimator boostAnmiator = new BubbleAnimView(mBubbleRadis);
            resetBoostAnim(boostAnmiator);
            mBubbleAnims.add(boostAnmiator);
        }
    }

    private void generateIcon(Bitmap bitmap){
        float value = mRandom.nextFloat();
        if (value >= 1 - POP_FACTOR){
            IconAnimView iconAnimView = new IconAnimView();
            resetBoostAnim(iconAnimView);
            iconAnimView.setBitmap(bitmap);
            mIconAnims.add(iconAnimView);
        }
    }



    private void resetBoostAnim(BoostAnimator boostAnimator){
        //随机产生气泡距离中心的距离
        float randR = RandomUtil.randInt(mBubbleGenMinRadius,mBubbleGenMaxRadius);
        //随机生成一个角度 转换成弧度用于计算坐标
        float angel = (float) (RandomUtil.randInt(0,360) * Math.PI / 180);

        int orginalX = (int) (mCenterX + randR * Math.cos(angel));
        int orinalY = (int) (mCenterY + randR * Math.sin(angel));
        boostAnimator.reset(mCenterX,mCenterY,orginalX,orinalY);
        boostAnimator.start();

    }

    private volatile boolean isRun;

    public void start(){
        isRun = true;
        /*post(new Runnable() {
            @Override
            public void run() {
                invalidate();
                if (isRun){
                    postDelayed(this, 16);
                }
            }
        });*/

    }

    public void stop(){
        isRun = false;

    }


    private Runnable iconRunnable = new Runnable() {
        @Override
        public void run() {
            if (isRun){
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
                if (bitmap != null && bitmap.getWidth() > 0 && bitmap.getHeight() > 0){
                    generateIcon(bitmap);
                }
                postDelayed(this, 3000);
            }
        }
    };
}
