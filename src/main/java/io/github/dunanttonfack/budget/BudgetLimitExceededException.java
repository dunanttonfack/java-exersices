package io.github.dunanttonfack.budget;

public class BudgetLimitExceededException extends Exception {

    public BudgetLimitExceededException(String message) {
        super(message);
    }
}
