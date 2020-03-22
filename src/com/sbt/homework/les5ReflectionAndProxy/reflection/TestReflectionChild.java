package com.sbt.homework.les5ReflectionAndProxy.reflection;

public class TestReflectionChild extends TestReflectionParent{
    private final static String MONDAY = "MONDAY";
    private final static String TESTSTRING = "TESTSTRING";
    public final static String MONDAY1 = "monday1";
    public final static String TESTSTRING2 = "TESTSTRING2";
    boolean commonBoolean;
    Boolean classBoolean;

    public static String getMONDAY() {
        return MONDAY;
    }

    public static String getTESTSTRING() {
        return TESTSTRING;
    }

    public static String getMONDAY1() {
        return MONDAY1;
    }

    public static String getTESTSTRING2() {
        return TESTSTRING2;
    }

    public void TestChildPublic(){
    }

    private String TestChildPrivate(){
        return "Test";
    }

    protected Integer TestChildProtected() {
        return 0;
    }
}
