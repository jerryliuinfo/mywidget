package com.tcl.widget.demo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.bean.PieBean;
import com.tcl.widget.demo.ui.base.ABaseFragment;
import com.tcl.widget.demo.ui.widget.PieWiew;

import java.util.ArrayList;

/**
 * @author Jerry
 * @Description:
 * @date 2016/11/18 10:13
 * @copyright TCL-MIG
 */

public class PieViewFragmentA extends ABaseFragment {
    PieWiew pieWiew;
    @Override
    protected int inflateContentView() {
        return R.layout.activity_pie;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);
        pieWiew = (PieWiew) rootView.findViewById(R.id.pie_view);
        pieWiew.setStartAngel(-90);
        //commit10
        pieWiew.setData(generateDatas());
    }

    private ArrayList<PieBean> generateDatas(){
        ArrayList<PieBean> datas = new ArrayList<>();
        for (int i = 0; i < 3; i++){
            PieBean bean = null;
            if (i == 0){
                bean = new PieBean("张三", 60);
            }else if (i == 1){
                bean = new PieBean("李四", 80);
            }else if (i == 2){
                bean = new PieBean("王五", 100);
            }
            datas.add(bean);
        }
        return datas;
    }
}
