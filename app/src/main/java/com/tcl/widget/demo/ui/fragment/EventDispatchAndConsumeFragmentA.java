package com.tcl.widget.demo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.ui.base.ABaseFragment;
import com.tcl.widget.demo.uti.NLog;
import com.tcl.widget.demo.ui.widget.MyViewGroupA;

/**
 * @author Jerry
 * @Description:
 * @date 2016/10/29 14:47
 * @copyright TCL-MIG
 */

public class EventDispatchAndConsumeFragmentA extends ABaseFragment {

    @Override
    protected int inflateContentView() {
        return R.layout.fragment_event_dispatch_consume;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        Button button = (Button) rootView.findViewById(R.id.btn_click);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NLog.e(MyViewGroupA.TAG, "button onClick");
            }
        });
        /*button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                NLog.e(MyViewGroupA.TAG, "button onTouch");
                NotificationManagerWrapper.getInstance().sendNotification();
                return true;
            }
        });*/



    }



}
