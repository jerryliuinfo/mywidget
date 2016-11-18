package com.tcl.widget.demo.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.NLog;
import com.tcl.widget.demo.uti.SystemUtility;

/**
 * @author Jerry
 * @Description:
 * @date 2016/10/15 10:28
 * @copyright TCL-MIG
 */

public class PathFillTypeView extends View {
    private static final String TAG = PathFillTypeView.class.getSimpleName();
    private Paint mPaint;
    private Paint mTextPaint;


    public PathFillTypeView(Context context) {
        super(context);
        init();
    }

    public PathFillTypeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public PathFillTypeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(getResources().getColor(android.R.color.black));
        NLog.d(TAG, "screen width = %d, screen height = %d", SystemUtility.getScreenWidth(), SystemUtility.getScreenHeight());

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setStyle(Paint.Style.STROKE);
        mTextPaint.setTextSize(getResources().getDimensionPixelSize(R.dimen.sp_12));
        mTextPaint.setColor(getResources().getColor(R.color.red));
    }

    private int mWiewWdith, mViewHeight;
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        NLog.d(TAG, "onSizeChanged w = %d, h = %d", w, h);
        mWiewWdith = w;
        mViewHeight = h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);

        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        NLog.d(TAG, "onMeasure widthMode = %d, widthSize = %d, heightMode = %d, heightSize = %d", widthMode,widthSize,heightMode,heightSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mWiewWdith / 2, mViewHeight /2);
        /*Path path = new Path();

        //path.setFillType(Path.FillType.EVEN_ODD);
        path.setFillType(Path.FillType.INVERSE_EVEN_ODD);
        path.addRect(-100,-100,100,100, Path.Direction.CW);


        canvas.drawPath(path, mPaint);*/

        Path path1 = new Path();
        Path path2 = new Path();
        Path path3 = new Path();
        Path path4 = new Path();

        path1.addCircle(0, 0, 200, Path.Direction.CW);
        path2.addRect(0, -200, 200, 200, Path.Direction.CW);
        path3.addCircle(0, -100, 100, Path.Direction.CW);
        path4.addCircle(0, 100, 100, Path.Direction.CW);


        path1.op(path2, Path.Op.DIFFERENCE);//Path1 - (Path1 与 Path2相交部分)
        path1.op(path3, Path.Op.UNION);
        path1.op(path4, Path.Op.DIFFERENCE);
        canvas.drawPath(path1, mPaint);

        Path path5 = new Path();
        path5.addCircle(0,200, 100, Path.Direction.CW);
        canvas.drawPath(path5, mTextPaint);

        canvas.drawTextOnPath("哈哈 我是Jerry", path5,0, -20, mTextPaint);


    }
}
