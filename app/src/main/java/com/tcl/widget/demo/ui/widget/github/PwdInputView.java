package com.tcl.widget.demo.ui.widget.github;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.InputFilter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.EditText;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;
import com.tcl.widget.demo.uti.ScreenUtil;
import com.tcl.widget.demo.uti.view.MeasureUtil;
import com.tcl.widget.demo.uti.view.PaintConfigUtil;

/**
 * Created by jerryliu on 2017/6/28.
 */

public class PwdInputView extends EditText {

    private RectF mRectF;
    private Paint mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mDividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private float mRadis = 10;


    /**
     * 分割线开始的坐标x
     */
    private int divideLineWStartX;

    private int mCircleStartX;
    private int mCircleStartY;


    private int roundAgel = 4;
    private int maxCount = 6;

    private int mSingelRectWidth;//单个输入框的宽度
    private int mTxtCount;

    private PwdCallback mCallback;

    public PwdInputView(Context context) {
        super(context,null);
        init();
    }

    public PwdInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PwdInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        PaintConfigUtil.configStrokePaint(mBorderPaint, ResUtil.getColor(R.color.gray),ResUtil.dp2px(2));
        PaintConfigUtil.configStrokePaint(mDividerPaint, ResUtil.getColor(R.color.gray),ResUtil.dp2px(1));
        PaintConfigUtil.configFillPaint(mCirclePaint,ResUtil.getColor(R.color.black));

        mRectF = new RectF();

        this.setBackgroundColor(Color.TRANSPARENT);
        this.setCursorVisible(false);
        this.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxCount)});
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureUtil.getMeasuredLength(widthMeasureSpec, ScreenUtil.getScreenWidth(getContext())),
                MeasureUtil.getMeasuredLength(widthMeasureSpec / 6, ScreenUtil.getScreenWidth(getContext()) / 6));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mRectF.set(0,0,getMeasuredWidth(),getMeasuredHeight());

        mSingelRectWidth = getMeasuredWidth() / maxCount;
        divideLineWStartX = getMeasuredWidth() / maxCount;

        mCircleStartX = getMeasuredWidth() / maxCount / 2;
        mCircleStartY = getMeasuredHeight() / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //不删除的画会默认绘制输入的文字
        //super.onDraw(canvas);
        drawRouncRect(canvas);
        drawSmallCircle(canvas);
    }

    private void drawRouncRect(Canvas canvas){
        canvas.drawRoundRect(mRectF, roundAgel,roundAgel,mBorderPaint);
        //6个密码输入框 需要画出的分割线数目是5
        for (int i = 0; i < maxCount - 1; i++){
            canvas.drawLine(
                    (i + 1) * divideLineWStartX,
                    0, (i + 1) * divideLineWStartX, getMeasuredHeight(), mBorderPaint);
        }
    }


    private void drawSmallCircle(Canvas canvas){
        for (int i = 0; i < mTxtCount; i++){
            canvas.drawCircle(mCircleStartX + i * mSingelRectWidth,mCircleStartY,mRadis, mCirclePaint);
        }
    }

    private String rightPwd = "123456";
    private String inputPwd;

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (!TextUtils.isEmpty(text)){
            mTxtCount = text.length();
            inputPwd = getInputPwd();
            if (inputPwd.length() == maxCount){
                if (mCallback != null){
                    if (inputPwd.equals(rightPwd)){
                        mCallback.onEquals(inputPwd);
                    }else {
                        mCallback.onDifference(inputPwd);
                    }
                }
            }
            if (mCallback != null){
                mCallback.onProgress(inputPwd);
            }
        }else {
            mTxtCount = 0;
        }
        invalidate();
    }

    @Override
    protected void onSelectionChanged(int selStart, int selEnd) {
        super.onSelectionChanged(selStart, selEnd);
        //保证光标始终在最后
        if (selStart == selEnd) {
            setSelection(getText().length());
        }
    }

    public String getInputPwd(){
        String text = getText().toString();
        return text != null? text:"";
    }

    public void setCallback(PwdCallback mCallback) {
        this.mCallback = mCallback;
    }

    public interface PwdCallback{
        void onProgress(String pwd);
        void onEquals(String pwd);
        void onDifference(String pwd);
    }

    public void setRightPwd(String rightPwd) {
        this.rightPwd = rightPwd;
    }
}
