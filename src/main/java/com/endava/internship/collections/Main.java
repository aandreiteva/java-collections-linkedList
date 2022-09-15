package com.endava.internship.collections;

import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List list = new MyLinkedList();
        list.add("Hi");
        list.add(12);
        list.add("There");
        list.add(12);
        ((MyLinkedList)list).print();
        ((MyLinkedList) list).addFirst(9);
        list.remove(0);
        list.add("loskadlk");
        ((MyLinkedList)list).print();
        list.remove("loskadlk");
        ((MyLinkedList)list).print();
    }
}
