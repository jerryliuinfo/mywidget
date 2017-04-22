package com.tcl.widget.demo.bean;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by jerryliu on 2017/4/22.
 */

public class RequestType {
    /** @hide */
    @IntDef({TYPE_NEWS, TYPE_AD, TYPE_RECOMMAND})
    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE {}


    public static final int TYPE_NEWS = 0x00;


    public static final int TYPE_AD = 0x01;


    public static final int TYPE_RECOMMAND = 0x02;

    public void setType(@TYPE int type) {

    }
}
