package com.sbt.homework.les2Collections;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MyHomeWork {

    final static String fileName = "D:/2.txt";

    public static class MyList<T> extends ArrayList<T> {
        @Override
        public Iterator<T> iterator() {
            return new ReverseIterator<>(this);
        }
    }

    public static class ReverseIterator<T> implements Iterator<T> {

        private int cursor;
        private int nextIndex;
        private ArrayList<T> array;
        ReverseIterator(ArrayList<T> arrayList){
            array = arrayList;
            cursor = array.size()-1;
        }
        @Override
        public boolean hasNext() {
            return cursor >=0;
        }

        @Override
        public T next() {
            int i = cursor;
            if (i < 0)
                throw new NoSuchElementException();
            T elem = array.get(cursor);
            cursor--;
            return elem;
        }

        @Override
        public void remove() {
            array.remove(cursor);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

        Set<String> uniqWords = new HashSet<>();
        Set<String> sortedWords = new TreeSet<>(Comparator.comparing(String::length).thenComparing(String.CASE_INSENSITIVE_ORDER));
        Map<String, Integer> countEachWord = new HashMap<>();
        Deque<String> reverse = new ArrayDeque<>();
        List<String> listWithReverseIterator = new MyList<>();
        List<String> simpleArrayList = new ArrayList<>();


        while(bufferedReader.ready()){
            String line = bufferedReader.readLine();
            for(String s : line.split(" ")){
                uniqWords.add(s);
                sortedWords.add(s);
                countEachWord.merge(s,1, (oldValue, newValue)-> oldValue + newValue);
            }
            reverse.addFirst(line);
            listWithReverseIterator.add(line);
            simpleArrayList.add(line);
        }

        bufferedReader.close();

        System.out.println("Количество раличных слов в файле: " + uniqWords.size());
        System.out.println("*************");
        sortedWords.forEach(System.out::println);
        System.out.println("*************");
        countEachWord.forEach((k,v) -> System.out.println("Слово: '" + k + "' встречается " + v + " раз."));
        System.out.println("*************");
        System.out.println("Все строки в обратном порядке: ");
        reverse.forEach(System.out::println);
        System.out.println("*************");
        System.out.println("Все строки в обратном порядке используя итератор: ");
        Iterator<String> iter = listWithReverseIterator.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
        System.out.println("*************");
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int ind = 1;
        while (ind!=0){
            System.out.println("Введите номер строки в файле (для выхода введите 0): ");
            ind = Integer.valueOf(bufferedReader.readLine());
            if(ind!=0) {
                if (ind > simpleArrayList.size()) {
                    System.out.println("Число превышает количество строк в файле, введите еще раз (всего в файле " + simpleArrayList.size() + " строк).");
                } else {
                    System.out.println("Строка " + ind + ": " + simpleArrayList.get(ind - 1));
                }
            }
        }

    }
}
