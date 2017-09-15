package com.esaburruss.java8functionalprogramming;

import java.time.Clock;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class MainClass {

    public static void main(String[] args) {
        matrix();
    }
    
    public static void matrix() {
        /*int[][] a = new int[][] {
            {3, -1, 2},
            {2,  0, 1},
            {1,  2, 1}
        };
        int[][] b = new int[][] {
                {2, -1, 1},
                {0, -2, 3},
                {3,  0, 1}
        }; */
        
        int[][] a = getMatrix(2500,2500);
        int[][] b = getMatrix(2500,2500);
        
        long instant1 = Instant.now().toEpochMilli();
        int[][] result1 = Matrix.getMatrixNoThread(a, b);
        long instant2 = Instant.now().toEpochMilli();
        System.out.println("Time: " + (instant2 - instant1));
        int[][] result2;
        try {
            result2 = Matrix.getMatrixFromThread(a, b);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long instant3 = Instant.now().toEpochMilli();
        System.out.println("Time: " + (instant3 - instant2));
    }
    
    public static int[][] getMatrix(int x, int y) {
        int[][] matrix = new int[y][x];
        for(int i = 0; i < y; i++) {
            for(int j = 0; j < x; j++) {
                matrix[i][j] = (i + j) % 10;
            }
        }
        return matrix;
    }
    
    public static void basicL() {
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
