package com.tcl.widget.demo.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Window;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.ui.base.ABaseFragment;
import com.tcl.widget.demo.ui.widget.coordinate.DependencyView;
import com.tcl.widget.demo.uti.NLog;
import com.tcl.widget.demo.uti.SystemUtility;

/**
 * Created by jerry on 2017/2/26.
 */

public class CoordinateLayoutFragment extends ABaseFragment {
    private static final String TAG = CoordinateLayoutFragment.class.getSimpleName();

    @Override
    public int inflateContentView() {
        return R.layout.fragment_coordinate;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        int statusBarHeight = SystemUtility.getStatusBarHeight(getActivity());
        int actionBarHeight = 0;
        DependencyView dependencyView = (DependencyView) findViewById(R.id.dependency);
        dependencyView.setStatusBarAndActionBarHeight(100);

        NLog.d(TAG, "statusBarHeight = %s, actionBarHeight = %s ", statusBarHeight,actionBarHeight);
        int appHeith = SystemUtility.getAppHeight(getActivity());
        NLog.d(TAG, "appHeith = %s ", appHeith);
    }

    public int getActionBarHeight(Activity ac, int statusBarHeight){
        int contentTop = ac.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
        //statusBarHeight是上面状态栏的高度
        int titleBarHeight = contentTop - statusBarHeight;
        return titleBarHeight;
    }
}
