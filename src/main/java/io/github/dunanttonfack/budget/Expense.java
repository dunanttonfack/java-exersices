package io.github.dunanttonfack.budget;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Expense extends Transaction {

    private Category category;

    public Expense(BigDecimal amount, String description, Category category, LocalDate date) {
        super(amount, description, date);
        this.category = Objects.requireNonNull(category, "Category must not be null");
    }

    @Override
    public BigDecimal signedAmount() {
        return getAmount().negate();
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String getType() {
        return "EXPENSE ";
    }
}
