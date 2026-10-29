package io.github.dunanttonfack;

import java.util.Objects;

public class StringUtils {

    public static String reverse(String s) {

        Objects.requireNonNull(s, "Input must not be null");

        //if (s == null) throw new NullPointerException();
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; i--) {
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }

    public static boolean isPalindrome(String s) {

        if (s == null) {
            throw new NullPointerException();
        }
        s = s.toLowerCase();
        String reverseString = reverse(s);

        return s.equals(reverseString);

    }

    public static int countVowels(String s) {

        if (s == null) throw new NullPointerException();

        int count = 0;

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);

            switch (c) {
                case 'a', 'A', 'e', 'E', 'u', 'U', 'i', 'I', 'o', 'O':
                    count++;
                    break;
            }
        }

        return count;
    }

    public static void main(String[] args) {

        System.out.println(reverse("Dunant"));

        String s = "Kayak";

        System.out.println(isPalindrome(s));
        System.out.println(s);

        String s1 = "Programming";

        System.out.println(countVowels(s1));
    }
}
