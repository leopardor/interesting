package com.shu.thread;

import com.shu.thread.PrintThread;

/**
 * @description: some desc
 * @author: arlenshu
 * @date: 2021/2/28 9:29
 */
public class PrintThreadTest {

    public static void main(String[] args) {
        PrintThread printThread = new PrintThread();

        Thread a = new Thread(printThread, "Thread-A");
        Thread b = new Thread(printThread, "Thread-B" );

        a.start();
        b.start();

        // 等待A B线程执行完
        try {
            a.join();
            System.out.println("A线程执行完毕");
            b.join();
            System.out.println("B线程执行完毕");
            System.out.println(Thread.currentThread().getName() + " 线程开始执行");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
