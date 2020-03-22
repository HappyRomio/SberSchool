package com.sbt.homework.les5ReflectionAndProxy.proxy;

public class SimpleClass implements TestProxy {
    @Override
    public void method1() {
        System.out.println("Simple class hello");
    }

    @Override
    public void method2() {

    }

    @Override
    public String method3() {
        return null;
    }
}
