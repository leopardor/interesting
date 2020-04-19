package com.shu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class MyJdkProxy implements InvocationHandler {

    //需要代理的目标对象
    private Object targetObject;

    public Object createProxyInstance(Object targetObject) {
        this.targetObject = targetObject;

        // 三个参数:当前对象类的加载器、当前对象类实现的接口、代理类
        return Proxy.newProxyInstance(this.targetObject.getClass().getClassLoader(),
                this.targetObject.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("方法入参: " + Arrays.toString(args));

        return method.invoke(targetObject, args);
    }
}
