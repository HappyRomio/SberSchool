package com.sbt.homework.les5ReflectionAndProxy.dz;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String... args) throws InvocationTargetException, IllegalAccessException {
        TestFrom testFrom = new TestFrom();
        TestTo testTo = new TestTo();

        System.out.println(testFrom.getString());
        System.out.println(testTo.getString());
        assign(testTo,testFrom);
        System.out.println(testFrom.getString());
        System.out.println(testTo.getString());


    }

    public static void assign(Object to, Object from) throws InvocationTargetException, IllegalAccessException {
        List<Method> getters = new ArrayList<>();
        List<Method> setters = new ArrayList<>();

        for(Method method : from.getClass().getMethods()){
            if(isGetter(method)){
                getters.add(method);
            }
        }

        for(Method method : to.getClass().getMethods()){
            if(isSetter(method)){
                setters.add(method);
            }
        }

        for(Method methodGet : getters){
            for(Method methodSet : setters) {
                if(isSetterCorespondGetter(methodGet,methodSet)){
                    Object result = methodGet.invoke(from, null);
                    methodSet.invoke(to,result);
                }
            }
        }

    }

    public static boolean isGetter(Method method){
        String mName = method.getName();
        if(!mName.startsWith("get") && !mName.startsWith("is") && !mName.startsWith("has"))      return false;
        if(method.getParameterTypes().length != 0)   return false;
        return !void.class.equals(method.getReturnType());
    }

    public static boolean isSetter(Method method){
        if(!method.getName().startsWith("set")) return false;
        return method.getParameterTypes().length == 1;
    }

    public static boolean isSetterCorespondGetter(Method getter, Method setter) {
        String gName = getter.getName();
        if(gName.startsWith("get")){
            gName = gName.replaceFirst("get","");
        } else if (gName.startsWith("is")){
            gName = gName.replaceFirst("is","");
        } else if (gName.startsWith("has")){
            gName = gName.replaceFirst("has","");
        }

        String sNameFormCompare = "set" + gName;
        String sName = setter.getName();

        if(!sNameFormCompare.equals(sName)) {
            return false;
        }

        Class getterReturnType = getter.getReturnType();
        Class[] setterParam = setter.getParameterTypes();

        if(getterReturnType == setterParam[0]){
            return true;
        }

        List<Class> getterSuperTypes = new ArrayList<>();
        Class temp;
        while ((temp=getterReturnType.getSuperclass())!=null){
            getterSuperTypes.add(temp);
        }

        for(Class clazz : getterSuperTypes){
            if(clazz == setterParam[0]){
                return true;
            }
        }

        return false;
    }
}
