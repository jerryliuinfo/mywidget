package com.tcl.widget.demo.bean;

/**
 * Created by lenovo on 2016/9/16.
 */

public class PieBean {
    public String name;
    public float value;
    public float percentage;

    public int color; //颜色
    public float angel; //角度

    public PieBean(String name, float value){
       this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "PieBean{" +
                "name='" + name + '\'' +
                ", value=" + value +
                ", percentage=" + percentage +
                ", angel=" + angel +
                '}';
    }
}
