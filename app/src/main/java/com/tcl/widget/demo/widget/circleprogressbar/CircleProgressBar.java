package com.tcl.widget.demo.widget.circleprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.NLog;
import com.tcl.widget.demo.uti.UnitUtils;

import java.lang.reflect.Field;

/**
 * @author Jerry
 * @Description:
 * @date 2016/8/13 11:16
 * @copyright TCL-MIG
 */

public class CircleProgressBar extends ProgressBar {
    private static final String TAG = CircleProgressBar.class.getSimpleName();

    private int mStyle;
    private static final int STYLE_LINE = 0;
    private static final int STYLE_SOLID = 1;
    private static final int STYLE_SOLID_LINE = 2;


    private int mShader;
    private static final int SHADER_LINEAR = 0;
    private static final int SHADER_RADIAL = 1;
    private static final int SHADER_SWEEP = 2;

    private Paint.Cap mCap;


    //背景颜色
    private int mBackgroundColor;
    private int mProgressTextSize;
    private int mProgressTextColor;

    private static final String DEFAULT_TEXT_RED_COLOR = "#ffff0000";
    //进度条背景颜色
    private int mProgressBackgroundColor;
    //进度条开始颜色
    private int mProgressStartColor;
    //进度条结束颜色
    private int mProgressEndColor;

    private int mProgressStrokeWidth; //当mProgressPaint不为Fill时 设置的strokeWidth
    private static final float DEFAULT_PROGRESS_STROKE_WIDTH = 4.0f;

    //进度条文字的画笔
    private Paint mProgressTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //整个背景的画笔
    private Paint mBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    //进度条背景的画笔
    private Paint mProgressBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    //进度条画笔
    private Paint mProgressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private static final float DEFAULT_PROGRESS_TEXT_SIZE = 11.0f;

    private int mCenterX; //圆中心的x坐标
    private int mCenterY; //圆中心的Y坐标
    private int mRadius;  //圆半径

    private RectF mProgressRect = new RectF();
    private Rect mProgressTextRect = new Rect();

    private static final float DEFAULT_START_DEGREE = -90.0f;
    //进度值
    private int mProgress;

    private int mLineCount; //为线条时的条数
    private int mLineWidth;  //线条长度
    private static final int DEFAULT_LINE_COUNT = 45;
    private static final float DEFAULT_LINE_WIDTH = 4.0f;




    public CircleProgressBar(Context context) {
        super(context);
        init(context, null);
    }

    public CircleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
       // adjustIndeterminate();
        init(context, attrs);
        initPaint();
    }

    private void init(Context context, AttributeSet attrs){
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleProgressBar);
        mBackgroundColor = a.getColor(R.styleable.CircleProgressBar_background_color, Color.TRANSPARENT);

        mProgressBackgroundColor = a.getColor(R.styleable.CircleProgressBar_progress_background_color, getResources().getColor(R.color.progress_bg_color));
        mProgressStartColor = a.getColor(R.styleable.CircleProgressBar_progress_start_color, getResources().getColor(R.color.progress_start_color));
        mProgressEndColor = a.getColor(R.styleable.CircleProgressBar_progress_end_color, getResources().getColor(R.color.progress_start_color));

        mProgressStrokeWidth = a.getDimensionPixelSize(R.styleable.CircleProgressBar_progress_stroke_width, UnitUtils.dip2px(getContext(),DEFAULT_PROGRESS_STROKE_WIDTH));


        mProgressTextColor = a.getColor(R.styleable.CircleProgressBar_progress_text_color, Color.parseColor(DEFAULT_TEXT_RED_COLOR));
        mProgressTextSize = a.getDimensionPixelSize(R.styleable.CircleProgressBar_progress_text_size, UnitUtils.dip2px(getContext(), DEFAULT_PROGRESS_TEXT_SIZE));

        mStyle = a.getInt(R.styleable.CircleProgressBar_style, STYLE_LINE);
        mShader = a.getInt(R.styleable.CircleProgressBar_shader, SHADER_LINEAR);
        if (a.hasValue(R.styleable.CircleProgressBar_progress_stroke_cap)){
            mCap = Paint.Cap.values()[a.getInt(R.styleable.CircleProgressBar_progress_stroke_cap, 0)];
        }else {
            mCap = Paint.Cap.BUTT;
        }

        mLineCount = a.getInt(R.styleable.CircleProgressBar_progress_line_count, DEFAULT_LINE_COUNT);
        mLineWidth = a.getDimensionPixelSize(R.styleable.CircleProgressBar_progress_line_width, UnitUtils.dip2px(getContext(), DEFAULT_LINE_WIDTH));



        a.recycle();
    }

    private void initPaint(){
        //初始化背景画笔
        mBackgroundPaint.setStyle(Paint.Style.FILL);
        mBackgroundPaint.setColor(mBackgroundColor);


        //初始化进度条背景圆弧画笔
        if (mStyle == STYLE_SOLID){
            mProgressBackgroundPaint.setStyle(Paint.Style.FILL);
        }else {
            mProgressBackgroundPaint.setStyle(Paint.Style.STROKE);
            mProgressBackgroundPaint.setStrokeWidth(mProgressStrokeWidth);
        }
        mProgressBackgroundPaint.setColor(mProgressBackgroundColor);

        //初始化进度条画笔
        if (mStyle == STYLE_SOLID){
            mProgressPaint.setStyle(Paint.Style.FILL);
        }else {
            mProgressPaint.setStyle(Paint.Style.STROKE);
            mProgressPaint.setStrokeWidth(mProgressStrokeWidth);
        }

        mProgressPaint.setColor(mProgressStartColor);


        //初始化进度条文字画笔
        mProgressTextPaint.setTextAlign(Paint.Align.CENTER);
        mProgressTextPaint.setColor(mProgressTextColor);
        mProgressTextPaint.setTextSize(mProgressTextSize);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;
        mRadius = Math.min(mCenterX, mCenterY);
        NLog.d(TAG, "onSizeChanged w = "+ w +", h = "+h +", mRadius = "+mRadius);
        mProgressRect.top = mCenterY - mRadius;
        mProgressRect.bottom = mCenterY + mRadius;
        mProgressRect.left = mCenterX - mRadius;
        mProgressRect.right = mCenterX + mRadius;

        updateProgressShader();

    }

    private void updateProgressShader() {
        if (mProgressStartColor != mProgressEndColor){
            Shader shader = null;
            switch (mShader){
                case SHADER_LINEAR:
                    shader = new LinearGradient(mProgressRect.left, mProgressRect.top, mProgressRect.left,
                            mProgressRect.bottom, mProgressStartColor, mProgressEndColor, Shader.TileMode.CLAMP);
                    break;
                case SHADER_RADIAL:
                    shader = new RadialGradient(mCenterX, mCenterY, mRadius, mProgressStartColor, mProgressEndColor, Shader.TileMode.CLAMP);
                    break;
                case SHADER_SWEEP:
                    float radian = (float) (mProgressStrokeWidth / Math.PI * 2.0f / mRadius);
                    float rotateDegrees = (float) (DEFAULT_START_DEGREE
                            - (mCap == Paint.Cap.BUTT && mStyle == STYLE_SOLID_LINE ? 0 : Math.toDegrees(radian)));

                    shader = new SweepGradient(mCenterX, mCenterY, new int[] {mProgressStartColor, mProgressEndColor},
                            new float[] {0.0f, 1.0f});
                    Matrix matrix = new Matrix();
                    matrix.postRotate(rotateDegrees, mCenterX, mCenterY);
                    shader.setLocalMatrix(matrix);
                    break;
            }

            mProgressPaint.setShader(shader);
        }else {

        }
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBackground(canvas);
        drawProgress(canvas);
        drawProgressText(canvas);
    }

    private void drawBackground(Canvas canvas){
        if (mBackgroundColor != Color.TRANSPARENT){
            canvas.drawCircle(mCenterX, mCenterY, mRadius, mBackgroundPaint);
        }
    }

    private void drawProgress(Canvas canvas){
        switch (mStyle){
            case STYLE_LINE:
                drawLineProgress(canvas);
                break;

            case STYLE_SOLID:
                drawSolidProgress(canvas);
                break;
            case STYLE_SOLID_LINE:
                drawSolidLineProgress(canvas);
                break;
            default:
                break;

        }

    }


    private void drawSolidProgress(Canvas canvas){
        canvas.drawArc(mProgressRect, DEFAULT_START_DEGREE, 360.0f, false, mProgressBackgroundPaint);
        canvas.drawArc(mProgressRect, DEFAULT_START_DEGREE, 360.0f * getProgress() / getMax(), true, mProgressPaint);
    }

    /**
     * Just draw arc
     */
    private void drawSolidLineProgress(Canvas canvas) {
        canvas.drawArc(mProgressRect, DEFAULT_START_DEGREE, 360.0f, false, mProgressBackgroundPaint);
        canvas.drawArc(mProgressRect, DEFAULT_START_DEGREE, 360.0f * getProgress() / getMax(), false, mProgressPaint);
    }


    private void drawLineProgress(Canvas canvas){
        float unitProgress = (float) (2.0f * Math.PI / mLineCount);

        final int outCircleRadis = mRadius;
        final int innerCircleRadis = mRadius - mLineWidth;
        final int progressLineCount = (int) (((float)getProgress() / (float) getMax()) * mLineCount);
        for (int i = 0; i < mLineCount; i++){
            float rotateDegrees = i * unitProgress;
            float startX = mCenterX + (float) Math.sin(rotateDegrees) * innerCircleRadis;
            float startY = mCenterX - (float) Math.cos(rotateDegrees) * innerCircleRadis;

            float stopX = mCenterX + (float) Math.sin(rotateDegrees) * outCircleRadis;
            float stopY = mCenterX - (float) Math.cos(rotateDegrees) * outCircleRadis;

            if (i < progressLineCount) {
                canvas.drawLine(startX, startY, stopX, stopY, mProgressPaint);
            } else {
                canvas.drawLine(startX, startY, stopX, stopY, mProgressBackgroundPaint);
            }
        }
    }

    private int count = 0;
    private void drawProgressText(Canvas canvas){
        String text = getProgress() + "%";
        mProgressTextPaint.getTextBounds(text, 0, text.length(), mProgressTextRect);
        count++;
        if (count >= 10){
            count = 0;
            NLog.d(TAG, "drawProgressText text = %s", text);
        }
        canvas.drawText(text, mCenterX, mCenterY + mProgressTextRect.height() / 2, mProgressTextPaint);
    }

    public int getProgress() {
        return mProgress;
    }

    public void setProgress(int mProgress) {
        this.mProgress = mProgress;
    }


    private void adjustIndeterminate() {
        try {
            Field mOnlyIndeterminateField = ProgressBar.class.getDeclaredField("mOnlyIndeterminate");
            mOnlyIndeterminateField.setAccessible(true);
            mOnlyIndeterminateField.set(this, false);

            Field mIndeterminateField = ProgressBar.class.getDeclaredField("mIndeterminate");
            mIndeterminateField.setAccessible(true);
            mIndeterminateField.set(this, false);

            Field mCurrentDrawableField = ProgressBar.class.getDeclaredField("mCurrentDrawable");
            mCurrentDrawableField.setAccessible(true);
            mCurrentDrawableField.set(this, null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
