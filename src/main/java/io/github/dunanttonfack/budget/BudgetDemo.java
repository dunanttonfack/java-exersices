package io.github.dunanttonfack.budget;

import java.math.BigDecimal;
import java.util.Set;

public class BudgetDemo {

    public static void main(String[] args) {

        Budget budget = new Budget();

        budget.addTransaction(new Income(new BigDecimal("1400.00"), "Monthly salary", "Salary"));
        budget.addTransaction(new Income(new BigDecimal("50.00"), "Tutoring lesson", "Tutoring"));
        budget.addTransaction(new Expense(new BigDecimal("450.00"), "Monthly rent", "Housing"));
        budget.addTransaction(new Expense(new BigDecimal("120.00"), "Supermarket", "Groceries"));
        budget.addTransaction(new Expense(new BigDecimal("30.00"), "Cinema", "Leisure"));
        budget.addTransaction(new Expense(new BigDecimal("80.00"), "Market", "Groceries"));

        System.out.println("Balance: " + budget.getBalance());

        Set<String> categories = budget.getCategories();
        System.out.println("Categories: " + categories);
        System.out.println("Total: " + budget.getTotalByCategory());

        try {
            budget.addTransaction(null);
            System.out.println("ERROR: no exception for null");
        } catch (NullPointerException e) {
            System.out.println("OK: " + e.getMessage());
        }


        try {
            budget.getTransactions().add(new Expense(new BigDecimal("40.00"), "Market", "Groceries"));
            System.out.println("ERROR: the list should be read-only");
        } catch (UnsupportedOperationException e) {
            System.out.println("OK: the transaction list is read-only");
        }
        System.out.println("Number of transactions: " + budget.getTransactions().size());
    }
}
