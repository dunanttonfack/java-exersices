package io.github.dunanttonfack.budget;

public enum Category {

    HOUSING("Housing"),
    GROCERIES("Groceries"),
    LEISURE("Leisure"),
    TRANSPORT("Transport"),
    OTHER("Other");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
