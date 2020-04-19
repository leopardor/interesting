package com.shu.thread;

public interface SafeCounter {

    // 自增无返回值
    public void increment();

    // 自增并返回自增后的值
    public int incrementAndReturn();
}
