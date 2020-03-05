package com.sbt.homework.les1Basic;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CurrencyManipulatorFactory {
    private static Map<String, CurrencyManipulator> map = new HashMap<>();
    private CurrencyManipulatorFactory(){

    }
    public static CurrencyManipulator getManipulatorByCurrencyCode(String currencyCode) {
            CurrencyManipulator manipulator = map.get(currencyCode.toUpperCase());
            if(manipulator==null){
                manipulator = new CurrencyManipulator(currencyCode.toUpperCase());
                map.put(currencyCode.toUpperCase(),manipulator);
                return manipulator;
            } else {
                return manipulator;
            }
    }
    public static Collection<CurrencyManipulator> getAllCurrencyManipulators(){
        return map.values();
    }
}
