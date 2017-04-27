package com.tcl.widget.demo.ui.widget.hero;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.uti.view.ViewUtil;

/**
 * Created by jerryliu on 2017/4/22.
 */

public class ViewMeasure extends View {
    public ViewMeasure(Context context) {
        super(context);
    }

    public ViewMeasure(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ViewMeasure(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(ViewUtil.measureView(widthMeasureSpec,200),ViewUtil.measureView(heightMeasureSpec,300));
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }


    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }


}
