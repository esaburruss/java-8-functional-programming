package com.esaburruss.java8functionalprogramming;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Matrix {
    
    public static int[][] getMatrixFromThread(int[][] a, int[][] b) throws InterruptedException {
        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<MatrixThreadI>> threads = new ArrayList<Callable<MatrixThreadI>>();
                
        int aY = a[0].length;
        int bX = b.length;
        
        if (aY != bX) {
            return null;
        }
        
        int aX = a.length;
        int bY = b[0].length;
        
        int[][] matrix = new int[aX][bY];
        
        for(int i = 0; i < aX; i++) {
            threads.add(new MatrixThreadI(i, a[i], b));
        }
        
        executor.invokeAll(threads).stream().map(future -> {
            try {
                return future.get();
            } catch (Exception ex) {
                throw new IllegalStateException(ex);
            }
        }).forEach((x) -> matrix[x.getId()] = x.getResult());
        
        return matrix;
    }
    
    public static int[][] getMatrixNoThread(int[][] a, int[][] b) {
        
        int aY = a[0].length;
        int bX = b.length;
        
        if (aY != bX) {
            return null;
        }
        
        int aX = a.length;
        int bY = b[0].length;
        
        int[][] matrix = new int[aX][bY];
        
        for(int i = 0; i < aX; i++) {
            if(i % 250 == 0)
                System.out.println(i);
            for(int j = 0; j < bY; j++) {
                for(int k = 0; k < aY; k++) {
                    matrix[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        
        return matrix;
    }
    
    public static int getCell(int[] a, int[]b) {
        return 0;
    }
 
    public static String toString(int[][] m) {
        String result = "";
        for(int i = 0; i < m.length; i++) {
            for(int j = 0; j < m[i].length; j++) {
                result += String.format("%4d", m[i][j]);
            }
            result += "\n";
        }
        return result;
    }
}
