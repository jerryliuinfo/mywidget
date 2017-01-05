package com.tcl.widget.demo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.ui.base.ABaseFragment;
import com.tcl.widget.demo.ui.widget.CustomDrawablView;

/**
 * @author Jerry
 * @Description:
 * @date 2017/1/3 17:45
 * @copyright TCL-MIG
 */

public class CustomDrableFragment extends ABaseFragment {
    @Override
    protected int inflateContentView() {
        return R.layout.fragment_custom_drawable;
    }
    CustomDrawablView customDrawablView;
    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        View view = rootView.findViewById(R.id.layout_root);
        customDrawablView = new CustomDrawablView();
        view.setBackground(customDrawablView);
    }
}
