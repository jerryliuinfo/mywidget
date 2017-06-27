package com.tcl.widget.demo.ui.widget.path;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.ResUtil;
import com.tcl.widget.demo.uti.view.PaintConfigUtil;

/**
 * Created by jerryliu on 2017/6/27.
 */

public class DashPathEffectView extends View {
    private Paint mPaint;
    private Path mPath;
    private PathEffect mPathEffect;
    public DashPathEffectView(Context context) {
        super(context,null);
        init();
    }

    public DashPathEffectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public DashPathEffectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        PaintConfigUtil.configNormal(mPaint, ResUtil.getColor(R.color.red),ResUtil.dp2px(2), Paint.Style.STROKE);

        mPath = new Path();
        mPath.moveTo(200, 600);
        mPath.lineTo(800, 600);


    }

    int phase;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPathEffect = new DashPathEffect(new float[]{20,8,20,8}, phase);
        mPaint.setPathEffect(mPathEffect);
        phase++;
        canvas.drawPath(mPath,mPaint);
        invalidate();
    }
}
