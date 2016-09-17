package com.tcl.widget.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by lenovo on 2016/9/16.
 */

public class CanvasActivity extends AppCompatActivity {
    public static void launch(Activity from) {
        Intent intent = new Intent(from,CanvasActivity .class);
        from.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
    }
}
