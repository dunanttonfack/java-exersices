package io.github.dunanttonfack;

public class ArrayUtils {

    public static int sum(int[] numbers) {

        if (numbers == null) throw new NullPointerException();

        int sum = 0;

        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }

        return sum;
    }

    public static double sum(double[] numbers) {

        if (numbers == null) throw new NullPointerException();

        double sum = 0;

        for (int i = 0; i < numbers.length; i++) {
            sum += numbers[i];
        }

        return sum;
    }

    public static double average(int[] numbers) {

        if (numbers == null || numbers.length == 0) throw new IllegalArgumentException();
        int sum = sum(numbers);

        return (double) sum / numbers.length;
    }

    public static int max(int... numbers) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("At least one number is required");
        }
        int max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(sum(new int[]{10, 20, 30}));
    }
}
