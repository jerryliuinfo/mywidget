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

public class PermissionGuideView extends FrameLayout {
    public PermissionGuideView(@NonNull Context context) {
        super(context,null);
        init(context);
    }

    public PermissionGuideView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
        init(context);
    }

    public PermissionGuideView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){

    }

}
