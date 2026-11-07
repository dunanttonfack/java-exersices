package io.github.dunanttonfack.budget;

import java.math.BigDecimal;

public class Income extends Transaction {

    private String source;

    public Income(BigDecimal amount, String description, String source) {
        super(amount, description);
        if (source.isBlank()) throw new IllegalArgumentException("Source must be not Blank");
        this.source = source;
    }

    @Override
    public BigDecimal signedAmount() {

        return getAmount();
    }

    public String getSource() {
        return source;
    }

    @Override
    public String getType() {
        return "INCOME ";
    }
}
