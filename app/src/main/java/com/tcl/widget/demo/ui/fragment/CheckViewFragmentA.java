package com.tcl.widget.demo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.ui.base.ABaseFragment;
import com.tcl.widget.demo.ui.widget.CheckView;

/**
 * @author Jerry
 * @Description:
 * @date 2016/9/30 14:00
 * @copyright TCL-MIG
 */

public class CheckViewFragmentA extends ABaseFragment {
    CheckView checkView;
    Button mBtnSwitch;

    public static ABaseFragment newInstance(){
        CheckViewFragmentA fragment = new CheckViewFragmentA();
        return fragment;
    }

    @Override
    protected int inflateContentView() {
        return R.layout.activity_check_view;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        checkView = (CheckView) rootView.findViewById(R.id.check_view);
        mBtnSwitch = (Button) rootView.findViewById(R.id.btn_switch);
        mBtnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkView.toggle();
            }
        });
    }
}
