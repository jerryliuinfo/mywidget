package com.tcl.widget.demo.ui.widget.hero;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;

/**
 * Created by jerryliu on 2017/4/22.
 */

public class CustomTextView extends android.support.v7.widget.AppCompatTextView {
    private Paint mOutRectPaint;
    private Paint mInnerRectPaint;

    public CustomTextView(Context context) {
        super(context,null);
        init(context,null);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init(context,attrs);
    }

    public CustomTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs){
        mOutRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOutRectPaint.setColor(ResUtil.getColor(R.color.green));

        mOutRectPaint.setStyle(Paint.Style.FILL);

        mInnerRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mInnerRectPaint.setColor(ResUtil.getColor(R.color.yellow));
        mInnerRectPaint.setStyle(Paint.Style.FILL);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRect(0,0,getMeasuredWidth(),getMeasuredHeight(), mOutRectPaint);

        canvas.drawRect(10,10,getMeasuredWidth() - 10, getMeasuredHeight() - 10, mInnerRectPaint);
        canvas.save();
        canvas.translate(10,0);
        super.onDraw(canvas);
        canvas.restore();

    }
}
