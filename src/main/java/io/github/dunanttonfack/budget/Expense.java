package io.github.dunanttonfack.budget;

import java.math.BigDecimal;

public class Expense extends Transaction {

    private String category;

    public Expense(BigDecimal amount, String descriptoin, String category) {
        super(amount, descriptoin);
        if (category.isBlank()) throw new IllegalArgumentException("Category must be not Blank");
        this.category = category;
    }

    @Override
    public BigDecimal signedAmount() {
        return getAmount().negate();
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String getType() {
        return "EXPENSE ";
    }
}
