package com.tcl.widget.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.tcl.widget.demo.widget.CheckView;

/**
 * Created by lenovo on 2016/9/17.
 */

public class CheckViewActivity extends AppCompatActivity {
    public static void launch(Activity from) {
        Intent intent = new Intent(from, CheckViewActivity.class);
        from.startActivity(intent);
    }
    CheckView checkView;
    Button mBtnSwitch;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_view);

        checkView = (CheckView) findViewById(R.id.check_view);
        mBtnSwitch = (Button) findViewById(R.id.btn_switch);
        mBtnSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkView.toggle();
            }
        });
    }
}
