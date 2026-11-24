package io.github.dunanttonfack.budget;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Budget {
    private final List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction t) {

        Objects.requireNonNull(t, "Transaction must not be null");
        transactions.add(t);
    }

    public BigDecimal getBalance() {

        return transactions.stream()
                .map(Transaction::signedAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Set<Category> getCategories() {
        return transactions.stream()
                .filter(Expense.class::isInstance)
                .map(Expense.class::cast)
                .map(Expense::getCategory)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public Map<Category, BigDecimal> getTotalByCategory() {
        return transactions.stream()
                .filter(Expense.class::isInstance)
                .map(Expense.class::cast)
                .collect(Collectors.toMap(
                        e -> e.getCategory(),
                        Expense::getAmount,
                        BigDecimal::add,
                        () -> new EnumMap<>(Category.class)
                ));
    }

    public List<Transaction> getTransactions() {

        return List.copyOf(transactions);
    }

    public BigDecimal getTotalExpenses() {

        return transactions.stream()
                .filter(Expense.class::isInstance)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<Transaction> getExpensesAbove(BigDecimal min) {

        return transactions.stream()
                .filter(Expense.class::isInstance)
                .filter(expense -> expense.getAmount().compareTo(min) > 0)
                .collect(Collectors.toList());
    }

    public List<Transaction> getTransactionsSortedByAmount() {

        return transactions.stream()
                .sorted(Comparator.comparing(Transaction::getAmount).reversed())
                .toList();
    }

    public void checkLimit(BigDecimal limit) throws BudgetLimitExceededException {
        BigDecimal expenses = getTotalExpenses();
        if (expenses.compareTo(limit) > 0) {
            throw new BudgetLimitExceededException("Expenses " + expenses + " exceed limit " + limit);
        }
    }

    public MonthlySummary getMonthlySummary(YearMonth month) {
        Objects.requireNonNull(month, "Month must not be null");

        List<Transaction> ofMonth = transactions.stream()
                .filter(t -> YearMonth.from(t.getDate()).equals(month))
                .toList();

        BigDecimal income = ofMonth.stream()
                .filter(Income.class::isInstance)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal expenses = ofMonth.stream()
                .filter(Expense.class::isInstance)
                .map(Transaction::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new MonthlySummary(month, income, expenses);
    }

    public Optional<Expense> findLargestExpense() {
        return transactions.stream()
                .filter(Expense.class::isInstance)
                .map(Expense.class::cast)
                .max(Comparator.comparing(Expense::getAmount));
    }


}
