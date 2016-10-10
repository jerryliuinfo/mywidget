package com.tcl.widget.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.tcl.widget.demo.container.FragmentContainerActivity;

/**
 * @author Jerry
 * @Description:
 * @date 2016/9/10 11:08
 * @copyright TCL-MIG
 */

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //CirclProgressActivity.launch(this);
       // CanvasActivity.launch(this);
        //PieActivity.launch(this);
        //CheckViewActivity.launch(this);
        //RadarActivity.launch(this);
        //FragmentContainerActivity.launch(this,RadarFragment.class, null);
        //FragmentContainerActivity.launch(this,BeizerFragment.class, null);
        FragmentContainerActivity.launch(this,BeizerFragment2.class, null);
        finish();
    }
}
