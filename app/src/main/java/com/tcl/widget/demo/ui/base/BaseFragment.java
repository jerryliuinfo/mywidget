package com.tcl.widget.demo.ui.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tcl.widget.demo.container.BaseActivity;

/**
 * @author Jerry
 * @Description:
 * @date 2016/9/30 14:02
 * @copyright TCL-MIG
 */

public abstract class BaseFragment extends Fragment {
    protected ViewGroup rootView;// 根视图
    protected Context mContext;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof BaseActivity) ((BaseActivity) activity).addFragment(toString(), this);
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        mContext = getActivity();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (inflateContentView() > 0) {
            rootView = (ViewGroup) inflater.inflate(inflateContentView(), null);
            rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));

            _layoutInit(inflater, savedInstanceState);

            layoutInit(inflater, savedInstanceState);

            return rootView;
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    abstract protected int inflateContentView();

    /**
     * A*Fragment重写这个方法
     *
     * @param inflater
     * @param savedInstanceSate
     */
    void _layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
    }

    /**
     * 子类重写这个方法，初始化视图
     *
     * @param inflater
     * @param savedInstanceSate
     */
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
    }
}
