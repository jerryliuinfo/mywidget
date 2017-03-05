package com.tcl.widget.demo.ui.widget.coordinate;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import com.tcl.widget.demo.uti.NLog;

/**
 * Created by jerry on 2017/2/26.
 */

public class Button1Behavior extends CoordinatorLayout.Behavior<Button> {

    private static final String TAG = Button1Behavior.class.getSimpleName();

    public Button1Behavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Button child, View dependency) {
        return dependency instanceof DependencyView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Button child, View dependency) {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) child.getLayoutParams();

        int left = dependency.getLeft() + dependency.getMeasuredWidth() + 50;
        int top = dependency.getTop();

        NLog.d(TAG, "Button1Behavior onDependentViewChanged left = %s, top = %s", left,top);
        params.leftMargin = left;
        params.topMargin = top;
        child.setLayoutParams(params);
        return true;

    }
}
