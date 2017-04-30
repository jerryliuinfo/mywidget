package com.tcl.widget.demo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.ui.base.ABaseFragment;
import com.tcl.widget.demo.ui.widget.boost.BoostAnimatorView;
import com.tcl.widget.demo.ui.widget.threestep.AndroidPath;

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
        final BoostAnimatorView boostAnimatorView = (BoostAnimatorView) findViewById(R.id.boost_anim_view);
        final AndroidPath androidPath = (AndroidPath) findViewById(R.id.path);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*if (boostAnimatorView.isAnimRunning()){
                    boostAnimatorView.stopAnim();
                }else {
                    boostAnimatorView.startAnim();
                }*/
                //androidPath.reset();
                androidPath.startAnim();
            }
        });




    }


}


