package io.github.dunant74;

public class MaxInArray {

    public static int maxArray(int[] array){
        if(array == null){
            return -1;
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
