package com.tcl.widget.demo;

import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Window;

import com.tcl.widget.demo.ui.base.BaseFragment;
import com.tcl.widget.demo.uti.NLog;

import java.util.Arrays;

/**
 * @author Jerry
 * @Description:
 * @date 2016/10/29 11:49
 * @copyright TCL-MIG
 */

public class MatrixFragment extends BaseFragment {
    private static final String TAG = MatrixFragment.class.getSimpleName();

    @Override
    protected int inflateContentView() {
        return R.layout.fragment_matrix;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        //test1();
        test2();

    }

    private void test1(){
        // 初始数据为三个点 (0, 0) (80, 100) (400, 300)
        float[] pts = new float[]{0, 0, 80, 100, 400, 300};

        // 构造一个matrix，x坐标缩放0.5
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 1f);

        // 输出pts计算之前数据
        NLog.i(TAG, "before: " + Arrays.toString(pts));

        // 调用map方法计算
        matrix.mapPoints(pts);

        // 输出pts计算之后数据
        NLog.i(TAG, "after : " + Arrays.toString(pts));
    }

    private void test2(){
        // 初始数据为三个点 (0, 0) (80, 100) (400, 300)
        float[] src = new float[]{0, 0, 80, 100, 400, 300};
        float[] dst = new float[6];

        // 构造一个matrix，x坐标缩放0.5
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f, 1f);

        // 输出计算之前数据
        Log.i(TAG, "test2 before: src="+ Arrays.toString(src));
        Log.i(TAG, "test2 before: dst="+ Arrays.toString(dst));

        // 调用map方法计算
        matrix.mapPoints(dst,src);

        // 输出计算之后数据
        Log.i(TAG, "test2 after : src="+ Arrays.toString(src));
        Log.i(TAG, "test2 after : dst="+ Arrays.toString(dst));
    }


}
