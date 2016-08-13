package com.tcl.widget.demo.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.Logger;

import java.text.DecimalFormat;

/**
 * @author xiangxiangliu
 * @Description:
 * @date 2016/6/3 17:07
 * @copyright TCL-MIG
 */
public class GlassView extends View{
    private static final String TAG = GlassView.class.getSimpleName();
    private float[] mRange = new float[]{0, 100};
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mCorner = 12;
    private String defClor = "#72e04c";
    private String[] colors = new String[]{"#72e04c","#cef135"};
    private RectF bgRect;
    private RectF txtRect;
    private RectF halfBottomRect;
    float mValue = 35.8f;

    private int textHeight = 40;
    private float mTextSize = 30;
    private Bitmap bitmapDivider;
    private PorterDuffXfermode porterDuffXfermode;
    private ValueAnimator animator;
    private ValueChangedListener valueChangedListener;
    private float dividerTop;
    private DecimalFormat decimalFormat;

    public GlassView(Context context) {
        super(context, null);
    }

    public GlassView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }
    private void init(AttributeSet attrs){

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int saveFlags = Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG
                | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG
                | Canvas.FULL_COLOR_LAYER_SAVE_FLAG
                | Canvas.CLIP_TO_LAYER_SAVE_FLAG;
        canvas.saveLayer(0, 0, getWidth(), getHeight(), null, saveFlags);
        if (bgRect == null){
            bgRect = new RectF(0, 0, getWidth(), getAvailableAreaHeight());
        }
        mPaint.setColor(Color.parseColor(colors == null? defClor: colors[0]));
        //画圆角矩形
        canvas.drawRoundRect(bgRect, mCorner,mCorner, mPaint);

        //给画文字定一个区域
        if (txtRect == null){
            txtRect = new RectF(0, getAvailableAreaHeight(), getWidth(), getHeight() - mCorner);
        }
        if (porterDuffXfermode == null){
            porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.CLEAR);
        }
        mPaint.setXfermode(porterDuffXfermode);
        mPaint.setColor(Color.GRAY);
        canvas.drawRect(txtRect, mPaint);
        //画完了文字区域后  重置xFremode
        mPaint.setXfermode(null);
        canvas.restore();


        //画文字
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(mTextSize);
        if (decimalFormat == null){
            decimalFormat = new DecimalFormat("#0.0");
        }
        String formatedValue = decimalFormat.format(mValue);
        float textWidth = mPaint.measureText(formatedValue);
        canvas.drawText(formatedValue, (getWidth() - textWidth) /2, getHeight() - textHeight /2, mPaint);

        //画横杆
        if (bitmapDivider == null){
            bitmapDivider = BitmapFactory.decodeResource(getResources(), R.mipmap.level_divider);
            bitmapDivider = zoomBitmap(bitmapDivider, (int)(getWidth() * 1.2));
        }

        if (mValue >= mRange[1]){
            dividerTop = mCorner;
        }else {
            dividerTop = (1 - mValue /(mRange[1] - mRange[0])) * getAvailableAreaHeight();
        }
        canvas.drawBitmap(bitmapDivider, 0,dividerTop, mPaint);

        //画横杠下面的区域
        if (halfBottomRect == null){
            halfBottomRect = new RectF(0, getAvailableAreaHeight(), getWidth(), getAvailableAreaHeight());
        }
        halfBottomRect.top = dividerTop + bitmapDivider.getHeight()/2 ;

        if (colors != null){
            mPaint.setColor(Color.parseColor(colors[1]));
        }
        canvas.drawRect(halfBottomRect, mPaint);
    }

    /**
     * 下部矩形的可画区域
     * @return
     */
    private int getAvailableAreaHeight(){
        return getHeight() - getUnavailableAreaHeight();
    }

    /**
     * 最底部的不可画区域
     * @return
     */
    private int getUnavailableAreaHeight(){
        return textHeight + mCorner;
    }


    public void setmRange(float[] mRange) {
        this.mRange = mRange;
    }

    public void setValue(float value) {
        Logger.d(TAG, "setValue value = "+value);
        postDelayed(new AnimRunable(value), 50);
    }

    public void setValueChangedListener(ValueChangedListener valueChangedListener) {
        this.valueChangedListener = valueChangedListener;
    }

    class AnimRunable implements Runnable{
        private float newValue;
        public AnimRunable(float value) {
            newValue = value;
        }

        @Override
        public void run() {
            if (animator != null){
                animator.cancel();
            }
            animator = ObjectAnimator.ofFloat(mValue, newValue);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    mValue = Float.parseFloat( animation.getAnimatedValue().toString());
                    if (valueChangedListener != null){
                        colors = valueChangedListener.onValueChanged(mValue);
                    }
                    invalidate();
                }
            });
            animator.setDuration(1000);
            animator.start();
        }
    }

    public interface ValueChangedListener{
        String[] onValueChanged(float newValue);
    }



    private Bitmap zoomBitmap(Bitmap source, int width){
        float scale = width * 1.0f / source.getWidth();
        Matrix matrix = new Matrix();
        matrix.setScale(scale,scale);
        Bitmap result = Bitmap.createBitmap(source,0,0,source.getWidth(),source.getHeight(),matrix,true);
        try {
            source.recycle();
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

}
