package com.tcl.widget.demo.ui.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.ui.base.ABaseFragment;
import com.tcl.widget.demo.ui.widget.GlassView;
import com.tcl.widget.demo.uti.GlassUtil;

/**
 * @author Jerry
 * @Description:
 * @date 2016/11/18 10:08
 * @copyright TCL-MIG
 */

public class GlassViewFragmentA extends ABaseFragment {
    private GlassView glassView;
    private Handler handler = new Handler();
    @Override
    protected int inflateContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        glassView = (GlassView) rootView.findViewById(R.id.glassview);
        glassView.setmRange(ranges);
        glassView.setValueChangedListener(new GlassView.ValueChangedListener() {
            @Override
            public String[] onValueChanged(float newValue) {
                return GlassUtil.getLevelColors(GlassUtil.getSmellLevel(newValue));
            }
        });

        handler.removeCallbacks(updateRunable);
        handler.post(updateRunable);
    }


    private float[] ranges = new float[]{0,100};

    private Runnable updateRunable = new Runnable() {
        @Override
        public void run() {
            glassView.setValue((float) (Math.random() * (ranges[1] - ranges[0])));
            handler.removeCallbacks(updateRunable);
            handler.postDelayed(updateRunable, 5000);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(updateRunable);
    }
}
