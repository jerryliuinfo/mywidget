package com.tcl.widget.demo.linkedblockqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Jerry
 * @Description:
 * @date 2016/12/21 14:17
 * @copyright TCL-MIG
 */

public class TestBlockQueue {
    public class Basket{
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(3);

        public void produce(){
        }
    }
}
