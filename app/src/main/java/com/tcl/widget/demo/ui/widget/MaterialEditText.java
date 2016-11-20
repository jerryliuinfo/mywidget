package com.tcl.widget.demo.ui.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.text.Editable;
import android.util.AttributeSet;
import android.widget.EditText;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.DisplayUtil;
import com.tcl.widget.demo.uti.NLog;
import com.tcl.widget.demo.uti.ViewUtil;

/**
 * Created by lenovo on 2016/11/17.
 */

public class MaterialEditText  extends EditText {
    private static final String TAG = MaterialEditText.class.getSimpleName();

    public MaterialEditText(Context context) {
        super(context);
        init(context, null);
    }


    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private int focusedLineColor;
    private int labelColor;
    private int overLengthColor;
    private String labelText;

    private Paint labelPaint;
    private Paint textPaint;
    private Paint underLinePaint;
    private Paint countPaint;

    private int maxCount = -1;//最大允许输入字符数
    private int currentCount;//当前输入字符数
    private StringBuffer countStringBuffer;
    private boolean isLabelShow = false;


    private int extraPaddingTop;
    private int extraPaddingBottom;

    private int lineStartY;//下划线的起始Y坐标
    private ValueAnimator labelAnimator;
    private int labelAlpha;
    private float fraction;



    private int DEFAULT_COLOR = Color.parseColor("#ff00ee00");
    public static final int TEXT_SIZE_SP = 16;
    private void init(Context context, AttributeSet attrs){
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText);
        focusedLineColor = a.getColor(R.styleable.MaterialEditText_focusedLineColor, 0xee18b4ed);
        labelColor = a.getColor(R.styleable.MaterialEditText_labelColor, 0xff18b4ed);
        overLengthColor = a.getColor(R.styleable.MaterialEditText_overLengthColor, Color.RED);

        labelText = a.getString(R.styleable.MaterialEditText_labelText);
        maxCount = a.getInt(R.styleable.MaterialEditText_maxLength,15);
        a.recycle();

        countStringBuffer = new StringBuffer();

        labelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        labelPaint.setTextSize(DisplayUtil.sp2px(TEXT_SIZE_SP));

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(DisplayUtil.sp2px(TEXT_SIZE_SP));

        underLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        underLinePaint.setStrokeWidth(DisplayUtil.sp2px(2));
        underLinePaint.setColor(Color.RED);

        countPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        countPaint.setTextSize(DisplayUtil.sp2px(TEXT_SIZE_SP));
        countPaint.setColor(Color.RED);


        extraPaddingTop = (int) (ViewUtil.getTextHeight(labelPaint) + DisplayUtil.dp2px(4));
        extraPaddingBottom = (int) (ViewUtil.getTextHeight(labelPaint) + DisplayUtil.dp2px(6));

        correctPaddings();

        labelAnimator = ValueAnimator.ofInt(0,255);
        labelAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                labelAlpha = (int) animation.getAnimatedValue();
                fraction = (float) (-(5.0f / 2550.0f) * labelAlpha + 1.5);
                NLog.d(TAG, "labelAlpha = %d, fraction = %f", labelAlpha,fraction);
                invalidate();

            }
        });
        initListener();
    }

    private void initListener(){
        addTextChangedListener(new MyTextWatchListener(){
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (maxCount != -1){
                    countStringBuffer.delete(0, countStringBuffer.length());
                    currentCount = s.length();
                    countStringBuffer.append(currentCount).append(" / ").append(maxCount);
                    if (currentCount <= maxCount){
                        countPaint.setColor(Color.LTGRAY);
                    }else {
                        countPaint.setColor(Color.RED);
                    }
                    if (currentCount == 0 && isLabelShow && isFocused()){
                        labelAnimator.reverse();
                        isLabelShow = false;
                    }else if (currentCount != 0 && !isLabelShow && isFocused()){
                        labelAnimator.start();
                        isLabelShow = true;
                    }
                    invalidate();
                }
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //draw label
        float y = (getScrollY() + extraPaddingTop - DisplayUtil.dp2px(4)) * fraction;
        NLog.d(TAG, "onDraw fraction = %f, y = %f,labelAlpha = %d", fraction, y, labelAlpha);
        canvas.drawText("哈哈", getScrollX(),y, labelPaint);
        // 设置标签的透明度
        labelPaint.setAlpha(labelAlpha);


        lineStartY = getScrollY() + getHeight() - getPaddingBottom() + DisplayUtil.dp2px(7);

        //draw under line
        canvas.drawLine(0,lineStartY, getWidth() - getPaddingRight(),lineStartY, underLinePaint);

        //draw current text count
        if (maxCount != -1){
            float x = getScrollX() + getWidth() - getPaddingRight() - countPaint.measureText(countStringBuffer.toString());
            canvas.drawText(countStringBuffer.toString(), x, lineStartY + extraPaddingBottom, countPaint);
        }
        super.onDraw(canvas);
    }

    private void correctPaddings(){
        super.setPadding(getPaddingLeft(), getPaddingTop() + extraPaddingTop,
                getPaddingRight(), getPaddingBottom() + extraPaddingBottom);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        NLog.d(TAG, "onSizeChanged w = %d, h = %d", w,h);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN){
            setBackground(null);
        }else {
            setBackgroundDrawable(null);
        }
    }
}
