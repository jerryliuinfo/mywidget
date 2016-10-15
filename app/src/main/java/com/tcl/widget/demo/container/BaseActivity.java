package com.tcl.widget.demo.container;


import android.support.v7.app.AppCompatActivity;

import com.tcl.widget.demo.ui.base.BaseFragment;

import java.lang.ref.WeakReference;
import java.util.Map;

/**
 * Created by wangdan on 15-1-16.
 */
public class BaseActivity extends AppCompatActivity {

    // 当有Fragment Attach到这个Activity的时候，就会保存
    private Map<String, WeakReference<BaseFragment>> fragmentRefs;

    public void addFragment(String tag, BaseFragment fragment) {
        fragmentRefs.put(tag, new WeakReference<>(fragment));
    }

    public void removeFragment(String tag) {
        fragmentRefs.remove(tag);
    }
}
