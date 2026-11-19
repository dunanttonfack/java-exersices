package io.github.dunanttonfack;

import java.util.List;
import java.util.stream.Collectors;

public class AufgabeStreams {

    public static void main(String[] args) {
        /*
        List<Integer> nombres = List.of(4, 7, 2, 9, 4, 12, 7, 1);

        //Kein Mehrfachkommen
        List<Integer> newList = nombres.stream().distinct()
                .filter(n -> n > 3)
                .map(n -> n * 10)
                .sorted()
                .toList();
        System.out.println(newList);

        //Zahlen, die größer als 3 sind.
        List<Integer> groesserDrei = nombres.stream().filter(n -> n > 3).toList();

        //Alle Zahlen mit 10 multiplitieren
        List<Integer> malZehn = nombres.stream().map(n -> n * 10).toList();

         */

        List<String> noms = List.of("Anna", "Bob", "Clara", "Bob");

        String texte = noms.stream()
                .collect(Collectors.joining(" | ", " [ ", " ]"));
        System.out.println(texte);


    }
}
