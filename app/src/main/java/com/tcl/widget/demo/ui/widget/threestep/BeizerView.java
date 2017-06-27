package com.tcl.widget.demo.ui.widget.threestep;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;

/**
 * Created by jerryliu on 2017/5/29.
 */

public class BeizerView extends View {
    private Paint mPaint;
    private Path mPath;
    public BeizerView(Context context) {
        super(context,null);
        init();
    }

    public BeizerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public BeizerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(ResUtil.dp2px(3));
        mPaint.setColor(ResUtil.getColor(R.color.green));

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.moveTo(100,300);
        mPath.quadTo(200,200,300,300);
        mPath.quadTo(400,400,500,300);

        canvas.drawPath(mPath,mPaint);

    }
}
