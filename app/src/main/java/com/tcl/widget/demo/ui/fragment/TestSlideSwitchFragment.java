package com.tcl.widget.demo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.widget.Toast;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.ui.base.ABaseFragment;
import com.tcl.widget.demo.ui.widget.supercleaner.MySwitch;
import com.tcl.widget.demo.ui.widget.supercleaner.SlideSwitch;

/**
 * Created by jerryliu on 2017/4/14.
 */

public class TestSlideSwitchFragment extends ABaseFragment implements SlideSwitch.SlideListener {

    public static final String TAG = TestSlideSwitchFragment.class.getSimpleName();

    TextView txt;
    SlideSwitch slide;
    SlideSwitch slide2;

    MySwitch mySwitch;
    @Override
    protected int inflateContentView() {
        return R.layout.fragment_slide_switch;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);

        slide = (SlideSwitch) findViewById(R.id.swit);
        slide2 = (SlideSwitch) findViewById(R.id.swit2);

        mySwitch = (MySwitch) findViewById(R.id.myswitch);
        mySwitch.setOpen(true);
        mySwitch.setListenr(new MySwitch.ISwitchListenr() {
            @Override
            public void onClose() {
                Toast.makeText(TestSlideSwitchFragment.this.getActivity(),"off",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onOpen() {
                Toast.makeText(TestSlideSwitchFragment.this.getActivity(),"on",Toast.LENGTH_SHORT).show();

            }
        });

        slide.setState(false);
        txt = (TextView) findViewById(R.id.txt);
        slide.setSlideListener(this);

    }


    @Override
    public void open() {
        txt.setText("first switch is opend, and set the second one is 'slideable'");
        slide2.setSlideable(true);
    }

    @Override
    public void close() {
        txt.setText("first switch is closed,and set the second one is 'unslideable'");
        slide2.setSlideable(false);
    }
}


