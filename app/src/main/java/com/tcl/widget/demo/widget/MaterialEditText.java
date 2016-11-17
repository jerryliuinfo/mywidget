package com.tcl.widget.demo.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.view.util.*;

/**
 * Created by lenovo on 2016/11/17.
 */

public class MaterialEditText  extends EditText {
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
    private int maxLength;
    private Paint labelPaint;
    private Paint textPaint;
    private Paint underLinePaint;

    private int currentCount;

    private int extraPaddingTop;
    private int extraPaddingBottom;




    private int DEFAULT_COLOR = Color.parseColor("#ff00ee00");
    private void init(Context context, AttributeSet attrs){
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MaterialEditText);
        focusedLineColor = a.getColor(R.styleable.MaterialEditText_focusedLineColor, 0xee18b4ed);
        labelColor = a.getColor(R.styleable.MaterialEditText_labelColor, 0xff18b4ed);
        overLengthColor = a.getColor(R.styleable.MaterialEditText_overLengthColor, Color.RED);

        labelText = a.getString(R.styleable.MaterialEditText_labelText);
        maxLength = a.getInt(R.styleable.MaterialEditText_maxLength,50);
        a.recycle();

        labelPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        labelPaint.setTextSize(DisplayUtil.sp2px(context,12));

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(DisplayUtil.sp2px(context,10));

        underLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        underLinePaint.setTextSize(DisplayUtil.sp2px(context,1));


        extraPaddingTop = (int) (ViewUtil.getTextHeight(labelPaint) + DisplayUtil.dp2px(context,4));
        extraPaddingBottom = (int) (ViewUtil.getTextHeight(labelPaint) + DisplayUtil.dp2px(context,6));

        correctPaddings();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //draw label
        //canvas.drawText(labelText, 0,0, textPaint);
        canvas.drawText("哈哈", 0,0, labelPaint);

        //draw under line

    }

    private void correctPaddings(){
        super.setPadding(getPaddingLeft(), getPaddingTop() + extraPaddingTop,
                getPaddingRight(), getPaddingBottom() + extraPaddingBottom);
    }

}
