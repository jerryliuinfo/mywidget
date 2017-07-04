package com.tcl.widget.demo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.ui.base.ABaseFragment;
import com.tcl.widget.demo.ui.widget.github.floatview.Floating;
import com.tcl.widget.demo.ui.widget.github.floatview.FloatingBuilder;
import com.tcl.widget.demo.ui.widget.github.floatview.FloatingElement;
import com.tcl.widget.demo.ui.widget.github.floatview.effect.AirPlaneFloating;
import com.tcl.widget.demo.ui.widget.github.floatview.effect.AlphaFloatingTransition;
import com.tcl.widget.demo.ui.widget.github.floatview.effect.BearFloating;
import com.tcl.widget.demo.ui.widget.github.floatview.effect.StartFloatingTransition;
import com.tcl.widget.demo.ui.widget.github.floatview.effect.TranslationFloatingTransition;
import com.tcl.widget.demo.uti.ResUtil;

/**
 * Created by jerryliu on 2017/7/4.
 * 测试floatiew
 */

public class FloatViewFragment extends ABaseFragment {
    private Floating floating;
    private ImageView icPaperAirPlane;
    private ImageView icCommandLine;
    private ImageView icLike;
    private ImageView icStar;

    private ImageView icBeer;
    private ImageView icPlane;


    @Override
    protected int inflateContentView() {
        return R.layout.fragment_floatview;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);

        floating = new Floating(getActivity());
        icPaperAirPlane = (ImageView) findViewById(R.id.icPaperAirPlane);
        icPaperAirPlane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = new ImageView(FloatViewFragment.this.getActivity());
                imageView.setImageResource(R.drawable.paper_airplane);
                imageView.setLayoutParams(new ViewGroup.LayoutParams(icPaperAirPlane.getMeasuredWidth(),icPaperAirPlane.getMeasuredHeight()));

                FloatingElement element = new FloatingBuilder().floatTransiton(new TranslationFloatingTransition()).
                        anchorView(v).
                        targetView(imageView).
                        build();
                floating.startFloating(element);
            }
        });
        icCommandLine = (ImageView) findViewById(R.id.icCommandLine);
        icCommandLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView targetView = new TextView(FloatViewFragment.this.getActivity());
                targetView.setText("Hello World");

                FloatingElement element = new FloatingBuilder().floatTransiton(new AlphaFloatingTransition())
                        .anchorView(v)
                        .targetView(targetView)
                        .offsetY(-v.getMeasuredHeight())
                        .build();
                floating.startFloating(element);
            }
        });

        icLike = (ImageView) findViewById(R.id.icLike);
        icLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View targetView = View.inflate(FloatViewFragment.this.getActivity(),R.layout.floatview_ic_like,null);
                FloatingElement element = new FloatingBuilder().floatTransiton(new TranslationFloatingTransition())
                        .anchorView(v)
                        .targetView(targetView)
                        .build();
                floating.startFloating(element);
            }
        });
        icStar = (ImageView) findViewById(R.id.icStar);
        icStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView targetView = new ImageView(FloatViewFragment.this.getActivity());
                targetView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup
                        .LayoutParams.WRAP_CONTENT));
                targetView.setImageResource(R.drawable.star);

                FloatingElement element = new FloatingBuilder().floatTransiton(new StartFloatingTransition())
                        .anchorView(v)
                        .targetView(targetView)
                        .build();
                floating.startFloating(element);
            }
        });


        icBeer = (ImageView) findViewById(R.id.icBeer);
        icBeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView targetView = new ImageView(FloatViewFragment.this.getActivity());
                targetView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup
                        .LayoutParams.WRAP_CONTENT));
                targetView.setImageResource(R.drawable.beer);

                FloatingElement element = new FloatingBuilder().floatTransiton(new BearFloating())
                        .anchorView(v)
                        .targetView(targetView)
                        .build();
                floating.startFloating(element);
            }
        });

        icPlane = (ImageView) findViewById(R.id.icPlane);
        icPlane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView targetView = new ImageView(FloatViewFragment.this.getActivity());
                int width = 30;
                targetView.setLayoutParams(new ViewGroup.LayoutParams(ResUtil.dp2px(width), ResUtil.dp2px(width)));
                targetView.setImageResource(R.drawable.floating_plane);

                FloatingElement element = new FloatingBuilder().floatTransiton(new AirPlaneFloating())
                        .anchorView(v)
                        .targetView(targetView)
                        .build();
                floating.startFloating(element);
            }
        });
    }
}
