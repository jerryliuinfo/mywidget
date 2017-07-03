package com.tcl.widget.demo.ui.widget.github;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;
import com.tcl.widget.demo.uti.view.MeasureUtil;
import com.tcl.widget.demo.uti.view.PaintConfigUtil;

/**
 * Created by jerryliu on 2017/7/3.
 */

public class SlantedTextView extends View {


    public static final int MODE_LEFT = 0;
    public static final int MODE_RIGHT = 1;
    public static final int MODE_LEFT_BOTTOM = 2;
    public static final int MODE_RIGHT_BOTTOM = 3;
    public static final int MODE_LEFT_TRIANGLE = 4;
    public static final int MODE_RIGHT_TRIANGLE = 5;
    public static final int MODE_LEFT_BOTTOM_TRIANGLE = 6;
    public static final int MODE_RIGHT_BOTTOM_TRIANGLE = 7;

    private int mMode = MODE_LEFT;
    private int mBackgroundColor;
    private int mSlantedLength = ResUtil.dp2px(28);

    public static final int ROTATE_ANGLE = 45;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private TextPaint mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
    private Path mPath = new Path();

    private String mSlantedText = "Java";

    public SlantedTextView(Context context) {
        this(context,null);
    }

    public SlantedTextView(Context context, @Nullable AttributeSet attrs) {
        this(context,null,-1);
    }

    public SlantedTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs){
        PaintConfigUtil.configFillPaint(mPaint, ResUtil.getColor(R.color.colorPrimary));
        PaintConfigUtil.configText(mTextPaint,ResUtil.getColor(R.color.white),ResUtil.sp2px(14));
        //mTextPaint.setTextAlign(Paint.Align.CENTER);

        TypedArray array = getContext().obtainStyledAttributes(attrs,R.styleable.SlantedTextView);
        try {
            mMode = array.getInt(R.styleable.SlantedTextView_slantedMode,MODE_LEFT_TRIANGLE);
        }finally {
            array.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        drawText(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int defaultWidth = ResUtil.dp2px(48);
        setMeasuredDimension(MeasureUtil.getMeasuredLength(widthMeasureSpec,defaultWidth),
                MeasureUtil.getMeasuredLength(heightMeasureSpec,defaultWidth));
    }

    private void drawText(Canvas canvas){
        int w = (int) (canvas.getWidth() - mSlantedLength / 2);
        int h = (int) (canvas.getHeight() - mSlantedLength / 2);
        float[] xy = calculateXY(w, h);
        float toX = xy[0];
        float toY = xy[1];
        float centerX = xy[2];
        float centerY = xy[3];
        float angle = xy[4];
        canvas.rotate(angle,centerX,centerY);
        canvas.drawText(mSlantedText,toX,toY,mTextPaint);
    }

    private float[] calculateXY(int w, int h) {
        float[] xy = new float[5];
        Rect rect = null;
        RectF rectF = null;
        int offset = (int) (mSlantedLength / 2);
        switch (mMode) {
            case MODE_LEFT_TRIANGLE:
            case MODE_LEFT:
                rect = new Rect(0, 0, w, h);
                rectF = new RectF(rect);
                rectF.right = mTextPaint.measureText(mSlantedText, 0, mSlantedText.length());
                rectF.bottom = mTextPaint.descent() - mTextPaint.ascent();
                rectF.left = (rect.width() - rectF.right) / 2.0f;
                rectF.top = (rect.height() - rectF.bottom) / 2.0f;
                xy[0] = rectF.left;
                xy[1] = rectF.top - mTextPaint.ascent();
                xy[2] = w / 2;
                xy[3] = h / 2;
                xy[4] = -ROTATE_ANGLE;
                break;
            case MODE_RIGHT_TRIANGLE:
            case MODE_RIGHT:
                rect = new Rect(offset, 0, w + offset, h);
                rectF = new RectF(rect);
                rectF.right = mTextPaint.measureText(mSlantedText, 0, mSlantedText.length());
                rectF.bottom = mTextPaint.descent() - mTextPaint.ascent();
                rectF.left += (rect.width() - rectF.right) / 2.0f;
                rectF.top += (rect.height() - rectF.bottom) / 2.0f;
                xy[0] = rectF.left;
                xy[1] = rectF.top - mTextPaint.ascent();
                xy[2] = w / 2 + offset;
                xy[3] = h / 2;
                xy[4] = ROTATE_ANGLE;
                break;
            case MODE_LEFT_BOTTOM_TRIANGLE:
            case MODE_LEFT_BOTTOM:
                rect = new Rect(0, offset, w, h+offset);
                rectF = new RectF(rect);
                rectF.right = mTextPaint.measureText(mSlantedText, 0, mSlantedText.length());
                rectF.bottom = mTextPaint.descent() - mTextPaint.ascent();
                rectF.left += (rect.width() - rectF.right) / 2.0f;
                rectF.top += (rect.height() - rectF.bottom) / 2.0f;

                xy[0] = rectF.left;
                xy[1] = rectF.top - mTextPaint.ascent();
                xy[2] = w / 2;
                xy[3] = h / 2 + offset;
                xy[4] = ROTATE_ANGLE;
                break;
            case MODE_RIGHT_BOTTOM_TRIANGLE:
            case MODE_RIGHT_BOTTOM:
                rect = new Rect(offset, offset, w+offset, h+offset);
                rectF = new RectF(rect);
                rectF.right = mTextPaint.measureText(mSlantedText, 0, mSlantedText.length());
                rectF.bottom = mTextPaint.descent() - mTextPaint.ascent();
                rectF.left += (rect.width() - rectF.right) / 2.0f;
                rectF.top += (rect.height() - rectF.bottom) / 2.0f;
                xy[0] = rectF.left;
                xy[1] = rectF.top - mTextPaint.ascent();
                xy[2] = w / 2 + offset;
                xy[3] = h / 2 + offset;
                xy[4] = -ROTATE_ANGLE;
                break;
        }
        return xy;
    }

    private void drawBackground(Canvas canvas){
        int w = getWidth();
        int h = getHeight();

        switch (mMode){
            case MODE_LEFT:
                mPath = getLeftModePath(w,h);
                break;
            case MODE_RIGHT:
                break;
            case MODE_LEFT_TRIANGLE:
                mPath = getLeftModeTrianglePath(w,h);
                break;
            case MODE_RIGHT_TRIANGLE:
                break;
            case MODE_LEFT_BOTTOM:
                break;
            case MODE_RIGHT_BOTTOM:
                break;
            case MODE_LEFT_BOTTOM_TRIANGLE:

                break;
            case MODE_RIGHT_BOTTOM_TRIANGLE:
                break;
        }

        mPath.close();
        canvas.drawPath(mPath,mPaint);
    }

    private Path getLeftModePath(int w, int h){
        mPath.reset();
        mPath.moveTo(w,0);
        mPath.lineTo(0,h);
        mPath.lineTo(0,h - mSlantedLength);
        mPath.lineTo(w - mSlantedLength, 0);
        return mPath;
    }

    private Path getLeftModeTrianglePath(int w, int h){
        mPath.reset();
        mPath.lineTo(w,0);
        mPath.lineTo(0,h);
        return mPath;
    }
}
