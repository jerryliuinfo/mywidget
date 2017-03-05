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

public class Button2Behavior extends CoordinatorLayout.Behavior<Button> {
    private static final String TAG = Button2Behavior.class.getSimpleName();
    public Button2Behavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Button child, View dependency) {
        return dependency instanceof DependencyView;
    }

    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Button child, View dependency) {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) child.getLayoutParams();

        int left = dependency.getLeft();
        int top = dependency.getTop() + dependency.getMeasuredHeight() + child.getMeasuredHeight();
        params.leftMargin = left;
        if (top > 806){
            top = 806;
        }
        NLog.d(TAG, "Button2Behavior onDependentViewChanged left = %s, top = %s", left,top);
        params.topMargin = top;
        child.setLayoutParams(params);
        return true;

    }
}
