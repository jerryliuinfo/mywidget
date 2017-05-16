package com.tcl.widget.demo.ui.widget.supercleaner;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Created by jerryliu on 2017/5/16.
 */

public class PermitView extends FrameLayout {
    public PermitView(@NonNull Context context) {
        super(context,null);
    }

    public PermitView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    public PermitView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init(Context context){

    }



}
