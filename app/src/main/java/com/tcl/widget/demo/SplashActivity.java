package com.tcl.widget.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tcl.widget.demo.service.LocalCastielService;
import com.tcl.widget.demo.service.RemoteCastielService;

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
        //FragmentContainerActivity.launch(this,CheckViewFragmentA.class, null);
        //FragmentContainerActivity.launch(this,RadarFragment.class, null);
        //FragmentContainerActivity.launch(this,BeizerFragmentA.class, null);
        //FragmentContainerActivity.launch(this,BeizerFragment2A.class, null);
        //FragmentContainerActivity.launch(this,PathBooleanFragment.class, null);
        //FragmentContainerActivity.launch(this,PathFillTypeFragmentA.class, null);
        //FragmentContainerActivity.launch(this, MatrixFragmentA.class, null);
        //FragmentContainerActivity.launch(this, EventDispatchAndConsumeFragmentA.class, null);
        //FragmentContainerActivity.launch(this, SlideSwitchFragmentA.class, null);
        //FragmentContainerActivity.launch(this, LayoutOptimizeFragmentA.class, null);
        //FragmentContainerActivity.launch(SplashActivity.this, MaterialEdittextFragmentA.class, null);
        setContentView(R.layout.activity_splash);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SplashActivity.this, SecondActivity.class));
            }
        });



       // finish();

        //git stash test
        // 启动本地服务和远程服务
        startService(new Intent(this, LocalCastielService.class));
        startService(new Intent(this, RemoteCastielService.class));
    }
}
