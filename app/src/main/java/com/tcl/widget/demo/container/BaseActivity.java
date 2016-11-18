package com.tcl.widget.demo.container;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.ui.base.ABaseFragment;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangdan on 15-1-16.
 */
public class BaseActivity extends AppCompatActivity {

    private View rootView;

    private Toolbar mToolbar;

    protected Context mContext;

    // 当有Fragment Attach到这个Activity的时候，就会保存
    private Map<String, WeakReference<ABaseFragment>> fragmentRefs;

    public void addFragment(String tag, ABaseFragment fragment) {
        fragmentRefs.put(tag, new WeakReference<>(fragment));
    }

    public void removeFragment(String tag) {
        fragmentRefs.remove(tag);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fragmentRefs = new HashMap<>();
        super.onCreate(savedInstanceState);
        mContext = BaseActivity.this;
    }

    /**
     * 在baseActivity中加入初始化统计参数的方法
     */
    @Override
    public void setContentView(int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
    }

    protected View getRootView() {
        return rootView;
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        rootView = view;
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup
                .LayoutParams.MATCH_PARENT));

        rootView = view;

    }


    public ActionBar getMDActionBar() {
        if (mToolbar == null) {
            getMDToolbar();
        }
        return getSupportActionBar();
    }

    public Toolbar getMDToolbar() {
        if (mToolbar == null) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (mToolbar != null) setSupportActionBar(mToolbar);
        }

        return mToolbar;
    }

}
