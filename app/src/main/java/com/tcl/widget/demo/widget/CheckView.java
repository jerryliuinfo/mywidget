package com.tcl.widget.demo.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.uti.NLog;

/**
 * Created by lenovo on 2016/9/17.
 */

public class CheckView extends View {
    private static final String TAG = CheckView.class.getSimpleName();
    private static final int ANIM_NULL = 0;
    private static final int ANIM_CHECK = 1;
    private static final int ANIM_UNCHECK = 2;

    private int mAnimState = ANIM_NULL;
    private boolean isChecked= false; //是否已经是勾选状态

    private static final int MAX_PAGE = 13;
    private static final int INIT_PAGE = -1;
    private int mCurrentPage = INIT_PAGE;
    private int mDuration = 500;
    private Bitmap mBitmap;

    private Handler mHandler;

    
    private int mWidth, mHeight;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    public CheckView(Context context) {
        super(context);
        init(context, null);
    }

    public CheckView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public CheckView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setDither(true);
        mPaint.setColor(getResources().getColor(R.color.progress_start_color));
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                NLog.d(TAG, "handleMessage mAnimState %d, isChecked = %b, mCurrentPage = %d",
                        mAnimState, isChecked, mCurrentPage);
                if (mAnimState == ANIM_CHECK && !isChecked){
                    mCurrentPage++;
                    if (mCurrentPage >= MAX_PAGE){
                        isChecked = true;
                        mAnimState = ANIM_NULL;
                        return;
                    }else {
                        mHandler.sendEmptyMessageDelayed(0, mDuration / MAX_PAGE);
                    }
                    invalidate();
                }else if (mAnimState == ANIM_UNCHECK && isChecked){
                    mCurrentPage--;
                    if (mCurrentPage < INIT_PAGE -1){
                        isChecked = false;
                        mAnimState = ANIM_NULL;
                        mCurrentPage = INIT_PAGE;
                        return;
                    }else {
                        mHandler.sendEmptyMessageDelayed(0, mDuration / MAX_PAGE);
                    }
                    invalidate();
                }else if (mAnimState == ANIM_NULL){

                }
            }
        };
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
        mPaint.setColor(getResources().getColor(R.color.progress_start_color));
        canvas.translate(mWidth/2, mHeight /2);
        canvas.drawCircle(0, 0 , 200, mPaint);

        if (mBitmap == null){
            mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.checkmark);
        }

        int slideLenght = mBitmap.getHeight();
        Rect srcRect = new Rect(slideLenght * mCurrentPage, 0 , slideLenght * (mCurrentPage + 1), slideLenght);
        NLog.d(TAG, "onDraw mCurrentPage = %d", mCurrentPage);
        Rect destRect = new Rect(-160, -160, 160, 160);
        canvas.drawBitmap(mBitmap, srcRect, destRect, mPaint);
    }

    private void check(){
        if (mAnimState != ANIM_NULL || isChecked){
            return;
        }
        mAnimState = ANIM_CHECK;
        mHandler.sendEmptyMessage(0);

    }

    private void unCheck(){
        if (mAnimState != ANIM_NULL || !isChecked){
            return;
        }
        mAnimState = ANIM_UNCHECK;
        mHandler.sendEmptyMessage(0);
    }

    public void toggle(){
        if (isChecked){
            unCheck();
        }else {
            check();
        }

    }

}
