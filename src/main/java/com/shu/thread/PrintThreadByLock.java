package com.shu.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: some desc
 * @author: arlenshu
 * @date: 2021/2/28 10:23
 */
public class PrintThreadByLock implements Runnable {

    private static Integer count = 1;

    private final static ReentrantLock lock = new ReentrantLock();

    private final static Condition condition = lock.newCondition();

    @Override
    public void run() {
        while (count <= 100) {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + ": " + count++);
            condition.signal();
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        // 保证最后执行的线程不被一直阻塞
        lock.lock();
        condition.signalAll();
        lock.unlock();
    }
}
