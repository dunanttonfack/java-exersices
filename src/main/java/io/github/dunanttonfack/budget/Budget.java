package io.github.dunanttonfack.budget;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Budget {
    private final List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction t) {

        Objects.requireNonNull(t, "Transaction must not be null");
        transactions.add(t);
    }

    public BigDecimal getBalance() {

        BigDecimal balance = BigDecimal.ZERO;

        for (Transaction t : transactions) {
            balance = balance.add(t.signedAmount());
        }
        return balance;
    }

    public Set<String> getCategories() {
        Set<String> categories = new TreeSet<>();

        for (Transaction t : transactions) {

            if (t instanceof Expense e) {
                categories.add(e.getCategory());
            }
        }

        return categories;
    }

    public Map<String, BigDecimal> getTotalByCategory() {

        Map<String, BigDecimal> totalByCategory = new TreeMap<>();

        for (Transaction t : transactions) {
            if (t instanceof Expense e) {
                totalByCategory.merge(e.getCategory(), e.getAmount(), BigDecimal::add);
            }
        }
        return totalByCategory;
    }

    public List<Transaction> getTransactions() {

        return List.copyOf(transactions);
    }
}
