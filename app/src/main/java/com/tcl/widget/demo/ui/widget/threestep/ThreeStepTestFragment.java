package com.tcl.widget.demo.ui.widget.threestep;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.ui.base.ABaseFragment;

/**
 * Created by jerryliu on 2017/4/14.
 */

public class ThreeStepTestFragment extends ABaseFragment {

    public static final String TAG = ThreeStepTestFragment.class.getSimpleName();


    private Button btn;

    @Override
    protected int inflateContentView() {
        return R.layout.fragment_three_step_test;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        btn = (Button) findViewById(R.id.btn);


    }





}


