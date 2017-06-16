package com.tcl.widget.demo.ui.widget.threestep.shader;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.NLog;

/**
 * Created by jerryliu on 2017/6/15.
 */

public class TelescopeView extends View {
    public static final String TAG = TelescopeView.class.getSimpleName();
    private Paint mPaint;
    private Bitmap mBmp;
    private Bitmap mBmpBg;
    public TelescopeView(Context context) {
        super(context,null);
        init();
    }

    public TelescopeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init();
    }

    public TelescopeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBmp = BitmapFactory.decodeResource(getResources(), R.drawable.scenery);
        mPaint.setShader(new BitmapShader(mBmp, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mBmpBg == null){
            mBmpBg = Bitmap.createBitmap(getWidth(),getHeight(), Bitmap.Config.ARGB_8888);
            Canvas bgCanvas = new Canvas(mBmpBg);
            //把mBmp绘制到mBmpBg
            bgCanvas.drawBitmap(mBmp,null,new Rect(0,0,getWidth(),getHeight()), mPaint);
        }
        mPaint.setShader(new BitmapShader(mBmpBg, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT));
        if (mDx != -1 && mDy != -1){
            canvas.drawCircle(mDx, mDy, mRadis,mPaint);
        }
    }

    int mDx = -1,mDy = -1;
    private int mRadis= 150;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDx = (int) event.getX();
                mDy = (int) event.getY();
                postInvalidate();
                NLog.d(TAG, "onTouchEvent ACTION_DOWN");
                return true;
            case MotionEvent.ACTION_MOVE:
                mDx = (int) event.getX();
                mDy = (int) event.getY();
                if (mDx > getWidth() - mRadis){
                    mDx = getWidth() - mRadis;
                }
                if (mDx < mRadis){
                    mDx = mRadis;
                }
                if (mDy >= getHeight() - mRadis){
                    mDy = getHeight() - mRadis;
                }
                if (mDy < mRadis){
                    mDy = mRadis;
                }
                NLog.d(TAG, "onTouchEvent ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mDx = -1;
                mDy = -1;
                break;
        }
        postInvalidate();
        return super.onTouchEvent(event);
    }
}
