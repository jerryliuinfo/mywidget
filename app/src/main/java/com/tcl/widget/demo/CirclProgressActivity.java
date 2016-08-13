package com.tcl.widget.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tcl.widget.demo.widget.circleprogressbar.SolidCircleProgressBar;

/**
 * @author Jerry
 * @Description:
 * @date 2016/8/13 15:25
 * @copyright TCL-MIG
 */

public class CirclProgressActivity extends AppCompatActivity{
    SolidCircleProgressBar progressBar;
    public static void launch(Activity from){
        from.startActivity(new Intent(from, CirclProgressActivity.class));
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_progressbar);
        progressBar = (SolidCircleProgressBar) findViewById(R.id.progress1);



    }
}
