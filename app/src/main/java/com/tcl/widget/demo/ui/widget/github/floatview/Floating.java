package com.tcl.widget.demo.ui.widget.github.floatview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.ui.widget.github.floatview.transition.FloatingTransition;
import com.tcl.widget.demo.ui.widget.github.floatview.transition.YumFloating;

/**
 * Created by jerryliu on 2017/7/4.
 */

public class Floating {
    public static final String TAG = Floating.class.getSimpleName();
    private FloatingDecorView mDecorView;

    public Floating(Activity activity){
        if (activity == null){
            throw new NullPointerException("activity should not be null");
        }
        ViewGroup rootView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        View decorView = rootView.findViewById(R.id.floating_decor);
        if (decorView instanceof FloatingDecorView){
            mDecorView = (FloatingDecorView) decorView;
        }else {
            mDecorView = new FloatingDecorView(activity);
            mDecorView.setId(R.id.floating_decor);
            rootView.addView(mDecorView);
        }
    }

    public void startFloating(FloatingElement floatingElement){
        View anchorView = floatingElement.mAnchorView;
        View targetView = floatingElement.mTargetView;

        if (targetView == null){
            targetView = LayoutInflater.from(anchorView.getContext()).inflate(floatingElement.mTargetViewLayResId,mDecorView,false);
        }

        Rect rect = new Rect();
        anchorView.getGlobalVisibleRect(rect);

        int[] location = new int[2];
        mDecorView.getLocationOnScreen(location);
        Log.d(TAG, "startFloating location[0] = "+location[0] +", location[1] = "+location[1]);
        rect.offset(-location[0], -location[1]);

        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1, View.MeasureSpec.AT_MOST);
        targetView.measure(widthMeasureSpec,heightMeasureSpec);

        int topMargin = rect.top  + ((anchorView.getMeasuredHeight() - targetView.getMeasuredHeight()) / 2) + floatingElement.mOffsetY;
        int leftMargin = rect.left  + ((anchorView.getMeasuredWidth() - targetView.getMeasuredWidth()) / 2) + floatingElement.mOffsetX;

        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.topMargin = topMargin;
        lp.leftMargin = leftMargin;
        mDecorView.addView(targetView,lp);

        FloatingTransition floatingAnimator = floatingElement.mFloatingTransition;
        floatingAnimator.applyFloating(new YumFloating(targetView));

    }




    private class FloatingDecorView extends FrameLayout{

        public FloatingDecorView(@NonNull Context context) {
            this(context,null);
        }

        public FloatingDecorView(@NonNull Context context, @Nullable AttributeSet attrs) {
            this(context, attrs,-1);
        }

        public FloatingDecorView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
            super(context, attrs, defStyleAttr);
        }
    }
}
