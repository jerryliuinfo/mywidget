package com.tcl.widget.demo.ui.widget.threestep;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;

/**
 * Created by jerryliu on 2017/4/30.
 */

public class PathEffect extends View {
    public PathEffect(Context context) {
        super(context,null);
        init(context);
    }

    public PathEffect(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init(context);
    }

    public PathEffect(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private Paint mPaint;
    private Path mPath;
    private void init(Context context){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(ResUtil.dip2px(2));
        mPaint.setColor(ResUtil.getColor(R.color.white));

        mPath = new Path();
        dashPathEffect = new DashPathEffect(new float[]{20,10,100,100},0);
    }

    DashPathEffect dashPathEffect;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.moveTo(100,600);
        mPath.lineTo(400,100);
        mPath.lineTo(700,900);



        //canvas.drawPath(mPath,mPaint);


        mPaint.setColor(ResUtil.getColor(R.color.blue));
        mPaint.setPathEffect(dashPathEffect);
        canvas.translate(0,100);
        canvas.drawPath(mPath,mPaint);

        mPaint.setColor(ResUtil.getColor(R.color.red));
        mPaint.setPathEffect(new DashPathEffect(new float[]{20,10,100,100},value));
        canvas.translate(0,100);
        canvas.drawPath(mPath,mPaint);

    }

    int value;
    public void startAnim(){
        ValueAnimator animator = ValueAnimator.ofInt(0,230);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                value = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

}
