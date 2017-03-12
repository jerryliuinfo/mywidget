package com.tcl.widget.demo.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.NLog;
import com.tcl.widget.demo.uti.ResUtil;
import com.tcl.widget.demo.uti.UnitUtils;

import java.util.Calendar;

/**
 * Created by jerry on 2017/3/6.
 */

public class MiClockView extends View {
    private static final String TAG = MiClockView.class.getSimpleName();

    //弧度宽度
    int mCircleStrokeWidth;
    private Paint mCirclePaint;
    //弧度
    private RectF mCircleRectF;

    /* 刻度圆弧画笔 */
    private Paint mScaleArcPaint;
    /* 刻度圆弧的外接矩形 */
    private RectF mScaleArcRectF;
    /* 刻度线画笔 */
    private Paint mScaleLinePaint;

    /* 亮色，用于分针、秒针、渐变终止色 */
    private int mLightColor;
    /* 暗色，圆弧、刻度线、时针、渐变起始色 */
    private int mDarkColor;

    //小时
    Rect mTextRect;
    private Paint mTextPaint;


    private float mDefaultPadding;
    private float mPaddingLeft,mPaddingRight,mPaddingTop,mPaddingBottom;

    /* 时针画笔 */
    private Paint mHourHandPaint;
    /* 分针画笔 */
    private Paint mMinuteHandPaint;
    /* 秒针画笔 */
    private Paint mSecondHandPaint;
    /* 时针路径 */
    private Path mHourHandPath;
    /* 分针路径 */
    private Path mMinuteHandPath;
    /* 秒针路径 */
    private Path mSecondHandPath;


    public MiClockView(Context context) {
        super(context);
        init(context,null);
    }

    public MiClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public MiClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    private void init(Context context, AttributeSet attrs){

        mDarkColor = getResources().getColor(R.color.miclock_dark);
        mLightColor = getResources().getColor(R.color.miclock_light);


        mHourHandPath = new Path();
        mMinuteHandPath = new Path();
        mSecondHandPath = new Path();

        mHourHandPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHourHandPaint.setStyle(Paint.Style.FILL);
        mHourHandPaint.setColor(mDarkColor);

        mMinuteHandPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMinuteHandPaint.setStyle(Paint.Style.FILL);
        mMinuteHandPaint.setStrokeWidth(ResUtil.dip2px(4));
        mMinuteHandPaint.setColor(mLightColor);



        mSecondHandPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSecondHandPaint.setStyle(Paint.Style.FILL);
        mSecondHandPaint.setColor(mLightColor);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStrokeWidth(UnitUtils.dip2px(context,3));
        mTextPaint.setTextSize(UnitUtils.sp2px(context,14));

        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCircleStrokeWidth = UnitUtils.dip2px(context,3);
        mCirclePaint.setStrokeWidth(mCircleStrokeWidth);
        mCirclePaint.setTextSize(UnitUtils.sp2px(context,14));
        mCirclePaint.setColor(ResUtil.getColor(R.color.green));

        mTextRect = new Rect();
        mCircleRectF = new RectF();
        mScaleArcRectF = new RectF();




        mScaleArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mScaleArcPaint.setStyle(Paint.Style.STROKE);

        mScaleLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mScaleLinePaint.setStyle(Paint.Style.STROKE);
        mScaleLinePaint.setColor(mDarkColor);



    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    int mViewWidth,mViewHeight;
    int mRadius;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
        NLog.d(TAG, "onSizeChanged w = %s, h = %s, padingLef = %s,padingTop = %s", w,h, getPaddingLeft(),getPaddingBottom());

        mRadius = Math.min(mViewWidth - getPaddingLeft() - getPaddingRight(),mViewHeight - getPaddingTop()- getPaddingBottom()) / 2;

        mDefaultPadding = 0.12f * mRadius;
        mPaddingLeft = mDefaultPadding + getPaddingLeft() + mViewWidth / 2 - mRadius;
        mPaddingRight = mPaddingLeft;

        mPaddingTop = mDefaultPadding + getPaddingTop() + mViewHeight / 2 - mRadius;
        mPaddingBottom = mPaddingTop;
        NLog.d(TAG, "mPaddingLeft = %s, mPaddingTop = %s", mPaddingLeft,mPaddingTop);

        mScaleLength = 0.12f * mRadius;//根据比例确定刻度线长度
        mScaleArcPaint.setStrokeWidth(mScaleLength);

        mScaleLinePaint.setStrokeWidth(0.012f * mRadius);
        //mMaxCanvasTranslate = 0.02f * mRadius;
        //梯度扫描渐变，以(w/2,h/2)为中心点，两种起止颜色梯度渐变
        //float数组表示，[0,0.75)为起始颜色所占比例，[0.75,1}为起止颜色渐变所占比例
        mSweepGradient = new SweepGradient(w / 2, h / 2,
                new int[]{mDarkColor, mLightColor}, new float[]{0.75f, 1});

        mGradientMatrix = new Matrix();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mCanvas = canvas;
        drawText();
        getCurrentTime();
        drawScaleLine();

        drawHourHand();
        drawMinuteHand();
        drawSecondHand();
        invalidate();
    }


    private static final int  GAP_DEGRESS = 5;//时针与旁边线的间隔角度
    private void drawText(){
        NLog.d(TAG, "drawText getWidth() = %s, getHeight() = %s", getWidth(),getHeight());
        String timeText = "12";
        mTextPaint.getTextBounds(timeText, 0, timeText.length(), mTextRect);
        int textLargeWidth = mTextRect.width();//两位数字的宽
        mCanvas.drawText("12", getWidth() / 2 - textLargeWidth / 2, mPaddingTop + mTextRect.height(), mTextPaint);
        timeText = "3";
        mTextPaint.getTextBounds(timeText, 0, timeText.length(), mTextRect);
        int textSmallWidth = mTextRect.width();//一位数字的宽
        mCanvas.drawText("3", getWidth() - mPaddingRight - mTextRect.height() / 2 - textSmallWidth / 2,
                getHeight() / 2 + mTextRect.height() / 2, mTextPaint);
        mCanvas.drawText("6", getWidth() / 2 - textSmallWidth / 2, getHeight() - mPaddingBottom, mTextPaint);
        mCanvas.drawText("9", mPaddingLeft + mTextRect.height() / 2 - textSmallWidth / 2,
                getHeight() / 2 + mTextRect.height() / 2, mTextPaint);

        //画4个弧
        mCircleRectF.set(mPaddingLeft + mTextRect.height() / 2 + mCircleStrokeWidth / 2,
                mPaddingTop + mTextRect.height() / 2 + mCircleStrokeWidth / 2,
                getWidth() - mPaddingRight - mTextRect.height() / 2 + mCircleStrokeWidth / 2,
                getHeight() - mPaddingBottom - mTextRect.height() / 2 + mCircleStrokeWidth / 2);
         int sweepAngle = (360 - GAP_DEGRESS * 2 * 4 ) / 4;
        for (int i = 0; i < 4; i++) {
            mCanvas.drawArc(mCircleRectF, GAP_DEGRESS + 90 * i, sweepAngle, false, mCirclePaint);
        }
    }





    /* 刻度线长度 */
    private float mScaleLength;
    /* 梯度扫描渐变 */
    private SweepGradient mSweepGradient;
    /* 渐变矩阵，作用在SweepGradient */
    private Matrix mGradientMatrix;
    private Canvas mCanvas;


    private static final int LINE_COUNT = 200;
    /**
     * 画一圈梯度渲染的亮暗色渐变圆弧，重绘时不断旋转，上面盖一圈背景色的刻度线
     */
    private void drawScaleLine() {
        mScaleArcRectF.set(mPaddingLeft + 1.5f * mScaleLength + mTextRect.height() / 2,
                mPaddingTop + 1.5f * mScaleLength + mTextRect.height() / 2,
                getWidth() - mPaddingRight - mTextRect.height() / 2 - 1.5f * mScaleLength,
                getHeight() - mPaddingBottom - mTextRect.height() / 2 - 1.5f * mScaleLength);

        //matrix默认会在三点钟方向开始颜色的渐变，为了吻合
        //钟表十二点钟顺时针旋转的方向，把秒针旋转的角度减去90度
        mGradientMatrix.setRotate(mSecondDegree - 90, getWidth() / 2, getHeight() / 2);
        mSweepGradient.setLocalMatrix(mGradientMatrix);
        mScaleArcPaint.setShader(mSweepGradient);
        mCanvas.drawArc(mScaleArcRectF, 0, 360, false, mScaleArcPaint);
        //画背景色刻度线
        mCanvas.save();



        for (int i = 0; i < 200; i++) {
            mCanvas.drawLine(getWidth() / 2, mPaddingTop + mScaleLength + mTextRect.height() / 2,
                    getWidth() / 2, mPaddingTop + 2 * mScaleLength + mTextRect.height() / 2, mScaleLinePaint);
            mCanvas.rotate(1.8f, getWidth() / 2, getHeight() / 2);
        }
        mCanvas.restore();
    }
    private float mSecondDegree,mMinuteDegress,mHourDegress;

    private void getCurrentTime(){
        Calendar calendar = Calendar.getInstance();
        float second = calendar.get(Calendar.SECOND);
        float minute = calendar.get(Calendar.MINUTE) + second / 60;
        float hour = calendar.get(Calendar.HOUR) + minute / 60;

        mSecondDegree = (second / 60) * 360;
        mMinuteDegress = (minute / 60) * 360;
        mHourDegress = (hour / 12)* 360;
    }

    /**
     * 画时针，根据不断变化的时针角度旋转画布
     * 针头为圆弧状，使用二阶贝塞尔曲线
     */
    private void drawHourHand() {
        mCanvas.save();
        //mCanvas.translate(mCanvasTranslateX * 1.2f, mCanvasTranslateY * 1.2f);
        mCanvas.rotate(mHourDegress, getWidth() / 2, getHeight() / 2);
        mHourHandPath.reset();
        float offset = mPaddingTop + mTextRect.height() / 2;
        //point 1
        mHourHandPath.moveTo(getWidth() / 2 - 0.018f * mRadius, getHeight() / 2 - 0.03f * mRadius);
        float endY = offset + 0.48f * mRadius;
        //point 2
        mHourHandPath.lineTo(getWidth() / 2 - 0.009f * mRadius, endY);
        //point 3(getWidth() / 2 + 0.009f * mRadius, offset + 0.48f * mRadius) 将1和3用贝塞尔曲线连接起来 控制点为(getWidth() / 2, offset + 0.46f * mRadius)
        float controlPointX = getWidth() / 2;
        float controlPointY = offset + 0.46f * mRadius;
        mHourHandPath.quadTo(controlPointX, controlPointY,
                getWidth() / 2 + 0.009f * mRadius, offset + 0.48f * mRadius);
        //point 4
        mHourHandPath.lineTo(getWidth() / 2 + 0.018f * mRadius, getHeight() / 2 - 0.03f * mRadius);
        mHourHandPath.close();
        mHourHandPaint.setStyle(Paint.Style.FILL);
        mCanvas.drawPath(mHourHandPath, mHourHandPaint);

        //画小圆圈
        mCircleRectF.set(getWidth() / 2 - 0.03f * mRadius, getHeight() / 2 - 0.03f * mRadius,
                getWidth() / 2 + 0.03f * mRadius, getHeight() / 2 + 0.03f * mRadius);
        mHourHandPaint.setStyle(Paint.Style.STROKE);
        mHourHandPaint.setStrokeWidth(0.01f * mRadius);
        mCanvas.drawArc(mCircleRectF, 0, 360, false, mHourHandPaint);
        mCanvas.restore();
    }


    /**
     * 画分针，根据不断变化的分针角度旋转画布
     */
    private void drawMinuteHand() {
        mCanvas.save();
        mCanvas.rotate(mMinuteDegress, getWidth() / 2 , getHeight() / 2 );
        mMinuteHandPath.moveTo(getWidth() / 2 , getHeight() / 2 - 0.03f * mRadius);

        float offset = mPaddingTop + mTextRect.height() / 2;


        mMinuteHandPaint.setStyle(Paint.Style.STROKE);
        mMinuteHandPath.lineTo(getWidth() / 2 - 0.02f * mRadius, offset + 0.365f * mRadius);
        mCanvas.drawPath(mMinuteHandPath,mMinuteHandPaint);

        //画小圆圈
        mCircleRectF.set(getWidth() / 2 - 0.03f * mRadius, getHeight() / 2 - 0.03f * mRadius,
                getWidth() / 2 + 0.03f * mRadius, getHeight() / 2 + 0.03f * mRadius);
        mMinuteHandPaint.setStrokeWidth(0.02f * mRadius);
        mCanvas.drawArc(mCircleRectF, 0, 360, false, mMinuteHandPaint);
        mCanvas.restore();
    }


    private void drawSecondHand(){
        mCanvas.save();
        //mCanvas.translate(mCanvasTranslateX, mCanvasTranslateY);
        mCanvas.rotate(mSecondDegree, getWidth() / 2, getHeight() / 2);
        mSecondHandPath.reset();
        float offset = mPaddingTop + mTextRect.height() / 2;
        mSecondHandPath.moveTo(getWidth() / 2, offset + 0.26f * mRadius);
        mSecondHandPath.lineTo(getWidth() / 2 - 0.05f * mRadius, offset + 0.34f * mRadius);
        mSecondHandPath.lineTo(getWidth() / 2 + 0.05f * mRadius, offset + 0.34f * mRadius);
        mSecondHandPath.close();
        mSecondHandPaint.setColor(mLightColor);
        mCanvas.drawPath(mSecondHandPath, mSecondHandPaint);
        mCanvas.restore();
    }

}
