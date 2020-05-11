package org.bpcl.ramdayal.ramdayalpannalal;

import java.util.HashMap;
import java.util.Map;

public class ZombieMatrixSolution {

    public static void main(String[] args){
        int[][] grid = { { 0, 1, 1, 0, 1 },
                         { 0, 1, 0, 1, 0 },
                         { 0, 0, 0, 0, 1 },
                         { 0, 1, 0, 0, 0 }
                        };
        System.out.println(humanDays(grid));

    }

    private static Map<String, Integer> getHumanCount(int[][] matrix) {
        Map<String, Integer> countMap = new HashMap<>();
        int humanCount = 0, zombieCount = 0;

        for(int i = 0; i < matrix.length; i++){
            for(int j=0; j< matrix[i].length; j++){
                if (matrix[i][j] == 1) {
                    zombieCount++;
                } else {
                    humanCount++;
                }
            }
        }
        countMap.put("humanCount", humanCount);
        countMap.put("zombieCount", zombieCount);
        return countMap;
    }

    private static int humanDays(int[][] matrix){

        if(getHumanCount(matrix).get("zombieCount") == 0){
            return -1;
        }

        int noOfDays = 0;
        int[][] newMatrix = new int[matrix.length][matrix[0].length];

        while(getHumanCount(matrix).get("humanCount") > 0){
            for(int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    if (matrix[i][j] == 1) {
                        newMatrix[i][j] = 1;
                        if( i >=0){
                            if( j >= 0 && j < matrix[i].length) {
                                if(j > 0){
                                    newMatrix[i][j-1] = 1;
                                }
                                if(j < matrix[i].length-1) {
                                    newMatrix[i][j + 1] = 1;
                                }
                            }
                            if(i>0){
                                newMatrix[i-1][j] = 1;
                            }
                            if(i < matrix.length-1){
                                newMatrix[i+1][j] = 1;
                            }
                        }

                    }
                }
            }
            matrix = newMatrix;
            noOfDays++;
        }

        return noOfDays;
    }
}




