package com.tcl.widget.demo.ui.widget.github.floatview.transition;

/**
 * Created by jerryliu on 2017/7/4.
 */

public abstract class BaseFloatingPathTraniton implements FloatingPathTransition {
    private PathPostion mPathPostion;
    private FloatingPath mFloatingPath;
    private float[] mPos;
    public float getStartPosition(){
        return 0;
    }

    public float getEndPostion(){
        if (mFloatingPath == null){
            mFloatingPath = getFloatingPath();
        }
        if (mFloatingPath != null){
            return mFloatingPath.getPathMeasure().getLength();
        }
        return 0;
    }


    public PathPostion getFloatingPathPosition(float progress){
        if (mPathPostion == null){
            mPathPostion = new PathPostion();
        }

        if (mFloatingPath == null){
            mFloatingPath = getFloatingPath();
        }

        if (mPos == null){
            mPos = new float[2];
        }
        if (mFloatingPath != null){
            mFloatingPath.getPathMeasure().getPosTan(progress,mPos,null);
            mPathPostion.x = mPos[0];
            mPathPostion.y = mPos[1];
        }
        return mPathPostion;
    }


}
