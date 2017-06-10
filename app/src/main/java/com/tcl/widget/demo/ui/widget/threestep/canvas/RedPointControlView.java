package com.tcl.widget.demo.ui.widget.threestep.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;

/**
 * Created by jerryliu on 2017/6/10.
 */

public class RedPointControlView extends FrameLayout {
    private Paint mPaint;
    private PointF mStartPoint;
    private PointF mCurrentPoint;
    private int mRadis = 100;
    private boolean mTouche = false;
    private Path mPath;
    private TextView mTipTextView;


    public RedPointControlView(@NonNull Context context) {
        super(context,null);
        init();
    }

    public RedPointControlView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public RedPointControlView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
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


        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTipTextView = new TextView(getContext());
        mTipTextView.setLayoutParams(params);
        mTipTextView.setPadding(10, 10, 10, 10);
        mTipTextView.setBackgroundResource(R.drawable.red_point_textview_bg);
        mTipTextView.setTextColor(Color.GREEN);
        mTipTextView.setText("99+");
        addView(mTipTextView);



    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        canvas.saveLayer(0,0,getWidth(),getHeight(),mPaint,Canvas.CLIP_SAVE_FLAG);
        if (!mTouche){
            mTipTextView.setX(mStartPoint.x - mTipTextView.getMeasuredWidth() / 2);
            mTipTextView.setY(mStartPoint.y - mTipTextView.getMeasuredHeight() / 2);
        }else {
            canvas.drawCircle(mStartPoint.x,mStartPoint.y, mRadis,mPaint);
            canvas.drawCircle(mCurrentPoint.x,mCurrentPoint.y,mRadis,mPaint);
            caculatePath();
            canvas.drawPath(mPath,mPaint);
        }
        canvas.restore();
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


    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mTouche = true;
                break;
            case MotionEvent.ACTION_UP:
                mTouche = false;
                break;
        }
        mCurrentPoint.set(event.getX(),event.getY());
        postInvalidate();
        return true;
    }
}
