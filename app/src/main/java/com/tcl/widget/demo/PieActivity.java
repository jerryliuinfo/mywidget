package com.tcl.widget.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.tcl.widget.demo.bean.PieBean;
import com.tcl.widget.demo.widget.PieWiew;

import java.util.ArrayList;

/**
 * Created by lenovo on 2016/9/16.
 */

public class PieActivity extends AppCompatActivity {
    PieWiew pieWiew;
    public static void launch(Activity from) {
        Intent intent = new Intent(from, PieActivity.class);
        from.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie);
        pieWiew = (PieWiew) findViewById(R.id.pie_view);
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
