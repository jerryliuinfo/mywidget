package com.tcl.widget.demo;

import android.app.Application;
import android.os.SystemClock;
import android.test.ApplicationTestCase;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    private static final String TAG = ApplicationTest.class.getSimpleName();

    public ApplicationTest() {
        super(Application.class);
    }

    public void testSeamphe() throws Exception {
        // 线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        // 只能5个线程同时访问
        final Semaphore semp = new Semaphore(5);
        // 模拟20个客户端访问
        for (int index = 0; index < 50; index++) {
            final int NO = index;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        // 获取许可
                        semp.acquire();
                        //Logger.d(TAG, "Accessing: " + NO);
                        Thread.sleep((long) (Math.random() * 3000));
                       // Logger.d(TAG, "before release-------" + semp.availablePermits());
                        // 访问完后，释放
                        semp.release();
                        //availablePermits()指的是当前信号灯库中有多少个可以被使用
                        //Logger.d(TAG, "after release-------" + semp.availablePermits());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            Thread.sleep(5 * 1000);
            exec.execute(run);
        }
        // 退出线程池
        exec.shutdown();
    }


}
