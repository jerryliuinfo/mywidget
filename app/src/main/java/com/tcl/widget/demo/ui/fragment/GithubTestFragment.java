package com.tcl.widget.demo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.ui.base.ABaseFragment;
import com.tcl.widget.demo.ui.widget.github.CircleProgressView;
import com.tcl.widget.demo.ui.widget.github.CustomProgressView;
import com.tcl.widget.demo.ui.widget.github.FadeInTextView;
import com.tcl.widget.demo.ui.widget.github.LoadingButtonView;
import com.tcl.widget.demo.ui.widget.github.PwdInputView;
import com.tcl.widget.demo.ui.widget.github.SubmitButtonView;
import com.tcl.widget.demo.ui.widget.github.WaveProgressView;
import com.tcl.widget.demo.ui.widget.github.floatview.Floating;
import com.tcl.widget.demo.ui.widget.github.floatview.FloatingBuilder;
import com.tcl.widget.demo.ui.widget.github.floatview.FloatingElement;
import com.tcl.widget.demo.ui.widget.github.floatview.effect.TranslationFloatingTransition;
import com.tcl.widget.demo.uti.NLog;

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
    private CustomProgressView custom_progress;
    private LoadingButtonView loading_btn;
    private FadeInTextView fadeInTextView;
    private PwdInputView pwd_input;


    private Floating floating;
    private ImageView icPaperAirPlane;





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
                fadeInTextView.startAnimation();
            }
        });

        submitButtonView = (SubmitButtonView) findViewById(R.id.submit);
        submitButtonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitButtonView.start();
            }
        });

        custom_progress = (CustomProgressView) findViewById(R.id.custom_progress);
        custom_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                custom_progress.setValue(mRandom.nextFloat() * custom_progress.getMaxValue());
            }
        });
        custom_progress.setListener(new CustomProgressView.ProgressAnimationListener() {
            @Override
            public void onProgressUpdate(int value) {
                //NLog.d(TAG, "onProgressUpdate value = %s", value);
            }

            @Override
            public void onFinish() {
                NLog.d(TAG, "onFinish");
            }
        });

        loading_btn = (LoadingButtonView) findViewById(R.id.loading_btn);
        loading_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loading_btn.isLoading()){
                    loading_btn.stopLoading();
                }else {
                    loading_btn.startLoading();
                }
            }
        });

        fadeInTextView = (FadeInTextView) findViewById(R.id.fadeInTextView);
        fadeInTextView.setTextString("自定义view实现字符串逐字显示，后边的文字是为了测试换行是否正常显示！");

        pwd_input = (PwdInputView) findViewById(R.id.pwd_input);
        pwd_input.setRightPwd("654321");
        pwd_input.setCallback(new PwdInputView.PwdCallback() {
            @Override
            public void onProgress(String pwd) {

            }

            @Override
            public void onEquals(String pwd) {
                Toast.makeText(GithubTestFragment.this.getActivity(),"onEquals",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onDifference(String pwd) {
                Toast.makeText(GithubTestFragment.this.getActivity(),"onDifference",Toast.LENGTH_SHORT).show();
            }
        });


        floating = new Floating(getActivity());
        icPaperAirPlane = (ImageView) findViewById(R.id.icPaperAirPlane);
        icPaperAirPlane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = new ImageView(GithubTestFragment.this.getActivity());
                imageView.setImageResource(R.drawable.paper_airplane);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(icPaperAirPlane.getMeasuredWidth(),icPaperAirPlane.getMeasuredHeight()));

                FloatingElement element = new FloatingBuilder().floatTransiton(new TranslationFloatingTransition()).
                        anchorView(v).
                        targetView(imageView).
                        build();
                floating.startFloating(element);
            }
        });
    }







}


