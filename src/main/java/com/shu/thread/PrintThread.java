package com.shu.thread;

import java.util.concurrent.Semaphore;

/**
 * @description: 线程交替打印
 * @author: arlenshu
 * @date: 2021/2/28 9:24
 */
public class PrintThread implements Runnable {

    private static Integer count = 1;

    @Override
    public void run() {
        synchronized (this) {
            while (count <= 100) {
                System.out.println(Thread.currentThread().getName() + ": " + count++);
                this.notify();
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 避免最后一次执行的线程一直处于阻塞状态
            this.notifyAll();
        }
    }

}
