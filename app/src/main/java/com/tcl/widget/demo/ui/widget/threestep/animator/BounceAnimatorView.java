package com.tcl.widget.demo.ui.widget.threestep.animator;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;

/**
 * Created by jerryliu on 2017/5/10.
 */

public class BounceAnimatorView extends View {
    private Paint mPaint;



    public BounceAnimatorView(Context context) {
        super(context,null);
        init(context);
    }

    public BounceAnimatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init(context);
    }

    public BounceAnimatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(ResUtil.getColor(R.color.red));
        mPaint.setStyle(Paint.Style.FILL);
    }


    private float mRadis;
    private Point mCurrentPoint;

    public void doAnimation(){
        ValueAnimator radisAnimator = ValueAnimator.ofFloat(20,200);
        //radisAnimator.setInterpolator(new BounceInterpolator());
        radisAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mRadis = (float) animation.getAnimatedValue();
                invalidate();
            }
        });


        ValueAnimator centerAnimator = ValueAnimator.ofObject(new PointEvaluator(), new Point(100,100), new Point(getMeasuredWidth() / 2,getMeasuredHeight() /  2));
        centerAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mCurrentPoint = (Point) animation.getAnimatedValue();
            }
        });


        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(2000);
        animatorSet.playTogether(centerAnimator,radisAnimator);

        animatorSet.start();

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mRadis > 0){
            canvas.drawCircle(mCurrentPoint.mCenterX ,mCurrentPoint.mCenterY, mRadis,mPaint);

        }

    }
}
