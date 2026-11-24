package io.github.dunanttonfack.budget;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public abstract class Transaction implements Reportable {

    private final BigDecimal amount;
    private final String description;
    private final LocalDate date;

    public Transaction(BigDecimal amount, String description, LocalDate date) {
        Objects.requireNonNull(amount, "Amount must not be null");
        Objects.requireNonNull(description, "Description must not be null");
        Objects.requireNonNull(date, "Date must not be null");
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidAmountException("Amount must be positive, got: " + amount);
        }
        if (description.isBlank()) {
            throw new IllegalArgumentException("Description must not be blank");
        }
        this.amount = amount;
        this.description = description;
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
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
