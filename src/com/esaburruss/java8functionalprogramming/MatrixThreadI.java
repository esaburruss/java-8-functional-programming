package com.esaburruss.java8functionalprogramming;

import java.util.concurrent.Callable;

public class MatrixThreadI implements Callable<MatrixThreadI> {
    private int id;
    private int[] a;
    private int[][] b;
    private int[] result;
    private int bY;
    
    public MatrixThreadI(int i, int[] a, int[][] b) {
        this.id = i;
        this.a = a;
        this.b = b;
        this.bY = b[0].length;
        result = new int[bY];
    }

    @Override
    public MatrixThreadI call() throws Exception {
        if(this.id % 250 == 0)
            System.out.println("Thread[" + this.id + "] started.");
        for(int j = 0; j < bY; j++) {
            for(int k = 0; k < a.length; k++) {
                result[j] += a[k] * b[k][j];
            }
        }
        //System.out.println("Thread[" + this.id + "] finished.");
        return this;
    }
    
    public int getId() {
        return this.id;
    }
    
    public int[] getResult() {
        return this.result;
    }
}
