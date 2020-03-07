package com.sbt.homework.les3Generics;



import java.util.*;

public class MyLinkedList<E> implements Iterable<E> {
    private int size = 0;
    private Node<E> first;
    private Node<E> last;

    public void add(E elem) {
        Node<E> newNode = new Node<>(elem, null, null);
        if (size == 0) {
            first=last = newNode;
            size++;
        } else {
            newNode.prev = last;
            last.next = newNode;
            last = newNode;
            size++;
        }
    }

    public void add(int index, E elem){
        if (index >= size ){
            throw new IndexOutOfBoundsException();
        }

        Node<E> newNode = new Node<>(elem, null, null);

        if(index==0){
            first.prev = newNode;
            newNode.next = first;
            first = newNode;
            size++;
        }else if(index==size-1){
            last.next = newNode;
            newNode.prev = last;
            last = newNode;
            size++;
        } else {
            Node<E> temp = first;
            for(int i=0; i < index; i++){
                temp = temp.next;
            }
            newNode.next = temp.next;
            newNode.prev = temp;
            temp.next.prev = newNode;
            temp.next = newNode;
            size++;
        }
    }

    public E get(int index) {
        if(index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> temp = first;
        for(int i =0; i <=index; i++){
            temp = temp.next;
        }
        return temp.elem;
    }

    public E remove (int index){
        if(index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> temp = first;
        for(int i =0; i <=index; i++){
            temp = temp.next;
        }
        E removedElem = temp.elem;
        temp.prev.next = temp.next;
        temp.next.prev = temp.prev;

        temp.prev = null;
        temp.next = null;

        size--;

        return removedElem;
    }

    public boolean addAll(Collection<? extends E> c) {
            for(E elem : c){
                add(elem);
            }
            return true;
    }

    public boolean copy(Collection<? extends E> c) {
        first = null;
        last = null;
        size = 0;
        for(E elem : c){
            add(elem);
        }

        return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> currNode = first;

            @Override
            public boolean hasNext() {
                return currNode != null;
            }

            @Override
            public E next() {
                E result = currNode.elem;
                currNode = currNode.next;
                return result;
            }
        };
    }

    private class Node<T> {
        T elem;
        Node<T> next;

        Node prev;

        Node(T elem, Node<T> prev, Node<T> next){
            this.elem = elem;
            this.prev = prev;
            this.next = next;
        }
    }

}
