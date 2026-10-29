package io.github.dunanttonfack;

public class ClassesDemo {

    public static void main(String[] args) {

        // ===== 1. Book: valid objects =====
        System.out.println("=== Books ===");
        Book book1 = new Book("Clean Code", "Robert C. Martin", 464);
        Book book2 = new Book("Effective Java");
        Book book3 = new Book("Head First Java", "Kathy Sierra", 720);

        System.out.println(book1);   // toString() is called automatically
        System.out.println(book2);
        System.out.println(book3);

        System.out.println("Title of book1: " + book1.getTitle());
        System.out.println("Author of book2: " + book2.getAuthor());

        book2.setPages(412);
        System.out.println("book2 after setPages: " + book2);

        System.out.println("Books created: " + Book.getCount());   // expected: 3

        // ===== 2. Book: error cases =====
        System.out.println("\n=== Book errors ===");
        try {
            new Book("Bad Book", "Someone", -5);
            System.out.println("ERROR: no exception for negative pages");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            new Book("   ", "Someone", 100);
            System.out.println("ERROR: no exception for blank title");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            new Book(null, "Someone", 100);
            System.out.println("ERROR: no exception for null title");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            book1.setPages(0);
            System.out.println("ERROR: no exception for setPages(0)");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        System.out.println("Books created (failed ones not counted): " + Book.getCount());   // still 3

        // ===== 3. Rectangle =====
        System.out.println("\n=== Rectangles ===");
        Rectangle r1 = new Rectangle(2.5, 4.0);
        Rectangle r2 = new Rectangle(5, 5);

        System.out.println(r1);
        System.out.println("Area: " + r1.area());              // 10.0
        System.out.println("Perimeter: " + r1.perimeter());    // 13.0
        System.out.println("Is square: " + r1.isSquare());     // false

        System.out.println(r2);
        System.out.println("Area: " + r2.area());              // 25.0
        System.out.println("Perimeter: " + r2.perimeter());    // 20.0
        System.out.println("Is square: " + r2.isSquare());     // true

        try {
            new Rectangle(0, 3);
            System.out.println("ERROR: no exception for width 0");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // ===== 4. ArrayUtils =====
        System.out.println("\n=== ArrayUtils ===");
        int[] numbers = {10, 20, 30};
        double[] decimals = {1.5, 2.5, 3.0};

        System.out.println("sum(int[]): " + ArrayUtils.sum(numbers));         // 60
        System.out.println("sum(double[]): " + ArrayUtils.sum(decimals));     // 7.0
        System.out.println("average: " + ArrayUtils.average(numbers));        // 20.0
        System.out.println("max: " + ArrayUtils.max(4, 17, 9, 2));            // 17

        try {
            ArrayUtils.average(new int[0]);
            System.out.println("ERROR: no exception for empty array");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            ArrayUtils.max();
            System.out.println("ERROR: no exception for max()");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        // ===== 5. StringUtils =====
        System.out.println("\n=== StringUtils ===");
        System.out.println("reverse: " + StringUtils.reverse("Dunant"));                  // tnanuD
        System.out.println("isPalindrome(Kayak): " + StringUtils.isPalindrome("Kayak"));  // true
        System.out.println("isPalindrome(Java): " + StringUtils.isPalindrome("Java"));    // false
        System.out.println("countVowels: " + StringUtils.countVowels("Programming"));     // 3
    }
}
