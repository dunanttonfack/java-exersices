package io.github.dunanttonfack;

public class EvenOrOdd {
    public static void evenOrOdd (int [] array){
        if (array != null) {
            for (int j : array) {
                if (j % 2 == 0) {
                    System.out.println(j + " is even");
                } else {
                    System.out.println(j + " is odd");
                }
            }
        }

    }

    public static void main(String[] args) {
        int[] numbers = {12, 45, 7, 89, 23, 56};

        evenOrOdd(numbers);
    }
}
