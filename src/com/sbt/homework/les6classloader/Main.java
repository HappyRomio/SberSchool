package com.sbt.homework.les6classloader;

import test1.secondPlugin.Plugin;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;


public class Main {

    public static void main(String... args) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        PluginManager pm = new PluginManager("D:\\test1" );
        Plugin plugin  = (Plugin)pm.load("secondPlugin", "SecondPlugin").getDeclaredConstructor().newInstance();
        plugin.doUsefull();
        System.out.println(plugin.getClass().getClassLoader().getName());

    }


}
