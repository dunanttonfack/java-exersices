package io.github.dunanttonfack.budget;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Set;

public class BudgetDemo {

    public static void main(String[] args) {

        Budget budget = new Budget();

        budget.addTransaction(new Income(new BigDecimal("1400.00"), "Monthly salary", "Salary", LocalDate.of(2026, 11, 1)));
        budget.addTransaction(new Income(new BigDecimal("50.00"), "Tutoring lesson", "Tutoring", LocalDate.of(2026, 11, 8)));
        budget.addTransaction(new Expense(new BigDecimal("450.00"), "Monthly rent", Category.HOUSING, LocalDate.of(2026, 11, 1)));
        budget.addTransaction(new Expense(new BigDecimal("120.00"), "Supermarket", Category.GROCERIES, LocalDate.of(2026, 11, 5)));
        budget.addTransaction(new Expense(new BigDecimal("30.00"), "Cinema", Category.LEISURE, LocalDate.of(2026, 11, 12)));
        budget.addTransaction(new Expense(new BigDecimal("80.00"), "Market", Category.GROCERIES, LocalDate.of(2026, 10, 28)));
        System.out.println("Balance: " + budget.getBalance());

        Set<Category> categories = budget.getCategories();
        System.out.println("Categories: " + categories);
        System.out.println("Total: " + budget.getTotalByCategory());
        System.out.println("Total Expense: " + budget.getTotalExpenses());

        System.out.println("Expenses above 100: " + budget.getExpensesAbove(new BigDecimal("100")).size());   // 2
        budget.getTransactionsSortedByAmount()
                .forEach(t -> System.out.println(t.getAmount()));   // 1400.00, 450.00, 120.00, 80.00, 50.00, 30.00

        try {
            budget.addTransaction(null);
            System.out.println("ERROR: no exception for null");
        } catch (NullPointerException e) {
            System.out.println("OK: " + e.getMessage());
        }


        try {
            budget.getTransactions().add(new Expense(new BigDecimal("40.00"), "Market", Category.GROCERIES, LocalDate.of(2026, 11, 1)));
            System.out.println("ERROR: the list should be read-only");
        } catch (UnsupportedOperationException e) {
            System.out.println("OK: the transaction list is read-only");
        }
        System.out.println("Number of transactions: " + budget.getTransactions().size());

        // -------------- TESTS Exceptions ---------------------------------
        System.out.println("\n");

        try {
            budget.checkLimit(new BigDecimal("500"));
            System.out.println("Within budget");
        } catch (BudgetLimitExceededException e) {
            System.out.println("Alert: " + e.getMessage());
        } finally {
            System.out.println("Limit check finished");
        }

        try {
            budget.checkLimit(new BigDecimal("1000"));
            System.out.println("Within budget");
        } catch (BudgetLimitExceededException e) {
            System.out.println("Alert: " + e.getMessage());
        } finally {
            System.out.println("Limit check finished");
        }

        try {
            budget.addTransaction(new Expense(new BigDecimal("-80.00"), "Market", Category.GROCERIES, LocalDate.of(2026, 11, 1)));
            System.out.println("ERROR: no exception for negative amount");
        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Amount check finished");
        }

        System.out.println("\n=== Monthly summaries ===");
        MonthlySummary november = budget.getMonthlySummary(YearMonth.of(2026, 11));
        System.out.println(november);
        System.out.println("November balance: " + november.balance());

        MonthlySummary october = budget.getMonthlySummary(YearMonth.of(2026, 10));
        System.out.println(october);
        System.out.println("October balance: " + october.balance());

        System.out.println("\n=== Largest expense ===");
        budget.findLargestExpense().ifPresentOrElse(
                e -> System.out.println("Largest expense: " + e.getDescription() + " (" + e.getAmount() + ")"),
                () -> System.out.println("No expenses"));

        new Budget().findLargestExpense().ifPresentOrElse(
                e -> System.out.println("Largest expense: " + e.getDescription()),
                () -> System.out.println("Empty budget: No expenses"));
    }
}
