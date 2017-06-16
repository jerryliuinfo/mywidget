package com.tcl.widget.demo.ui.widget.threestep;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;

/**
 * Created by jerryliu on 2017/6/13.
 */

public class ExtraAlphaView extends View {
    private Paint mPaint;
    private Bitmap mOrigBmp;
    private Bitmap mAlphaBmp;
    public ExtraAlphaView(Context context) {
        super(context,null);
        init();
    }

    public ExtraAlphaView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public ExtraAlphaView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        setLayerType(LAYER_TYPE_SOFTWARE,null);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOrigBmp = BitmapFactory.decodeResource(getResources(), R.drawable.blog12);

        mAlphaBmp = mOrigBmp.extractAlpha();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = 200;
        int height = width * mOrigBmp.getHeight() / mOrigBmp.getWidth();

        int offset = 10;
        mPaint.setMaskFilter(new BlurMaskFilter(10, BlurMaskFilter.Blur.NORMAL));

        mPaint.setColor(ResUtil.getColor(R.color.green));
        canvas.drawBitmap(mAlphaBmp,null,new RectF(offset, offset,width+offset,height +offset),mPaint);


        mPaint.setMaskFilter(null);
        canvas.drawBitmap(mOrigBmp,null,new Rect(0,0,width,height),mPaint);

    }
}
