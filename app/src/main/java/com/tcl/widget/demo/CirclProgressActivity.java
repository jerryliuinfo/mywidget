package com.tcl.widget.demo;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tcl.widget.demo.widget.circleprogressbar.CircleProgressBar;

/**
 * @author Jerry
 * @Description:
 * @date 2016/8/13 15:25
 * @copyright TCL-MIG
 */

public class CirclProgressActivity extends AppCompatActivity{
    private static final String TAG = CirclProgressActivity.class.getSimpleName();

    public static void launch(Activity from){
        from.startActivity(new Intent(from, CirclProgressActivity.class));
    }


    CircleProgressBar mSolidProgressBar;
    CircleProgressBar mLineProgressBar;
    private CircleProgressBar mCustomProgressBar1;
    private CircleProgressBar mCustomProgressBar2;
    private CircleProgressBar mCustomProgressBar3;
    private CircleProgressBar mCustomProgressBar4;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_progressbar);
        mLineProgressBar = (CircleProgressBar) findViewById(R.id.progress1);
        mSolidProgressBar = (CircleProgressBar) findViewById(R.id.progress2);
        mCustomProgressBar1 = (CircleProgressBar) findViewById(R.id.custom_progress3);
        mCustomProgressBar2 = (CircleProgressBar) findViewById(R.id.custom_progress4);
        mCustomProgressBar3 = (CircleProgressBar) findViewById(R.id.custom_progress5);
        mCustomProgressBar4 = (CircleProgressBar) findViewById(R.id.custom_progress6);

        mSolidProgressBar.setMax(100);
        mLineProgressBar.setMax(100);
        mCustomProgressBar1.setMax(100);
        mCustomProgressBar2.setMax(100);
        mCustomProgressBar3.setMax(100);
        mCustomProgressBar4.setMax(100);
    }

    @Override
    protected void onResume() {
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
