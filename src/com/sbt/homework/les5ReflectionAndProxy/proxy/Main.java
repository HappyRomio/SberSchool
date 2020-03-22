package com.sbt.homework.les5ReflectionAndProxy.proxy;


import java.lang.reflect.Proxy;

public class Main {

    public static void main(String...args){

        TestProxy testProxy = new SimpleClass();
        TestProxy proxy = (TestProxy)Proxy.newProxyInstance(testProxy.getClass().getClassLoader(), testProxy.getClass().getInterfaces(),new SimpleHandler(testProxy));

        proxy.method1();
        proxy.method1();

    }

}

