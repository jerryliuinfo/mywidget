package com.tcl.widget.demo.ui.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;
import com.tcl.widget.demo.uti.view.RComputer;

/**
 * Created by jerryliu on 2017/4/27.
 */

public class BoostView extends View {
    private Paint mOuterCirclePaint;
    private Paint mArcPaint;
    private Paint mInnerCirclePaint;
    private int[] mArcColors;
    private Bitmap mRocketBitmap;
    public BoostView(Context context) {
        super(context,null);
        init();
    }


    public BoostView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public BoostView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        mOuterCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOuterCirclePaint.setStyle(Paint.Style.STROKE);
        mOuterCirclePaint.setStrokeWidth(ResUtil.dip2px(2));
        mOuterCirclePaint.setColor(ResUtil.getColor(R.color.boostengine_frame_circle));

        mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(ResUtil.dip2px(2));

        mInnerCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInnerCirclePaint.setStyle(Paint.Style.FILL);


        mArcColors = new int[]{ResUtil.getColor(R.color.boostengine_ring_start_color),
                ResUtil.getColor(R.color.boostengine_ring_end_color),ResUtil.getColor(R.color.boostengine_ring_start_color)};

        float[] positions = new float[]{0,0.5f,1};
        SweepGradient sweepGradient = new SweepGradient(mCenterX,mCenterY,mArcColors,positions);
        mArcPaint.setShader(sweepGradient);
    }

    private float  mRadis;//外圆半径
    private RectF mArcRect;
    private int mCenterX,mCenterY;
    private RComputer rComputer;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        computer();
    }

    private void computer(){
        mCenterX = getMeasuredWidth() / 2;
        mCenterY = getMeasuredHeight() / 2;
        float r = mCenterX;
        rComputer = new RComputer(r,mCenterX,mCenterY);
        mRadis = rComputer.getCorrectR(mOuterCirclePaint);

        rComputer.consume(ResUtil.dip2px(5));
        mArcRect = rComputer.getCorrectRect(mArcPaint);


        rComputer.consume(ResUtil.dip2px(5));
        mRocketBitmap = scaleRocketBitmap();
        LinearGradient linearGradient = new LinearGradient(
                mCenterX-rComputer.getRemain(), mCenterY,mCenterX+rComputer.getRemain(),mCenterY,
                ResUtil.getColor(R.color.boostengine_circle_top),ResUtil.getColor(R.color.boostengine_circle_bottom),
                Shader.TileMode.MIRROR);
        mInnerCirclePaint.setShader(linearGradient);
    }

    int realWidth;

    private Bitmap scaleRocketBitmap(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.boostengine_center_icon);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleW = rComputer.getRemain() / width;
        float scaleH = rComputer.getRemain() / height;
        float scale = Math.min(scaleW,scaleH);
        Matrix matrix = new Matrix();
        matrix.setScale(scale,scale);
        realWidth = (int) (width * scale);
        return Bitmap.createBitmap(bitmap,0,0,width,height,matrix,true);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawOutCircle(canvas);
        drawArc(canvas);
        drawInnerCircle(canvas);
    }


    private void drawOutCircle(Canvas canvas){
        canvas.drawCircle(mCenterX,mCenterY,mRadis,mOuterCirclePaint);
    }
    private void drawArc(Canvas canvas){
        canvas.save();
        canvas.rotate(mRoationAngel,mCenterX,mCenterY);
        canvas.drawArc(mArcRect,0,180,false,mArcPaint);

        canvas.rotate(180,mCenterX,mCenterY);
        canvas.drawArc(mArcRect,0,180,false,mArcPaint);

        canvas.restore();
    }

    private void drawInnerCircle(Canvas canvas){
        canvas.drawCircle(mCenterX,mCenterY, rComputer.getRemain(), mInnerCirclePaint);
        canvas.drawBitmap(mRocketBitmap,mCenterX - realWidth/2,mCenterY - realWidth/2,mOuterCirclePaint);
    }



    ValueAnimator mRotateAnimator;
    private float mRoationAngel;

    public void startAnim(){
        if (mRotateAnimator == null){
            mRotateAnimator = ValueAnimator.ofFloat(0,360);
            mRotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
            mRotateAnimator.setDuration(1000);
            mRotateAnimator.setInterpolator(new LinearInterpolator());
            mRotateAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mRoationAngel = (float) animation.getAnimatedValue();
                    invalidate();
                }
            });


        }
        mRotateAnimator.start();
    }

    public boolean isAnimRunning(){
        if (mRotateAnimator != null){
            return mRotateAnimator.isRunning();
        }
        return false;
    }




    public void stopAnim(){
        if (mRotateAnimator != null){
            mRotateAnimator.cancel();
        }
    }


    public int getCenterY() {
        return mCenterY;
    }
}
