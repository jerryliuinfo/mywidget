package com.tcl.widget.demo.ui.widget.threestep;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;

/**
 * Created by jerryliu on 2017/6/13.
 */

public class BlurMaskFiterView extends View{
    private Paint mPaint;
    public BlurMaskFiterView(Context context) {
        super(context,null);
        init();
    }

    public BlurMaskFiterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public BlurMaskFiterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(ResUtil.getColor(R.color.red));
        mPaint.setMaskFilter(new BlurMaskFilter(50, BlurMaskFilter.Blur.SOLID));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(200,200,100, mPaint);
    }
}
