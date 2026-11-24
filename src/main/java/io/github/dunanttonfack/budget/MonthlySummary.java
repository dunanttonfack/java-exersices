package io.github.dunanttonfack.budget;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.util.Objects;

public record MonthlySummary(YearMonth month, BigDecimal income, BigDecimal expenses) {

    public MonthlySummary {                       // constructeur compact
        Objects.requireNonNull(month, "Month must not be null");
        Objects.requireNonNull(income, "Income must not be null");
        Objects.requireNonNull(expenses, "Expenses must not be null");
    }

    public BigDecimal balance() {
        return income.subtract(expenses);
    }
}
