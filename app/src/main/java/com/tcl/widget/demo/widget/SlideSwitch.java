package com.tcl.widget.demo.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Looper;
import android.os.Parcelable;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.NLog;
import com.tcl.widget.demo.view.util.DisplayUtil;

import static android.R.attr.radius;

/**
 * @author Jerry
 * @Description:
 * @date 2016/11/11 20:55
 * @copyright TCL-MIG
 */

public class SlideSwitch extends View {
    private static final String TAG = SlideSwitch.class.getSimpleName();
    public static final int SHAPE_RECT = 1;
    public static final int SHAPE_CIRCLE = 2;
    private static final int RIM_SIZE = 6;
    private static final int DEFAULT_COLOR_THEME = Color.parseColor("#ff00ee00");
    // 3 attributes
    private boolean isOpen;
    private int shape;

    private Paint slideBgpaint;//背景画笔

    private Paint blockPaint;//滑块画笔

    private Rect backRect;
    private Rect frontRect;
    private RectF frontCircleRect;
    private RectF backCircleRect;
    private int alpha;
    private int max_left;
    private int min_left;
    private int frontRect_left;
    private int frontRect_left_begin = RIM_SIZE;
    private int eventStartX;
    private int eventLastX;
    private int diffX = 0;
    private boolean slideable = true;
    private SlideListener listener;

    public interface SlideListener {
         void open();

         void close();
    }

    public SlideSwitch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        listener = null;
        slideBgpaint = new Paint();
        slideBgpaint.setAntiAlias(true);

        blockPaint = new Paint();
        blockPaint.setAntiAlias(true);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.slideswitch);
        slide_bg_open_color = a.getColor(R.styleable.slideswitch_slide_bg_open_color,
                DEFAULT_COLOR_THEME);
        slide_bg_close_color = a.getColor(R.styleable.slideswitch_slide_bg_close_color,
                DEFAULT_COLOR_THEME);
        slide_block_open_color = a.getColor(R.styleable.slideswitch_slide_block_open_color,
                DEFAULT_COLOR_THEME);
        slide_block_close_color = a.getColor(R.styleable.slideswitch_slide_block_close_color,
                DEFAULT_COLOR_THEME);

        bg_padding_left = a.getDimensionPixelOffset(R.styleable.slideswitch_padding_left, DisplayUtil.dp2px(context,4));
        bg_padding_top = a.getDimensionPixelOffset(R.styleable.slideswitch_padding_top, DisplayUtil.dp2px(context,6));
        circle_padding_top = a.getDimensionPixelOffset(R.styleable.slideswitch_corcle_padding_top, DisplayUtil.dp2px(context,3f));

        isOpen = a.getBoolean(R.styleable.slideswitch_isOpen, false);
        shape = a.getInt(R.styleable.slideswitch_shape, SHAPE_RECT);
        a.recycle();
    }

    private int slide_bg_open_color;
    private int slide_bg_close_color;
    private int slide_block_open_color;
    private int slide_block_close_color;
    private int bg_padding_left = 4;
    private int bg_padding_top = 6;
    private int circle_padding_top = 3;

    public SlideSwitch(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideSwitch(Context context) {
        this(context, null);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = measureDimension(280, widthMeasureSpec);
        int height = measureDimension(140, heightMeasureSpec);
        NLog.d(TAG, "onMeasure width = %d, height = %d, shape = %d, ", width,height,shape);
        if (shape == SHAPE_CIRCLE) {
            if (width < height)
                width = height * 2;
        }
        setMeasuredDimension(width, height);
        initDrawingVal();
    }

    int mWiewWidth;
    int mWiewHeight;
    public void initDrawingVal() {
        mWiewWidth = getMeasuredWidth();
        mWiewHeight = getMeasuredHeight();

        backCircleRect = new RectF();
        backRect = new Rect(bg_padding_left, bg_padding_top, mWiewWidth - bg_padding_left, mWiewHeight - bg_padding_top);

        frontCircleRect = new RectF();
        frontRect = new Rect();

        min_left = bg_padding_left;
        max_left = mWiewWidth  -  bg_padding_left - (mWiewHeight - circle_padding_top * 2);
        if (isOpen) {
            frontRect_left = max_left;
            alpha = 255;
        } else {
            frontRect_left = min_left;
            alpha = 0;
        }
        frontRect_left_begin = frontRect_left;
    }

    public int measureDimension(int defaultSize, int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = defaultSize; // UNSPECIFIED
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画背景椭圆
        drawBgRect(canvas);
        //画滑块
        drawBlock(canvas);
    }

    /**
     * 画背景椭圆
     * @param canvas
     */
    private void drawBgRect(Canvas canvas){
        // 画背景椭圆
        int radius = backRect.height() / 2;
        backCircleRect.set(backRect);
        if (isOpen){
            slideBgpaint.setColor(slide_bg_open_color);
        }else {
            slideBgpaint.setColor(slide_bg_close_color);
        }
        canvas.drawRoundRect(backCircleRect, radius, radius, slideBgpaint);
    }


    /**
     * 画滑块
     * @param canvas
     */
    private void drawBlock(Canvas canvas){
        frontRect.set(frontRect_left, backRect.top - circle_padding_top, frontRect_left + mWiewHeight - 2 * circle_padding_top,
                mWiewHeight - circle_padding_top);
        frontCircleRect.set(frontRect);
        if (isOpen){
            blockPaint.setColor(slide_block_open_color);
        }else {
            blockPaint.setColor(slide_block_close_color);
        }
        canvas.drawRoundRect(frontCircleRect, radius, radius, blockPaint);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (slideable == false)
            return super.onTouchEvent(event);
        int action = MotionEventCompat.getActionMasked(event);
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                eventStartX = (int) event.getRawX();
                break;
            case MotionEvent.ACTION_MOVE:
                eventLastX = (int) event.getRawX();
                diffX = eventLastX - eventStartX;
                int tempX = diffX + frontRect_left_begin;
                tempX = (tempX > max_left ? max_left : tempX);
                tempX = (tempX < min_left ? min_left : tempX);
                if (tempX >= min_left && tempX <= max_left) {
                    frontRect_left = tempX;
                    alpha = (int) (255 * (float) tempX / (float) max_left);
                    invalidateView();
                }
                break;
            case MotionEvent.ACTION_UP:
                int wholeX = (int) (event.getRawX() - eventStartX);
                frontRect_left_begin = frontRect_left;
                boolean toRight;
                toRight = (frontRect_left_begin > max_left / 2 ? true : false);
                if (Math.abs(wholeX) < 3) {
                    toRight = !toRight;
                }
                moveToDest(toRight);
                break;
            default:
                break;
        }
        return true;
    }

    /**
     * draw again
     */
    private void invalidateView() {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            invalidate();
        } else {
            postInvalidate();
        }
    }

    public void setSlideListener(SlideListener listener) {
        this.listener = listener;
    }

    public void moveToDest(final boolean toRight) {
        ValueAnimator toDestAnim = ValueAnimator.ofInt(frontRect_left,
                toRight ? max_left : min_left);
        toDestAnim.setDuration(500);
        toDestAnim.setInterpolator(new AccelerateDecelerateInterpolator());
        toDestAnim.start();
        toDestAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                frontRect_left = (Integer) animation.getAnimatedValue();
                alpha = (int) (255 * (float) frontRect_left / (float) max_left);
                invalidateView();
            }
        });
        toDestAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (toRight) {
                    isOpen = true;
                    if (listener != null)
                        listener.open();
                    frontRect_left_begin = max_left;
                } else {
                    isOpen = false;
                    if (listener != null)
                        listener.close();
                    frontRect_left_begin = min_left;
                }
            }
        });
    }

    public void setState(boolean isOpen) {
        this.isOpen = isOpen;
        initDrawingVal();
        invalidateView();
        if (listener != null)
            if (isOpen == true) {
                listener.open();
            } else {
                listener.close();
            }
    }

    public void setShapeType(int shapeType) {
        this.shape = shapeType;
    }

    public void setSlideable(boolean slideable) {
        this.slideable = slideable;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.isOpen = bundle.getBoolean("isOpen");
            state = bundle.getParcelable("instanceState");
        }
        super.onRestoreInstanceState(state);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putBoolean("isOpen", this.isOpen);
        return bundle;
    }
}
