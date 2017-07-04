package com.tcl.widget.demo.ui.widget.github.floatview.transition;

import android.graphics.Path;
import android.graphics.PathMeasure;

/**
 * Created by jerryliu on 2017/7/4.
 */

public class FloatingPath {
    private Path mPath;
    private PathMeasure mPathMeasure;

    public FloatingPath() {
    }

    public static FloatingPath create(Path path, boolean forceClosed){
        FloatingPath floatingPath = new FloatingPath();
        floatingPath.mPath = path;
        floatingPath.mPathMeasure = new PathMeasure(path,forceClosed);
        return floatingPath;
    }


    public Path getPath() {
        return mPath;
    }

    public PathMeasure getPathMeasure() {
        return mPathMeasure;
    }
}
