package com.tcl.widget.demo.ui.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.ui.base.ABaseFragment;
import com.tcl.widget.demo.uti.NLog;

/**
 * Created by lenovo on 2016/11/20.
 */

public class HealthTableFragment extends ABaseFragment {

    @Override
    protected int inflateContentView() {
        return R.layout.fragment_health_table;
    }
    private ImageView imageView;
    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        imageView = (ImageView) rootView.findViewById(R.id.icon);
        Button btnMove = (Button) rootView.findViewById(R.id.btn_move);
        final Rect rect = new Rect();
        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "translationY", rect.bottom);
                animator.setDuration(5000);
                animator.start();
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        NLog.d(TAG, "onAnimationUpdate = %f", (float)animation.getAnimatedValue());
                    }
                });
            }
        });

        final Button btnEnable = (Button) rootView.findViewById(R.id.btn_enable);

        btnEnable.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {

                btnEnable.getGlobalVisibleRect(rect);
                NLog.d(TAG, "rect top = %d, rect bottom = %d", rect.top,rect.bottom);
                return true;
            }
        });

    }


}
