package io.github.dunanttonfack.budget;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
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

    public Set<String> getCategories() {
        return transactions.stream()
                .filter(Expense.class::isInstance)
                .map(Expense.class::cast)
                .map(Expense::getCategory)
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public Map<String, BigDecimal> getTotalByCategory() {
        return transactions.stream()
                .filter(Expense.class::isInstance)
                .map(Expense.class::cast)
                .collect(Collectors.toMap(
                        e -> e.getCategory(),
                        Expense::getAmount,
                        BigDecimal::add,
                        TreeMap::new
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

    public void checkLimit(BigDecimal limit) throws BudgetLimitExceededException {
        BigDecimal expenses = getTotalExpenses();
        if (expenses.compareTo(limit) > 0) {
            throw new BudgetLimitExceededException("Expenses " + expenses + " exceed limit " + limit);
        }
    }
}
