package io.github.dunant74;

public class SumOfNumbers {

    public static void main(String[] args) {

        int ergebnis = 0;
        for (int i = 1; i <= 100; i++){
            ergebnis += i;
        }

        System.out.print(ergebnis);
    }
}
