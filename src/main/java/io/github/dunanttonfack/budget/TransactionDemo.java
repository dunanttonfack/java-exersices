package io.github.dunanttonfack.budget;

import java.math.BigDecimal;

public class TransactionDemo {
    public static void main(String[] args) {
        Transaction[] transactions = {
                new Income(new BigDecimal("1400.00"), "Monthly salary", "Salary"),
                new Income(new BigDecimal("50.00"), "Tutoring lesson", "Tutoring"),
                new Expense(new BigDecimal("450.00"), "Monthly rent", "Housing"),
                new Expense(new BigDecimal("120.00"), "Supermarket", "Groceries"),
                new Expense(new BigDecimal("30.00"), "Cinema", "Leisure"),
                new Expense(new BigDecimal("80.00"), "Market", "Groceries")
        };
        BigDecimal balance = BigDecimal.ZERO;
        for (Transaction t : transactions) {
            balance = balance.add(t.signedAmount());
        }

        System.out.println(balance);

        int countExpense = 0;

        for (Transaction t : transactions) {
            if (t instanceof Expense e) {
                countExpense++;
                System.out.println("Category of Expense: " + e.getCategory());
            }
        }

        System.out.println("Number of Expense: " + countExpense);

        System.out.println(Reportable.header());
        for (Transaction t : transactions) {
            System.out.println(t.toReportLine());
        }

        System.out.println("\n=== Error tests ===");

        try {
            new Expense(new BigDecimal("-10"), "Test", "Food");
            System.out.println("ERROR: no exception for negative amount");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            new Income(BigDecimal.ZERO, "Test", "Salary");
            System.out.println("ERROR: no exception for zero amount");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            new Income(new BigDecimal("100"), "   ", "Salary");
            System.out.println("ERROR: no exception for blank description");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            new Expense(new BigDecimal("100"), "Test", "");
            System.out.println("ERROR: no exception for blank category");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            new Income(new BigDecimal("100"), "Test", " ");
            System.out.println("ERROR: no exception for blank source");
        } catch (IllegalArgumentException e) {
            System.out.println("OK: " + e.getMessage());
        }

        try {
            new Expense(null, "Test", "Food");
            System.out.println("ERROR: no exception for null amount");
        } catch (NullPointerException e) {
            System.out.println("OK: " + e.getMessage());
        }

    }


}
