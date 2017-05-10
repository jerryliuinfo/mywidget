package com.tcl.widget.demo.request;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by jerryliu on 2017/5/3.
 */

public class Request {
    public static final int DEFAULT_PERIODIC = 5;//失败之后下一次请求间隔
    private static final int MAX_RETRY_COUNT = 2;
    //重试次数
    private AtomicInteger retryCount = new AtomicInteger(1);

    private String adKey;

    private Status status = Status.FAILURE;

    //request的结果
    public enum Status {
        SUCCESS,
        FAILURE,
        RUNNING
    }

    public Request(String key) {
        adKey = key;
    }

    //失败之后的间隔时间，这个值默认肯定是0 单位是秒
    private long failedPeriodic;

    public long getPeriodic() {
        if (status == Status.FAILURE) {
            failedPeriodic = DEFAULT_PERIODIC;
        }
        return failedPeriodic;
    }


    public synchronized void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    /**
     * 该请求是否在请求中
     *
     * @return true是  false否
     */
    public synchronized boolean isRunning() {
        return getStatus() == Status.RUNNING;
    }
}
