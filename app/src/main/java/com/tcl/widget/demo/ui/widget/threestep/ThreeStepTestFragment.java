package com.tcl.widget.demo.ui.widget.threestep;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageView;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.ui.base.ABaseFragment;

/**
 * Created by jerryliu on 2017/4/14.
 */

public class ThreeStepTestFragment extends ABaseFragment {

    public static final String TAG = ThreeStepTestFragment.class.getSimpleName();


    private Button btn;
    private PathView mPathView;

    private ImageView drawable_tint1;

    @Override
    protected int inflateContentView() {
        return R.layout.fragment_three_step_test;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        btn = (Button) findViewById(R.id.btn);


        mPathView = (PathView) findViewById(R.id.path_view);

        drawable_tint1 = (ImageView) findViewById(R.id.drawable_tint1);
        Drawable drawable = ContextCompat.getDrawable(getActivity(),R.drawable.hawk);
        drawable_tint1.setImageDrawable(tintDrawable(drawable, ContextCompat.getColorStateList(getActivity(),R.color.hawk_selector)));



    }

    public static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }




}


