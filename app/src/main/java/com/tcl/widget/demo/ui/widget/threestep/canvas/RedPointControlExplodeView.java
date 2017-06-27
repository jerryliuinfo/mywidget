package com.tcl.widget.demo.ui.widget.threestep.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;

/**
 * Created by jerryliu on 2017/6/10.
 */

public class RedPointControlExplodeView extends FrameLayout {
    private Paint mPaint;
    private PointF mStartPoint;
    private PointF mCurrentPoint;
    private float DEFAULT_RADIS = 20;
    private float mRadis = DEFAULT_RADIS;
    //拖动过程中圆的最小半径
    public static final int MIN_RADIS = 10;
    private boolean mTouche;
    private Path mPath;

    private TextView mTipTextView;
    private Rect mTipTextRectF;

    private ImageView mExplodeImageView;
    private boolean mIsAnimStart = false;


    public RedPointControlExplodeView(@NonNull Context context) {
        super(context,null);
        init();
    }

    public RedPointControlExplodeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public RedPointControlExplodeView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(ResUtil.getColor(R.color.red));

        mStartPoint = new PointF(100,100);
        mCurrentPoint = new PointF();

        mPath = new Path();

        mTipTextRectF = new Rect();

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTipTextView = new TextView(getContext());
        mTipTextView.setLayoutParams(params);
        mTipTextView.setPadding(ResUtil.dp2px(5), ResUtil.dp2px(5), ResUtil.dp2px(5), ResUtil.dp2px(5));
        mTipTextView.setBackgroundResource(R.drawable.red_point_textview_bg);
        mTipTextView.setTextColor(Color.WHITE);
        mTipTextView.setText("99+");

        mExplodeImageView = new ImageView(getContext());
        mExplodeImageView.setLayoutParams(params);
        mExplodeImageView.setImageDrawable(ContextCompat.getDrawable(getContext(),R.drawable.tip_anim));
        mExplodeImageView.setVisibility(INVISIBLE);

        addView(mTipTextView);
        addView(mExplodeImageView);

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {

        canvas.saveLayer(0,0,getWidth(),getHeight(),mPaint,Canvas.CLIP_SAVE_FLAG);
        if (!mTouche || mIsAnimStart){
            mTipTextView.setX(mStartPoint.x - mTipTextView.getWidth() / 2);
            mTipTextView.setY(mStartPoint.y - mTipTextView.getHeight() / 2);
        }else {
            caculatePath();
            canvas.drawCircle(mStartPoint.x,mStartPoint.y, mRadis,mPaint);
            canvas.drawCircle(mCurrentPoint.x,mCurrentPoint.y,mRadis,mPaint);
            canvas.drawPath(mPath,mPaint);

            mTipTextView.setX(mCurrentPoint.x - mTipTextView.getWidth() / 2);
            mTipTextView.setY(mCurrentPoint.y - mTipTextView.getHeight() / 2);
        }
        canvas.restore();
        super.dispatchDraw(canvas);

    }


    private void caculatePath(){
        float startX = mStartPoint.x;
        float startY = mStartPoint.y;

        float endX = mCurrentPoint.x;
        float endY = mCurrentPoint.y;

        //两个圆之间圆心的距离
        float dx = endX - startX;
        float dy = endY - startY;

        double a = Math.atan(dx / dy);

        //切点和圆心之间在x，y方向上的偏移
        float offsetX = (float) (mRadis * Math.sin(a));
        float offsetY = (float) (mRadis * Math.cos(a));

        float x1 = startX + offsetX;
        float y1 = startY - offsetY;

        float x2 = endX + offsetX;
        float y2 = endY - offsetY;

        float x3 = endX - offsetX;
        float y3 = endY + offsetY;

        float x4 = startX - offsetX;
        float y4 = startY + offsetY;

        float anchorX = (startX + endX) / 2;
        float anchorY = (startY + endY) / 2;

        mPath.reset();
        mPath.moveTo(x1,y1);

        mPath.quadTo(anchorX,anchorY,x2,y2);

        mPath.lineTo(x3,y3);
        mPath.quadTo(anchorX,anchorY,x4,y4);

        double distance = Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));
        mRadis = (float) (DEFAULT_RADIS - distance / 15);
        if (mRadis <= MIN_RADIS){
            //mRadis = MIN_RADIS;
            mIsAnimStart = true;

            mExplodeImageView.setX(mCurrentPoint.x - mTipTextView.getWidth() / 2);
            mExplodeImageView.setY(mCurrentPoint.y - mTipTextView.getHeight() / 2);
            mExplodeImageView.setVisibility(VISIBLE);
            ((AnimationDrawable)(mExplodeImageView.getDrawable())).start();
            mTipTextView.setVisibility(INVISIBLE);
        }

    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (isTouchedInTextViewArea(event)){
                    mTouche = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                mTouche = false;
                break;
        }
        mCurrentPoint.set(event.getX(),event.getY());
        postInvalidate();
        return true;
    }


    private boolean isTouchedInTextViewArea(MotionEvent event){
        int[] location = new int[2];
        mTipTextView.getLocationOnScreen(location);
        mTipTextRectF.left = location[0];
        mTipTextRectF.top = location[1];
        mTipTextRectF.right = mTipTextView.getWidth() + location[0];
        mTipTextRectF.bottom = mTipTextView.getHeight() + location[1];

        if (mTipTextRectF.contains((int) event.getRawX(), (int) event.getRawY())) {
            return true;
        }
        return false;
    }

}
