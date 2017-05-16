package com.tcl.widget.demo.ui.fragment;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.ui.base.ABaseFragment;
import com.tcl.widget.demo.ui.widget.boost.BoostAnimatorView;
import com.tcl.widget.demo.ui.widget.threestep.AndroidPath;
import com.tcl.widget.demo.ui.widget.threestep.PathEffect;
import com.tcl.widget.demo.ui.widget.threestep.animator.BounceAnimatorView;
import com.tcl.widget.demo.ui.widget.threestep.interpolator.CharEvaluator;
import com.tcl.widget.demo.ui.widget.threestep.interpolator.MyInterpolator;
import com.tcl.widget.demo.uti.NLog;

/**
 * Created by jerryliu on 2017/4/14.
 */

public class TestWidgetFragment extends ABaseFragment {

    public static final String TAG = TestWidgetFragment.class.getSimpleName();


    private Button btn;
    private BounceAnimatorView bounce_view;
    @Override
    protected int inflateContentView() {
        return R.layout.fragment_line_chart_view;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        btn = (Button) findViewById(R.id.btn);

        bounce_view = (BounceAnimatorView) findViewById(R.id.bounce_view);

        final BoostAnimatorView boostAnimatorView = (BoostAnimatorView) findViewById(R.id.boost_anim_view);
        final AndroidPath androidPath = (AndroidPath) findViewById(R.id.path);
        final PathEffect pathEffect = (PathEffect) findViewById(R.id.path_effect);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (boostAnimatorView.isAnimRunning()){
                    boostAnimatorView.stopAnim();
                }else {
                    boostAnimatorView.startAnim();
                }
                //androidPath.reset();
                //androidPath.startAnim();
                //pathEffect.startAnim();
                //doAnimation();
                //bounce_view.doAnimation();
            }
        });
    }

    int currentValue;
    private void doAnimation(){
        ValueAnimator animator = ValueAnimator.ofInt(0,400);
        animator.setDuration(1000);
        animator.setInterpolator(new MyInterpolator());

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentValue = (int) animation.getAnimatedValue();
                NLog.d(TAG, "currentValue = %s", currentValue);
                btn.layout(btn.getLeft(),currentValue,btn.getRight(),currentValue+btn.getHeight());
            }
        });



        animator = ValueAnimator.ofObject(new CharEvaluator(), Character.valueOf('A'),Character.valueOf('Z'));
        animator.setDuration(10000);
        animator.setInterpolator(new MyInterpolator());

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
//                currentValue = (int) animation.getAnimatedValue();
//                NLog.d(TAG, "currentValue = %s", currentValue);
//                btn.layout(btn.getLeft(),currentValue,btn.getRight(),currentValue+btn.getHeight());
                //char text = (char) animation.getAnimatedValue();
                //btn.setText(String.valueOf(text));

            }
        });


        animator.start();
    }



}


