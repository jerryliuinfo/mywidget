package com.tcl.widget.demo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.bean.PieBean;
import com.tcl.widget.demo.uti.NLog;

import java.util.ArrayList;


/**
 * Created by lenovo on 2016/9/16.
 */

public class PieWiew extends View {
    private static final String TAG = PieWiew.class.getSimpleName();
    // 颜色表 (注意: 此处定义颜色使用的是ARGB，带Alpha通道的)
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};
    private int mWidth, mHeight;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mStartAngel; //切饼图初始角度
    private ArrayList<PieBean> mDatas;

    public PieWiew(Context context) {
        super(context, null);
    }

    public PieWiew(Context context, AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public PieWiew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void init(Context context, AttributeSet attrs){

    }

    private void initPaint(){
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDatas == null || mDatas.size() == 0){
            return;
        }

        float currentStartAngel = mStartAngel;
        canvas.translate(mWidth / 2, mHeight / 2);
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);
        RectF rect = new RectF(-r, -r, r,r);
        for (int i = 0; i < mDatas.size(); i++){
            PieBean bean = mDatas.get(i);
            mPaint.setColor(bean.color);
            canvas.drawArc(rect,currentStartAngel, bean.angel, true, mPaint);
            currentStartAngel += bean.angel;
        }
    }

    public void setStartAngel(int startAngel){
        this.mStartAngel = startAngel;
        invalidate();
    }

    public void setData(ArrayList<PieBean> datas){
        this.mDatas = datas;
        initData(datas);
        invalidate();
    }


    private void initData(ArrayList<PieBean> datas){
        if (datas == null || datas.size() == 0){
            return;
        }
        int sumValue = 0;
        for (int i = 0; i < datas.size(); i++){
            PieBean bean = datas.get(i);
            sumValue += bean.value;
            bean.color = mColors[i % mColors.length];
        }
        for (int i = 0; i < datas.size(); i++){
            PieBean bean = datas.get(i);
            bean.percentage = bean.value / sumValue;
            bean.angel = bean.percentage * 360;
            NLog.d(TAG, "percentage = %f, angel = %f", bean.percentage, bean.angel);
        }

    }



}
