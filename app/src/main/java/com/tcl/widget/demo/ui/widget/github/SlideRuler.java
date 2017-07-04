package com.tcl.widget.demo.ui.widget.github;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;
import com.tcl.widget.demo.uti.ScreenUtil;
import com.tcl.widget.demo.uti.view.MeasureUtil;
import com.tcl.widget.demo.uti.view.PaintConfigUtil;

/**
 * Created by jerryliu on 2017/7/3.
 */

public class SlideRuler extends View {
    //长刻度的长度
    private int longCursor = ResUtil.dp2px(25);
    //短刻度的长度
    private int shortCursor = ResUtil.dp2px(15);

    private Paint mLongCursorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mShortCursorPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private TextPaint mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);



    public SlideRuler(Context context) {
        this(context,null);
    }

    public SlideRuler(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,-1);
    }

    public SlideRuler(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs){
        PaintConfigUtil.configStrokePaint(mLongCursorPaint,ResUtil.getColor(R.color.gray),ResUtil.dp2px(1));
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int defaultWidth = ScreenUtil.getScreenWidth(getContext());
        int defaultHeight = getPaddingBottom() + getPaddingTop() + ScreenUtil.getScreenHeight(getContext()) / 4;
        setMeasuredDimension(MeasureUtil.getMeasuredLength(widthMeasureSpec,defaultWidth),MeasureUtil.getMeasuredLength(heightMeasureSpec,defaultHeight));
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
