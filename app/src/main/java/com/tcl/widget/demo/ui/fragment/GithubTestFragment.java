package com.tcl.widget.demo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.ui.base.ABaseFragment;
import com.tcl.widget.demo.ui.widget.github.CircleProgressView;

import java.util.Random;

/**
 * Created by jerryliu on 2017/4/14.
 */

public class GithubTestFragment extends ABaseFragment {

    public static final String TAG = GithubTestFragment.class.getSimpleName();
    private CircleProgressView mCircleProgress1;
    private Random mRandom;


    @Override
    protected int inflateContentView() {
        return R.layout.fragment_github;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        mCircleProgress1 = (CircleProgressView) findViewById(R.id.circle_progress1);
        mRandom = new Random();
        mCircleProgress1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCircleProgress1.setValue(mRandom.nextFloat() * mCircleProgress1.getMaxValue());
            }
        });
        findViewById(R.id.btn_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCircleProgress1.reset();
            }
        });

    }







}


