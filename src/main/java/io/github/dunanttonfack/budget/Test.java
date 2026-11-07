package io.github.dunanttonfack.budget;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Test {
    public static void main(String[] args) {

        List<String> staedten = new ArrayList<>();
        staedten.add("Düsseldorf");
        staedten.add("Stuttgart");
        staedten.add("Köln");
        staedten.add("Düsburg");
        staedten.add("Osnabrück");

        staedten.add(2, "Braunschweig");

        staedten.remove("Düsburg");

        ListIterator<String> it = staedten.listIterator(staedten.size());

        while (it.hasPrevious()) {
            System.out.println(it.previousIndex());
            System.out.println(it.previous());
        }
    }
}
