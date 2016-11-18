package com.tcl.widget.demo.ui.fragment;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.ui.base.ABaseFragment;
import com.tcl.widget.demo.ui.widget.circleprogressbar.CircleProgressBar;

/**
 * @author Jerry
 * @Description:
 * @date 2016/11/18 10:10
 * @copyright TCL-MIG
 */

public class CirclProgressFragmentA extends ABaseFragment {

    CircleProgressBar mSolidProgressBar;
    CircleProgressBar mLineProgressBar;
    private CircleProgressBar mCustomProgressBar1;
    private CircleProgressBar mCustomProgressBar2;
    private CircleProgressBar mCustomProgressBar3;
    private CircleProgressBar mCustomProgressBar4;

    @Override
    protected int inflateContentView() {
        return R.layout.activity_circle_progressbar;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        mLineProgressBar = (CircleProgressBar) rootView.findViewById(R.id.progress1);
        mSolidProgressBar = (CircleProgressBar) rootView.findViewById(R.id.progress2);
        mCustomProgressBar1 = (CircleProgressBar) rootView.findViewById(R.id.custom_progress3);
        mCustomProgressBar2 = (CircleProgressBar) rootView.findViewById(R.id.custom_progress4);
        mCustomProgressBar3 = (CircleProgressBar) rootView.findViewById(R.id.custom_progress5);
        mCustomProgressBar4 = (CircleProgressBar) rootView.findViewById(R.id.custom_progress6);

        mSolidProgressBar.setMax(100);
        mLineProgressBar.setMax(100);
        mCustomProgressBar1.setMax(100);
        mCustomProgressBar2.setMax(100);
        mCustomProgressBar3.setMax(100);
        mCustomProgressBar4.setMax(100);
    }

    @Override
    public void onResume() {
        super.onResume();
        simulateProgress();
    }
    ValueAnimator animator;
    private void simulateProgress(){
        if (animator != null){
            animator.cancel();
        }
        animator = ValueAnimator.ofInt(0, 100);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int progress = (int) animation.getAnimatedValue();
                mSolidProgressBar.setProgress(progress);
                mLineProgressBar.setProgress(progress);
                mCustomProgressBar1.setProgress(progress);
                mCustomProgressBar2.setProgress(progress);
                mCustomProgressBar3.setProgress(progress);
                mCustomProgressBar4.setProgress(progress);
            }
        });
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(4000);
        animator.start();
    }

}
