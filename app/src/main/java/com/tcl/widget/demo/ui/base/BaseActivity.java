package com.tcl.widget.demo.ui.base;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.tcl.widget.demo.R;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


/**
 * @author liangni
 * @Description:
 * @date 2016/3/2 15:17
 * @copyright TCL-MIG
 */
public class BaseActivity extends AppCompatActivity {

    protected static final String TAG = BaseActivity.class.getSimpleName();

    // 当有Fragment Attach到这个Activity的时候，就会保存
    private Map<String, WeakReference<ABaseFragment>> fragmentRefs;

    private View rootView;

    private Toolbar mToolbar;

    protected Context mContext;

    private long mStayTime;


    protected String mPermissionRequest = "";

    public String getmPermissionRequest() {
        return mPermissionRequest;
    }


    public void setmPermissionRequest(String mPermissionRequest) {
        this.mPermissionRequest = mPermissionRequest;
    }




    private static WeakReference<BaseActivity> mRunningActivty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fragmentRefs = new HashMap<>();
        super.onCreate(savedInstanceState);
        mContext=BaseActivity.this;
    }

    public ActionBar getMDActionBar() {
        if (mToolbar == null) {
            getMDToolbar();
        }
        return getSupportActionBar();
    }

    public void setMDActionBarNull() {
        mToolbar = null;
    }

    public FragmentManager getMDFragmentManager() {
        return getFragmentManager();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mStayTime= System.currentTimeMillis();
        //如果上次是home键退出，并且不是重启的
    }


    //设置标题颜色
    protected void setTitleBarColor(int color){
        getMDActionBar().setBackgroundDrawable(new ColorDrawable(color));
    }

    public Toolbar getMDToolbar() {
        if (mToolbar == null) {
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            if (mToolbar != null) {
//
//                mToolbar.setNavigationIcon(R.drawable.tool_bar_icon);
//                mToolbar.setLogo(R.drawable.tool_bar_icon);
                setSupportActionBar(mToolbar);
            }
        }

        return mToolbar;
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public void addFragment(String tag, ABaseFragment fragment) {
        fragmentRefs.put(tag, new WeakReference<>(fragment));
    }

    public void removeFragment(String tag) {
        fragmentRefs.remove(tag);
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        mStayTime= System.currentTimeMillis();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(mPermissionRequest)) {
            mPermissionRequest = "";
        }
        mRunningActivty = new WeakReference<>(this);
        //StatisticsAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //StatisticsAgent.onPause(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //停止时候需要处理的事情
        onStopAction();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (onHomeClick()) return true;
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    protected boolean onHomeClick() {
        return onBackClick();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            if (onBackClick()) return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    public boolean onBackClick() {
        if (fragmentRefs != null){
            Set<String> keys = fragmentRefs.keySet();
            for (String key : keys) {
                WeakReference<ABaseFragment> fragmentRef = fragmentRefs.get(key);
                if (fragmentRef != null){
                    ABaseFragment fragment = fragmentRef.get();
                    if (fragment != null && fragment.onBackClick()) return true;
                }
            }
        }

        finish();
        return true;
    }

    protected void onStopAction(){

    }

    /**
     * 返回停留时间
     * @return
     */
    public long getStayTime(){
        return System.currentTimeMillis()-mStayTime;
    }

    /**
     * 以Toast形式显示一个消息
     *
     * @param msg
     */
    public void showMessage(CharSequence msg) {
    }

    /**
     * @param msgId
     */
    public void showMessage(int msgId) {
        showMessage(getText(msgId));
    }

    @Override
    public void finish() {
        try {
            super.finish();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static WeakReference<BaseActivity> getRunningActivity(){
        return mRunningActivty;
    }

    public void resetStayTime(){
        mStayTime= System.currentTimeMillis();
    }

    //刷新语言
    public void refreshToolBarLanguage(@StringRes int resId){
        getMDActionBar().setTitle(resId);

    }



}