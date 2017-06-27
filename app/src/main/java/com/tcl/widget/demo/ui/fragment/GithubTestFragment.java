package com.tcl.widget.demo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.ui.base.ABaseFragment;
import com.tcl.widget.demo.ui.widget.github.CircleProgressView;
import com.tcl.widget.demo.ui.widget.github.SubmitButtonView;
import com.tcl.widget.demo.ui.widget.github.WaveProgressView;

import java.util.Random;

/**
 * Created by jerryliu on 2017/4/14.
 */

public class GithubTestFragment extends ABaseFragment {

    public static final String TAG = GithubTestFragment.class.getSimpleName();
    private CircleProgressView mCircleProgress1;
    private WaveProgressView wave_progress1;
    private Random mRandom;
    private SubmitButtonView submitButtonView;


    @Override
    protected int inflateContentView() {
        return R.layout.fragment_github;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        mCircleProgress1 = (CircleProgressView) findViewById(R.id.circle_progress1);

        wave_progress1 = (WaveProgressView) findViewById(R.id.wave_progress1);
        mRandom = new Random();
        mCircleProgress1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCircleProgress1.setValue(mRandom.nextFloat() * mCircleProgress1.getMaxValue());
            }
        });
        wave_progress1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wave_progress1.setValue(mRandom.nextFloat() * wave_progress1.getMaxValue());
            }
        });



        findViewById(R.id.btn_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCircleProgress1.reset();
            }
        });

        submitButtonView = (SubmitButtonView) findViewById(R.id.submit);
        submitButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitButtonView.start();
            }
        });

    }







}


