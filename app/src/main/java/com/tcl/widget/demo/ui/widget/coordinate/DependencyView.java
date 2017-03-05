package com.tcl.widget.demo.ui.widget.coordinate;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.tcl.widget.demo.uti.NLog;
import com.tcl.widget.demo.uti.SystemUtility;

/**
 * Created by jerry on 2017/2/26.
 */

public class DependencyView extends View {
    private static final String TAG = DependencyView.class.getSimpleName();
    private int lastX,lastY;
    int mScreenWidth,mScreenHeight;
    int mViewWidth,mViewHeight;
    int statusBarAndActionBarHeight;
    int bottomNavigationBarHeight;

    public DependencyView(Context context) {
        this(context,null);
    }

    public DependencyView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public DependencyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mViewWidth = getMeasuredWidth();
        mViewHeight = getMeasuredHeight();
        NLog.d(TAG, "mViewWidth = %d, mViewHeight = %d", mViewWidth,mViewHeight);
    }

    public void setStatusBarAndActionBarHeight(int statusBarAndActionBarHeight) {
        this.statusBarAndActionBarHeight = statusBarAndActionBarHeight;
    }

    private void init(){
        mScreenWidth = SystemUtility.getScreenWidth();
        mScreenHeight = SystemUtility.getScreenHeight();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        NLog.d(TAG, "onTouchEvent x = %s, y = %s, mLastX = %s, mLastY = %s", x,y, lastX,lastY);
        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN: {

                break;
            }

            case MotionEvent.ACTION_MOVE: {
                CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) getLayoutParams();
                int left = layoutParams.leftMargin + x - lastX;
                int top = layoutParams.topMargin + y - lastY;

                NLog.d(TAG, "ACTION_MOVE left = %s, top = %s", left,top);
                if (left <= 0){
                    left = 0;
                }
                if (left > mScreenWidth - mViewWidth){
                    left = mScreenWidth - mViewWidth;
                }
                NLog.d(TAG, "statusBarAndActionBarHeight  = %s", statusBarAndActionBarHeight);
                if (top < statusBarAndActionBarHeight ){
                    top = statusBarAndActionBarHeight;
                }
                if (top > mScreenHeight - mViewHeight ){
                    top = mScreenHeight - mViewHeight;
                }
                layoutParams.leftMargin = left;
                layoutParams.topMargin = top;
                setLayoutParams(layoutParams);
                requestLayout();
                break;
            }

            case MotionEvent.ACTION_UP: {

                break;
            }

        }
        lastX = x;
        lastY = y;
        return true;

    }



}
