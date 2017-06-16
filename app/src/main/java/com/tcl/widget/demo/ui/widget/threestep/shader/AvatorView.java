package com.tcl.widget.demo.ui.widget.threestep.shader;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.NLog;
import com.tcl.widget.demo.uti.ResUtil;
import com.tcl.widget.demo.uti.view.MeasureUtil;

/**
 * Created by jerryliu on 2017/6/15.
 */

public class AvatorView extends View {
    public static final String TAG = AvatorView.class.getSimpleName();
    private Paint mPaint;
    private Paint mBorderPaint;
    private Bitmap mBitmap;
    private BitmapShader mBitmapShader;
    private Matrix matrix;
    private int mStyle = STYPE_CIRCLE;
    public static final int STYPE_CIRCLE = 0;
    public static final int STYPE_RECT = 1;
    private int mRadis = 5;
    private int mBorderWidth;


    public AvatorView(Context context) {
        super(context,null);
        init(context,null);
    }

    public AvatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init(context,attrs);
    }

    public AvatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.AvatorView);
        try {
            mStyle = typedArray.getInt(R.styleable.AvatorView_format,0);
        }finally {
            typedArray.recycle();
        }

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

        mBorderWidth = ResUtil.dip2px(2);


        mBorderPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBorderPaint.setStyle(Paint.Style.STROKE);
        mBorderPaint.setStrokeWidth(mBorderWidth);
        mBorderPaint.setColor(ResUtil.getColor(R.color.red));

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.avator);

        mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        matrix = new Matrix();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(MeasureUtil.getMeasuredLength(widthMeasureSpec,mBitmap.getWidth()),MeasureUtil.getMeasuredLength(heightMeasureSpec,mBitmap.getHeight()));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float scale = (float) getWidth() / mBitmap.getWidth();
        NLog.d(TAG, "getWidth = %s, bitmap width = %s, scale = %s", getWidth(), mBitmap.getWidth(),scale);
        matrix.setScale(scale,scale);
        mBitmapShader.setLocalMatrix(matrix);
        mPaint.setShader(mBitmapShader);

        if (mStyle == STYPE_CIRCLE){
            canvas.drawCircle(getWidth() / 2, getHeight() /2, (getWidth() - mBorderWidth * 2) / 2, mPaint);
            canvas.drawCircle(getWidth() / 2, getHeight() / 2,(getWidth() - mBorderPaint.getStrokeWidth()) / 2, mBorderPaint);
        }else if (mStyle == STYPE_RECT){
            canvas.drawRoundRect(new RectF(0,0,getWidth(),getHeight()), mRadis,mRadis,mPaint);
        }

    }
}
