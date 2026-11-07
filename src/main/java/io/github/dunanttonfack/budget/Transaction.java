package io.github.dunanttonfack.budget;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Transaction implements Reportable {

    private BigDecimal amount;
    private String description;

    public Transaction(BigDecimal amount, String descriptoin) {

        Objects.requireNonNull(amount, "Amount must not be null");
        Objects.requireNonNull(descriptoin, "Description must not be null");

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        //if (description.isBlank()) {
        // throw new IllegalArgumentException("Description must not be blank");
        // }
        this.amount = amount;
        this.description = descriptoin;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toReportLine() {
        return getType() + " | " + description + " | " + signedAmount();
    }

    public abstract String getType();

    public abstract BigDecimal signedAmount();
}
