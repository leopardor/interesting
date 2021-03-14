package com.shu.thread;

/**
 * @description: some desc
 * @author: arlenshu
 * @date: 2021/2/28 10:29
 */
public class PrintThreadByLockTest {

    public static void main(String[] args) {
        PrintThreadByLock printThreadByLock = new PrintThreadByLock();

        Thread a = new Thread(printThreadByLock, "Thread-A");
        Thread b = new Thread(printThreadByLock, "Thread-B");

        a.start();
        Thread.yield();
        b.start();

        try {
            a.join();
            b.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
