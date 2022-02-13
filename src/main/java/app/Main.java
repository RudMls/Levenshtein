package app;

import org.decimal4j.util.DoubleRounder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        // Initialisation des variantes
        HashMap<Integer, Variant> variants = new HashMap<>() {
            {
                // Variant conforme
                put(1, new Variant(1));
                put(2, new Variant(2));
                put(6, new Variant(6));
                // Variant non conforme
                put(3, new Variant(3));
                put(4, new Variant(4));
                put(7, new Variant(7));
                put(8, new Variant(8));
                put(9, new Variant(9));
            }};

        // Fichier json
        ArrayList<Event> events = LoadJson.getEvents("files\\events.json");

        // Liste des évènements
        events.forEach(event -> {
            variants.get(event.getVariant()).setActivities(event.getActivities());
            switch (event.getCaseKey()) {
                case 13 -> variants.get(1).setCasConforme(event.getActivities());
                case 6 -> variants.get(2).setCasConforme(event.getActivities());
                case 3 -> variants.get(6).setCasConforme(event.getActivities());
            }
        });


        variants.forEach((key, value) -> {
            // Variant 1, 2, 6
            if (List.of(1, 2, 6).contains(key)) {
                value.getActivities().forEach(activity -> {
                    value.ajouterDistance(value,
                            Levenshtein.calculate(value.getCasConforme(), activity));
                });
            } else {
                // Variant 3, 4, 7, 8, 9
                value.getActivities().forEach(activity -> {
                    // Distance <-> Variant 1
                    value.ajouterDistance(variants.get(1),
                            Levenshtein.calculate(variants.get(1).getCasConforme(), activity)
                    );
                    // Distance <-> Variant 2
                    value.ajouterDistance(variants.get(2),
                            Levenshtein.calculate(variants.get(2).getCasConforme(), activity)
                    );
                    // Distance <-> Variant 6
                    value.ajouterDistance(variants.get(6),
                            Levenshtein.calculate(variants.get(6).getCasConforme(), activity)
                    );
                });
            }
        });

        // Base
        System.out.println("P1");
        System.out.println("Variant 1 (Scénario 13) = " + variants.get(1).getCasConforme());
        System.out.println("Variant 2 (Scénario 6) =  " + variants.get(2).getCasConforme());
        System.out.println("Variant 6 (Scénario 3) =  " + variants.get(6).getCasConforme());
        System.out.println();

        // Distance de Levenshtein
        System.out.println("Distance de Levenshtein");
        System.out.println("\t\t\t Variant 1 \t Variant 2 \t Variant 6");
        System.out.println("Cas conformes");
        System.out.print("\t\t\t " + DoubleRounder.round(variants.get(1).getMoyenneDistance(variants.get(1)), 2));
        System.out.print("\t\t " + DoubleRounder.round(variants.get(2).getMoyenneDistance(variants.get(2)), 2));
        System.out.print("\t\t " + DoubleRounder.round(variants.get(6).getMoyenneDistance(variants.get(6)), 2));
        System.out.println();
        System.out.println("Cas non conformes");
        variants.forEach((key, value) -> {
            if (!List.of(1, 2, 6).contains(key)) {
                System.out.print("Variant " + key);
                System.out.print("\t " + DoubleRounder.round(value.getMoyenneDistance(variants.get(1)), 2));
                System.out.print("\t\t " + DoubleRounder.round(value.getMoyenneDistance(variants.get(2)), 2));
                System.out.print("\t\t " + DoubleRounder.round(value.getMoyenneDistance(variants.get(6)), 2));
                System.out.println();
            }
        });

    }

}
