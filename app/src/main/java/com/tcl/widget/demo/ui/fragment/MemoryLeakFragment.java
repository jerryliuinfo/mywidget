package com.tcl.widget.demo.ui.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.tcl.widget.demo.R;
import com.tcl.widget.demo.ui.base.ABaseFragment;
import com.tcl.widget.demo.uti.NLog;

/**
 * Created by jerryliu on 2017/5/4.
 */

public class MemoryLeakFragment extends ABaseFragment {
    public static final String TAG = MemoryLeakFragment.class.getSimpleName();
    private static Bitmap mDogBitmap;
    private static Bitmap mGirlBitmap;

    @Override
    protected int inflateContentView() {
        return R.layout.framgent_momory_leak2;
    }

    @Override
    protected void layoutInit(LayoutInflater inflater, Bundle savedInstanceSate) {
        super.layoutInit(inflater, savedInstanceSate);

        Runnable runnable = new MyRunnable();
        new MyThread(runnable).start();
    }


    static class MyThread extends Thread{
        Runnable runnable;

        public MyThread( Runnable runnable) {
            this.runnable = runnable;
        }
    }


    static class MyRunnable implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(8000000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private static int count;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        count++;
        NLog.d(TAG, "onCreate count = %s", count);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        count--;
        NLog.d(TAG, "finalize count = %s", count);
    }
}
