package com.tcl.widget.demo;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tcl.widget.demo.uti.GlassUtil;
import com.tcl.widget.demo.widget.GlassView;

public class MainActivity extends AppCompatActivity {

    private GlassView glassView;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        glassView = (GlassView) findViewById(R.id.glassview);
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
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(updateRunable);
    }
}
