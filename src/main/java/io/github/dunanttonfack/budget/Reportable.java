package io.github.dunanttonfack.budget;

public interface Reportable {
    //int number = 0; // implicitement public static final int number = 0;

    static String header() { // méthode avec un corps
        return "TYPE\t | DESCRIPTION\t | AMOUNT\t";
    }

    public abstract String toReportLine();// implicitement public abstract

    default String toReportBlock() {
        return "----\n" + toReportLine() + "\n----";
    }
}
