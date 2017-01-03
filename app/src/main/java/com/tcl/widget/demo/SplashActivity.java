package com.tcl.widget.demo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.tcl.widget.demo.container.BaseActivity;
import com.tcl.widget.demo.container.FragmentContainerActivity;
import com.tcl.widget.demo.ui.fragment.MenuFragment;
import com.tcl.widget.demo.ui.widget.DrawTextImageView;

/**
 * @author Jerry
 * @Description:
 * @date 2016/9/10 11:08
 * @copyright TCL-MIG
 */

public class SplashActivity extends BaseActivity {
    TextView btn;
    DrawTextImageView drawTextImageView;
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
        btn = (TextView) findViewById(R.id.btn);
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(SplashActivity.this, SecondActivity.class));
            }
        });
        //FragmentContainerActivity.launch(SplashActivity.this, HealthTableFragment.class, null);
        FragmentContainerActivity.launch(SplashActivity.this, MenuFragment.class, null);
        finish();
        getMDActionBar().setDisplayHomeAsUpEnabled(true);
        getMDActionBar().setHomeButtonEnabled(true);
        drawTextImageView = (DrawTextImageView) findViewById(R.id.drawTextImageView);
        drawTextImageView.setDrawText("48℃");
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return false;
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }
}
