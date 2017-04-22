package com.tcl.widget.demo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.ui.base.ABaseFragment;
import com.tcl.widget.demo.ui.widget.MainWhiteListEntryView;

/**
 * Created by jerryliu on 2017/4/14.
 */

public class TestWidgetFragment extends ABaseFragment {
    private Button btn;
    @Override
    protected int inflateContentView() {
        return R.layout.fragment_line_chart_view;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        btn = (Button) findViewById(R.id.btn);
        final MainWhiteListEntryView view = (MainWhiteListEntryView) findViewById(R.id.main_white_list);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.setAnimDuration(200);
                view.startAnimation(0);
            }
        });
    }
}