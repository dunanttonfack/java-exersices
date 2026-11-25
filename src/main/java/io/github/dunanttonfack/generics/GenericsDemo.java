package io.github.dunanttonfack.generics;

import io.github.dunanttonfack.budget.Category;
import io.github.dunanttonfack.budget.Expense;
import io.github.dunanttonfack.budget.Income;
import io.github.dunanttonfack.budget.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class GenericsDemo {

    public static void main(String[] args) {

        // ===== 1. InMemoryRepository<Transaction> =====
        System.out.println("=== Repository of Transactions ===");
        InMemoryRepository<Transaction> transactionRepo = new InMemoryRepository<>();
        transactionRepo.save(new Income(new BigDecimal("1400.00"), "Monthly salary", LocalDate.of(2026, 11, 1), "Salary"));
        transactionRepo.save(new Expense(new BigDecimal("450.00"), "Monthly rent", LocalDate.of(2026, 11, 1), Category.HOUSING));
        transactionRepo.save(new Expense(new BigDecimal("120.00"), "Supermarket", LocalDate.of(2026, 11, 5), Category.GROCERIES));

        System.out.println("Count: " + transactionRepo.count());                       // 3

        Optional<Transaction> firstExpense = transactionRepo.findFirst(t -> t instanceof Expense);
        firstExpense.ifPresentOrElse(
                t -> System.out.println("First expense: " + t.getDescription()),        // Monthly rent
                () -> System.out.println("No expense found"));

        Optional<Transaction> bigOne = transactionRepo.findFirst(
                t -> t.getAmount().compareTo(new BigDecimal("5000")) > 0);
        System.out.println("Transaction above 5000 present? " + bigOne.isPresent());   // false

        // ===== 2. InMemoryRepository<String> : la MÊME classe, un autre type =====
        System.out.println("\n=== Repository of Strings ===");
        InMemoryRepository<String> skillRepo = new InMemoryRepository<>();
        skillRepo.save("Java");
        skillRepo.save("Spring");
        skillRepo.save("SQL");

        System.out.println("All: " + skillRepo.findAll());                             // [Java, Spring, SQL]
        System.out.println("First starting with S: "
                + skillRepo.findFirst(s -> s.startsWith("S")).orElse("none"));          // Spring

        // ===== 3. Pair<A, B> =====
        System.out.println("\n=== Pair ===");
        Pair<Category, BigDecimal> rent = new Pair<>(Category.HOUSING, new BigDecimal("450.00"));
        System.out.println(rent);                                                       // Pair[first=HOUSING, second=450.00]
        System.out.println("Category: " + rent.first() + ", amount: " + rent.second());

        Pair<String, Integer> lesson = new Pair<>("Generics", 19);
        System.out.println(lesson);                                                     // Pair[first=Generics, second=19]

        // ===== 4. GenericUtils.max =====
        System.out.println("\n=== GenericUtils.max ===");
        System.out.println("Max of numbers: " + GenericUtils.max(List.of(3, 9, 4)));               // 9
        System.out.println("Max of words: " + GenericUtils.max(List.of("pear", "apple", "kiwi"))); // pear
        System.out.println("Max of amounts: " + GenericUtils.max(List.of(
                new BigDecimal("450.00"), new BigDecimal("120.00"), new BigDecimal("30.00"))));    // 450.00

        // ===== 5. Error tests =====
        System.out.println("\n=== Error tests ===");
        try {
            List<Integer> empty = List.of();
            GenericUtils.max(empty);
            System.out.println("ERROR: no exception for empty list");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            skillRepo.save(null);
            System.out.println("ERROR: no exception for null item");
        } catch (NullPointerException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            transactionRepo.findAll().add(null);
            System.out.println("ERROR: findAll should be read-only");
        } catch (UnsupportedOperationException | NullPointerException e) {
            System.out.println("OK: findAll is read-only");
        }
    }
}
