package com.tcl.widget.demo.request;

import android.text.TextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jerryliu on 2017/5/3.
 * request的存储类，主要是为了维护任务失败的时候处理
 */

public class RequestStorage {
    private final Map<String, Request> requestMap;

    public RequestStorage() {
        requestMap = new HashMap<>();
    }


    public synchronized void removeRequest(String key) {
        if (TextUtils.isEmpty(key)) {
            return;
        }
        if (requestMap.containsKey(key)) {
            requestMap.remove(key);
        }
    }

    public synchronized Request getRequest(String key) {
        if (TextUtils.isEmpty(key) || !requestMap.containsKey(key)) {
            return null;
        }
        return requestMap.get(key);
    }

    public synchronized void putRequest(String key, Request request) {
        if (TextUtils.isEmpty(key)) {
            return;
        }
        if (!requestMap.containsKey(key)) {
            requestMap.put(key, request);
        }
    }
}
