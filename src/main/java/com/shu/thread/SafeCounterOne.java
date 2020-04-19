package com.shu.thread;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SafeCounterOne implements SafeCounter {
    private static volatile int count = 0;
    private static int taskCount = 100;
    private static CountDownLatch countDownLatch = new CountDownLatch(taskCount);
    // 信号量 相当于一种许可 只有获取到许可 才能继续往下执行，执行完释放许可即可
    private static Semaphore semaphore = new Semaphore(10);
    private static AtomicInteger  safeCount = new AtomicInteger(0);
    private static volatile boolean flag = true;

    @Override
    public void increment() {
        synchronized (SafeCounter.class) {
            countDownLatch.countDown();
            count++;
        }
    }

    @Override
    public int incrementAndReturn() {
        safeCount.incrementAndGet();

        return safeCount.get();
    }

    public static void main(String[] args) {
        SafeCounter counter = new SafeCounterOne();

        int availableProcessors = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                availableProcessors,
                2 * availableProcessors + 1,
                60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(10 * availableProcessors),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

//        ThreadPoolExecutor.DiscardOldestPolicy
//        Executors.newFixedThreadPool(); // 线程数固定大小的线程池
//        Executors.newCachedThreadPool(); // 线程数无限可以无线增加的线程池
//        Executors.newScheduledThreadPool(); //
//        Executors.newSingleThreadExecutor(); // 单个线程的线程池
        System.out.println("新生线程池可执行的线程数: " + executor.getActiveCount());
        executor.submit(() -> {
           while (flag) {
               System.out.println("AAAA");
           }
        });
        for (int i = 0; i < taskCount; ++i) {
            executor.execute(counter::increment);
        }
        System.out.println("提交任务后，新生线程池可执行的线程数: " + executor.getActiveCount());


//        Future<Integer> future = executor.submit(counter::incrementAndReturn);
//        try {
//            Integer result = future.get(1, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (TimeoutException e) {
//            e.printStackTrace();
//        }

        try {
            countDownLatch.await();
            flag = false;
            // 等待全部线程执行完 然后再打印结果
            System.out.println(count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

}
