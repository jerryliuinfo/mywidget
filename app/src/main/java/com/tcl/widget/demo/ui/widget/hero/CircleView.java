package com.tcl.widget.demo.ui.widget.hero;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;
import com.tcl.widget.demo.uti.ViewUtil;

/**
 * Created by jerryliu on 2017/4/22.
 */

public class CircleView extends View {
    private Paint mCirclePaint;
    private TextPaint mTextPaint;
    private Paint mArcPaint;
    public CircleView(Context context) {
        super(context,null);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(ResUtil.getColor(R.color.white));

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(ResUtil.sp2px(12));
        mTextPaint.setColor(ResUtil.getColor(R.color.black));
        //下面这行是实现水平居中
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mArcPaint.setStyle(Paint.Style.STROKE);
        mArcPaint.setStrokeWidth(ResUtil.dip2px(20));
        mArcPaint.setColor(ResUtil.getColor(R.color.blue));



        mArcRect = new RectF();
    }

    private int mCircleXY;
    private int mRadis;
    private RectF mArcRect;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (w > 0){
            mCircleXY = w / 2;
            mRadis =  (w - getPaddingLeft() - getPaddingRight())/ 4;

            mArcRect.left = getPaddingLeft();
            mArcRect.top = getPaddingTop();
            mArcRect.right = w - getPaddingRight();
            mArcRect.bottom = h - getPaddingBottom();

        }


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(ViewUtil.measureView(widthMeasureSpec,300),ViewUtil.measureView(widthMeasureSpec,300));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawCircle(canvas);
        drawArc(canvas);
        drawText(canvas);
    }


    private void drawCircle(Canvas canvas){
        canvas.drawCircle(mCircleXY,mCircleXY,mRadis,mCirclePaint);
    }

    private void drawArc(Canvas canvas){
        canvas.drawArc(mArcRect,0,270,false, mArcPaint);
    }

    private void drawText(Canvas canvas){
        String txt = "Hello World";
        Rect rect = new Rect();
        mTextPaint.getTextBounds(txt,0,txt.length(),rect);
        canvas.drawText(txt,mCircleXY ,ViewUtil.geBaseLineY(mTextPaint, getMeasuredHeight()), mTextPaint);
    }


}
