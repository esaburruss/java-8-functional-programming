package com.esaburruss.java8functionalprogramming;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MainClass {

    public static void main(String[] args) {
        Consumer<String> messageConsumer = message -> System.out.println(message);
        messageConsumer.accept("Hello World!");
        
        List<Integer> arrList = getList(10, i -> i % 2 == 0);
        arrList.forEach(System.out::println);
    }

    public static List<Integer> getList(int x, Predicate<Integer> predicate) {
        ArrayList<Integer> arrList = new ArrayList<Integer>();
        for(int i = 0; i < x; i++) {
            if(predicate.test(i))
                arrList.add(i);
        }
        return arrList;
    }
}
