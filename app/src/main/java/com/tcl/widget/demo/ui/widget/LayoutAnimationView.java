package com.tcl.widget.demo.ui.widget;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by jerryliu on 2017/5/29.
 */

public class LayoutAnimationView extends FrameLayout {

    public LayoutAnimationView(@NonNull Context context) {
        super(context);
    }

    public LayoutAnimationView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public LayoutAnimationView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
