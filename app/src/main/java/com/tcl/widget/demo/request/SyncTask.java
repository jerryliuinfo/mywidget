package com.tcl.widget.demo.request;

import java.util.concurrent.CountDownLatch;

/**
 * Created by jerryliu on 2017/5/3.
 */

public class SyncTask implements Runnable {
    private CountDownLatch countDownLatch = new CountDownLatch(1);
    public Request request;
    @Override
    public void run() {
        if (request != null){
            request.setStatus(Request.Status.RUNNING);
        }

    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public void onFinish(){

    }
}
