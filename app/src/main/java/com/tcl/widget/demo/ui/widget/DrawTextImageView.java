package com.tcl.widget.demo.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.NLog;

/**
 * @author Jerry
 * @Description:
 * @date 2016/12/28 19:16
 * @copyright TCL-MIG
 */

public class DrawTextImageView extends ImageView {

    private static final String TAG = DrawTextImageView.class.getSimpleName();

    public DrawTextImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public DrawTextImageView (Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DrawTextImageView (Context context) {
        super(context);
        init();
    }

    /**
     * 文字
     */
    private String textString = "";

    /**
     * 文字大小
     */
    private float textSize = 30;

    /**
     * 文字位置x
     */
    private float x = -1000;

    /**
     * 文字位置y
     */
    private float y = -1000;

    /**
     * 文字颜色
     */
    private int textColor = R.color.holo_red_light;

    /**
     * 文字线条粗细
     */
    private int textDrawStrokeWidth = 3;

    Paint paint;
    private void init(){
         paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(getResources().getColor(textColor));
        paint.setStrokeWidth(textDrawStrokeWidth);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(textSize);
    }


    /**
     *
     */
    public void setDrawText(String string){
        textString = string;
        drawableStateChanged();
    }

    /**
     *  设置文字
     */
    public void setDrawTextSize(float textSize){
        this.textSize = textSize;
        drawableStateChanged();
    }

    /**
     * 设置文字位置x，y
     */
    public void setDrawLocalXY(float x, float y){
        this.x = x;
        this.y = y;
        drawableStateChanged();
    }

    /**
     * 设置文字颜色
     */
    public void setDrawTextColorResourse(int textColor){
        this.textColor = textColor;
        drawableStateChanged();
    }

    /**
     * 设置文字粗细
     */
    public void setDrawTextStrokeWidth(int textDrawStrokeWidth){
        this.textDrawStrokeWidth = textDrawStrokeWidth;
        drawableStateChanged();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int mCenterX = getMeasuredWidth() / 2;
        int mCenterY = getMeasuredHeight() / 2;

        Rect rect = new Rect();
        paint.getTextBounds(textString, 0, textString.length(),rect);
        int startX = mCenterX - rect.width() / 2;
        NLog.d(TAG, "mCenterX = %s, mCenterY = %s", mCenterX,mCenterY);
        if (!textString.equals("")) {
            Paint.FontMetrics fontMetrics = paint.getFontMetrics();
            float fontHeight = fontMetrics.bottom - fontMetrics.top;
            float baseY = getMeasuredHeight() - (getMeasuredHeight() - fontHeight ) /2 -fontMetrics.bottom;
            canvas.drawText(textString, startX,baseY, paint);
        }


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        return super.dispatchTouchEvent(event);
    }
}
