package com.tcl.widget.demo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.container.BaseActivity;
import com.tcl.widget.demo.ui.base.ABaseFragment;

/**
 * @author Jerry
 * @Description:
 * @date 2017/1/3 11:20
 * @copyright TCL-MIG
 */

public class MenuFragment extends ABaseFragment {
    @Override
    protected int inflateContentView() {
        return R.layout.fragment_menu;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        BaseActivity baseActivity = (BaseActivity) getActivity();
        baseActivity.getMDActionBar().setDisplayHomeAsUpEnabled(true);
        baseActivity.getMDActionBar().setTitle("Menu");
        setHasOptionsMenu(true);
        super.layoutInit(inflater, savedInstanceSate);
    }
}
