package com.tcl.widget.demo.ui.base;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Jerry
 * @Description:
 * @date 2016/9/30 14:02
 * @copyright TCL-MIG
 */

public abstract class ABaseFragment extends Fragment {
    static final String TAG = "AFragment-Base";



    public ViewGroup rootView;// 根视图

    private boolean destory = false;

    // UI线程的Handler
    Handler mHandler = new Handler(Looper.getMainLooper()) {

    };

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof BaseActivity)
            ((BaseActivity) activity).addFragment(toString(), this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (inflateContentView() > 0) {
            ViewGroup contentView = (ViewGroup) inflater.inflate(inflateContentView(), null);
            contentView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));

            setupContentView(inflater, contentView, savedInstanceState);

            return getContentView();
        }

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    /**
     * 根据ContentView初始化视图
     *
     * @param inflater
     * @param contentView
     * @param savedInstanceState
     */
    protected void setupContentView(LayoutInflater inflater, ViewGroup contentView, Bundle savedInstanceState) {
        setContentView(contentView);


        layoutInit(inflater, savedInstanceState);
    }
    /**
     * 子类重写这个方法，初始化视图
     *
     * @param inflater
     * @param savedInstanceSate
     */
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {

    }

    public void setContentView(ViewGroup view) {
        this.rootView = view;
    }

    /**
     * 根视图
     *
     * @return
     */
    public ViewGroup getContentView() {
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (savedInstanceState == null)
            requestData();
    }

    /**
     * Action的home被点击了
     *
     * @return
     */
    public boolean onHomeClick() {
        return onBackClick();
    }

    /**
     * 返回按键被点击了
     *
     * @return
     */
    public boolean onBackClick() {
        return false;
    }

    /**
     * 初次创建时默认会调用一次
     */
    public void requestData() {

    }

    /**
     * 延迟时间刷新
     *
     * @param delay
     */
    public void requestDataDelay(long delay) {
        Runnable requestDelayRunnable = new Runnable() {

            @Override
            public void run() {

                requestData();
            }

        };

    }

    public void requestDataOutofdate() {
        requestData();
    }



    public View findViewById(int viewId) {
        if (getContentView() == null)
            return null;

        return getContentView().findViewById(viewId);
    }



    void setViewVisiable(View v, int visibility) {
        if (v != null && v.getVisibility() != visibility)
            v.setVisibility(visibility);
    }




    @Override
    public void onDestroy() {
        destory = true;

        try {
            // 4.1.1必报错，不知道为什么
            super.onDestroy();
        } catch (Exception e) {

        }

    }

    public boolean isDestory() {
        return destory;
    }

    public boolean isActivityRunning() {
        return getActivity() != null;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        if (getActivity() != null && getActivity() instanceof BaseActivity)
            ((BaseActivity) getActivity()).removeFragment(this.toString());
    }



    /**
     * 指定Fragment的LayoutID
     *
     * @return
     */
    abstract protected int inflateContentView();

    /**
     * 指定Activity的ContentViewID
     *
     * @return
     */
    protected int inflateActivityContentView() {
        return -1;
    }



    public ViewGroup getRootView() {
        return rootView;
    }



}
