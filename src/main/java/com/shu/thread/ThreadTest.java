package com.shu.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {



    // 消费队列
    private static List<Object> list = new ArrayList<>();

    // 最大容量
    private static final int MAX_SIZE = 100;

    // 锁
    private static Lock lock = new ReentrantLock();
    private static Condition fullCondition = lock.newCondition();
    private static Condition emptyCondition = lock.newCondition();

    // 生产者
    private static void produce() {

        lock.lock();
        while (list.size() == MAX_SIZE) {
            System.out.println("生产者线程: " + Thread.currentThread().getName() + "队列已满：");
            emptyCondition.signalAll();
            try {
                fullCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(new Object());
        System.out.println("生产者线程：" + Thread.currentThread().getName() + "共生产新产品，共有: " + list.size());
        lock.unlock();
    }

    // 消费者
    private static void consume() {
        lock.lock();
        while (list.size() < 1) {
            System.out.println("消费者线程: " + Thread.currentThread().getName() + "队列为空：");
            fullCondition.signal();
            try {
                emptyCondition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        list.remove(0);
        System.out.println("消费者线程: " + Thread.currentThread().getName() + " 正在消费, 队列还剩: " + list.size());
        lock.unlock();
    }


    private static void printNumberThree() {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        Thread A = new Thread(() -> {
            lock.lock();
            System.out.println("A 1");
            try {
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A 2");
            System.out.println("A 3");
            lock.unlock();
        });


        Thread B = new Thread(() -> {
            Thread.yield(); // 保证A先执行
            lock.lock();
            System.out.println("B 1");
            System.out.println("B 2");
            System.out.println("B 3");
            condition.signal();
            lock.unlock();
        });

        A.start();
        B.start();
    }

    // 线程B等待线程A打印完才打印
    private static void printNumberTwo() {

        Thread A = new Thread(() -> {
            printNumber("A");
        });

        Thread B = new Thread(() -> {
            try {
                // B等待A执行完
                A.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            printNumber("B");
        });

        A.start();
        B.start();
    }


    private static void printNumberOne() {
        Thread A = new Thread(() -> {
            printNumber("A");
        });

        Thread B = new Thread(() -> {
            printNumber("B");
        });

        A.start();
        B.start();
    }


    private static void printNumber(String threadName) {
        int i = 0;

        while (i++ < 3) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Thread: " + threadName + " Print: " + i);
        }
    }

    private static int num = 100;
    private static void printNumber (int num) {

        Thread t1 = new Thread(doPrint());
        Thread t2 = new Thread(doPrint());

        t1.start();
        // 当前线程让出执行权，保证t1先执行
        Thread.yield();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static Runnable doPrint() {
        return () -> {
            while (num > 0) {
                synchronized (ThreadTest.class) {
                    System.out.println(Thread.currentThread() + "-" + (num--));
                    ThreadTest.class.notify();
                    if (num > 0) {
                        try {
                            ThreadTest.class.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
    }

    public static void main(String[] args) {

        // 线程轮流依次打印 1 1 2 2 3 3
//        printNumberOne();

        // 线程B等待线程A打印完 在打印
//        printNumberTwo();

        // A先打印 然后等待B的通知继续打印
//        printNumberThree();

        // 生产者 消费者模拟
//        Thread productThread = new Thread( () -> {
//            for (;;)
//                produce();
//        });
//
//        Thread consumeThread = new Thread(() -> {
//            for (;;)
//                consume();
//        });
//
//        productThread.start();
//        consumeThread.start();

        printNumber(10);
    }
}
