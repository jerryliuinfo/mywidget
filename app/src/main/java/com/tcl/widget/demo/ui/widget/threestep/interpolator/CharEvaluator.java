package com.tcl.widget.demo.ui.widget.threestep.interpolator;

import android.animation.TypeEvaluator;

/**
 * Created by jerryliu on 2017/5/9.
 */

public class CharEvaluator implements TypeEvaluator<Character> {
    @Override
    public Character evaluate(float fraction, Character startValue, Character endValue) {
        int startInt  = (int)startValue;
        int endInt = (int)endValue;
        int curInt = (int)(startInt + fraction *(endInt - startInt));
        char result = (char)curInt;
        return result;
    }
}
