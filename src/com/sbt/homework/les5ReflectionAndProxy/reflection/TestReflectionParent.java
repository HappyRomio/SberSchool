package com.sbt.homework.les5ReflectionAndProxy.reflection;

public class TestReflectionParent implements TestReflectionInterface {

    public void TestParentPublic(){
    }

    private String TestParentPrivate(){
        return "Test";
    }

    protected Integer TestParentProtected() {
        return 0;
    }

    @Override
    public void firstTestInterfaceMethod() {

    }

    @Override
    public Double secondTestInterfaceMethod() {
        return null;
    }
}
