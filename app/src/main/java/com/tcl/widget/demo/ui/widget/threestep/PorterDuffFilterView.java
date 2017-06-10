package com.tcl.widget.demo.ui.widget.threestep;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;

/**
 * Created by jerryliu on 2017/6/4.
 */

public class PorterDuffFilterView extends android.support.v7.widget.AppCompatImageView {
    private Bitmap mBmp;
    private Paint mPaint;

    public PorterDuffFilterView(Context context) {
        super(context,null );
        init();
    }

    public PorterDuffFilterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public PorterDuffFilterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(ResUtil.getColor(R.color.green));

        mBmp = BitmapFactory.decodeResource(getResources(), R.drawable.hawk);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = 100;
        int height = width * mBmp.getHeight() / mBmp.getWidth();
        canvas.drawBitmap(mBmp,null,new RectF(0,0,width,height),mPaint);


        canvas.translate(150,0);
        mPaint.setColorFilter(new PorterDuffColorFilter(0xffff00ff, PorterDuff.Mode.SRC));
        canvas.drawBitmap(mBmp,null,new Rect(0,0,width,height),mPaint);

        canvas.translate(150,0);
        mPaint.setColorFilter(new PorterDuffColorFilter(0xff00f0ff, PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(mBmp,null,new Rect(0,0,width,height),mPaint);

        canvas.translate(150,0);
        mPaint.setColorFilter(new PorterDuffColorFilter(0xfff0f0ff, PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(mBmp,null,new Rect(0,0,width,height),mPaint);

        canvas.translate(150,0);
        mPaint.setColorFilter(new PorterDuffColorFilter(0xffffff00, PorterDuff.Mode.SRC_OVER));
        canvas.drawBitmap(mBmp,null,new Rect(0,0,width,height),mPaint);


        canvas.translate(150,0);
        mPaint.setColorFilter(new PorterDuffColorFilter(0xff000000, PorterDuff.Mode.SRC_ATOP));
        canvas.drawBitmap(mBmp,null,new Rect(0,0,width,height),mPaint);
    }

}
