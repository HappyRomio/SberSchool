package com.sbt.homework.les3Generics;



public class Main {
    public static void main(String... args){
       MyLinkedList<String> list = new MyLinkedList<>();

       /*

        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
    */

        for (String s : list) {
            System.out.println(s);
        }
        //list.forEach(System.out::println);

    }
}
