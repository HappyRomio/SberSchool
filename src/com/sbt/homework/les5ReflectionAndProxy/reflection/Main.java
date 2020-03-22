package com.sbt.homework.les5ReflectionAndProxy.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {
    public static void main (String... args) throws IllegalAccessException {
        TestReflectionParent test = new TestReflectionChild();

        //all methods
        Class clazz = test.getClass();
        /*while (clazz != null) {
            System.out.println("-------------"+clazz.getName()+"-------------");
            for(Method method : clazz.getDeclaredMethods()) {
                System.out.println(method.getName());
            }
            clazz = clazz.getSuperclass();
        }
        */
        //get getters
        Method[] methods = clazz.getMethods();
        for(Field field : clazz.getDeclaredFields()){
            for(Method method : methods) {
                if (field.getType().getSimpleName().equalsIgnoreCase("boolean")) {
                    if (method.getName().equals("is" + field.getName()) || method.getName().equals("has" + field.getName())) {
                        System.out.println(method.getName());
                    }
                } else {
                    if (method.getName().equals("get" + field.getName())) {
                        System.out.println(method.getName());
                    }
                }
            }
        }

        //public static final string name = name
        boolean flag = true;
        for(Field field : clazz.getFields()){
            if(field.getType().getSimpleName().equalsIgnoreCase("String")){
                int modifier = field.getModifiers();
                if(Modifier.isStatic(modifier) && Modifier.isPublic(modifier) && Modifier.isFinal(modifier)){
                    String s = "";
                    if(!field.getName().equals(field.get(s))){
                        flag = false;
                    }
                }
            }
        }

        System.out.println(flag);

    }
}
