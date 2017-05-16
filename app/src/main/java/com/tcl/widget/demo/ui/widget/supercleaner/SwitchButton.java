package com.tcl.widget.demo.ui.widget.supercleaner;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.DisplayUtil;
import com.tcl.widget.demo.uti.ResUtil;

/**
 * Created by jerryliu on 2017/5/16.
 */

public class SwitchButton extends android.support.v7.widget.AppCompatImageView {
    private Paint mBgPaint;
    private Paint mSlidePaint;
    private Paint mRipplePaint;

    private RectF mBgRect;
    private float mRippleProgress;


    public SwitchButton(Context context) {
        super(context,null);
        init(context);
    }

    public SwitchButton(Context context, AttributeSet attrs) {
        super(context, attrs,0);
        init(context);
    }

    public SwitchButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        mBgPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBgPaint.setStyle(Paint.Style.FILL);
        mBgPaint.setColor(ResUtil.getColor(R.color.base_gray));

        mRipplePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRipplePaint.setStyle(Paint.Style.FILL);
        mRipplePaint.setColor(ResUtil.getColor(R.color.base_gray));

        mSlidePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSlidePaint.setStyle(Paint.Style.FILL);
        mSlidePaint.setColor(ResUtil.getColor(R.color.green));


        mBgWidth = ResUtil.dip2px(30);
        mBgHeight = ResUtil.dip2px(15);
        mBgRect = new RectF();

        mCircleRadis = ResUtil.dip2px(10);
    }

    private int mBgWidth, mBgHeight;
    private float mCircleRadis;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mBgRect.left = (getMeasuredWidth() - mBgWidth ) / 2;
        mBgRect.right = mBgRect.left + mBgWidth;
        mBgRect.top = (getMeasuredHeight() - mBgHeight ) / 2;
        mBgRect.bottom = mBgRect.top + mBgHeight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBg(canvas);
        drawCircle(canvas);
        drawRipple(canvas);
    }

    private void drawBg(Canvas canvas){
        canvas.drawRoundRect(mBgRect,90,90, mBgPaint);
    }


    private float mDeltaX;

    private void drawCircle(Canvas canvas){
        canvas.drawCircle(mBgRect.left + mCircleRadis + mDeltaX, getMeasuredHeight() / 2, mCircleRadis,mSlidePaint);
    }

    private void drawRipple(Canvas canvas){
        if (mRippleProgress > 0.0f && mRippleProgress < 1.0f){
            this.mRipplePaint.setAlpha((int)((1.0F - this.mRippleProgress) * 255.0F));
            int radius = (int)(this.mRippleProgress < 0.5F?this.mRippleProgress * (float)this.getMeasuredWidth() + (float) DisplayUtil.dp2px(10.0F):(float)(this.getMeasuredWidth() / 2));
            if(radius > this.getMeasuredWidth() / 2) {
                radius = this.getMeasuredWidth() / 2;
            }
            LinearGradient mLinearGradient = new LinearGradient((float)(-radius), 0.0F, 0.0F, 0.0F, new int[]{861954144, -10461088, 861954144}, new float[]{0.0F, 0.5F, 1.0F}, Shader.TileMode.CLAMP);
            mRipplePaint.setShader(mLinearGradient);

            canvas.drawCircle(getMeasuredWidth()/ 2 ,getMeasuredHeight() / 2,radius, mRipplePaint );
        }
    }



    public void performClicked(){
        this.mSlidePaint.setColor(this.getResources().getColor(R.color.base_permission_guide_switch_btn_circle_select));
        this.mBgPaint.setColor(this.getResources().getColor(R.color.base_permission_guide_switch_btn_line_select));
        ValueAnimator animator = ValueAnimator.ofFloat(0,1);
        animator.setDuration(500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animValue = (float) animation.getAnimatedValue();
                mDeltaX = (mBgRect.right - mBgRect.left - mCircleRadis * 2 ) * animValue;

                mRippleProgress = animValue;
                invalidate();
            }
        });
        animator.start();
    }

    public void reset() {
        this.clearAnimation();
        this.mSlidePaint.setColor(Color.argb(255, 217, 217, 217));
        this.mBgPaint.setColor(ResUtil.getColor(R.color.base_permission_guide_switch_btn_line));
        this.mDeltaX = 0;
        this.invalidate();
    }


}
