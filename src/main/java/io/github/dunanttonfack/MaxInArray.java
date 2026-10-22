package io.github.dunanttonfack;

public class MaxInArray {

    public static int maxArray(int[] array){
        if(array == null || array.length == 0){
            throw new IllegalArgumentException("Array must not be null or empty");
        }
        int max = array[0];

        for(int i = 1; i < array.length; i++){
            if( array[i] > max) max = array[i];
        }
        return max;
    }

    public static void main(String[] args) {

        int[] numbers = {12, 45, 7, 89, 23, 56};

        System.out.println(maxArray(numbers));
    }
}
