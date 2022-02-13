package app;

import java.util.*;

    public class Variant {

        private final int number;
        private ArrayList<String> casConforme;
        private final HashMap<Variant, ArrayList<Integer>> distances;
        private final ArrayList<ArrayList<String>> activities;

        public Variant(int number) {
            this.number = number;
            this.distances = new HashMap<>();
            this.activities = new ArrayList<>();
        }

        public Variant(int number, ArrayList<String> casConforme) {
            this.number = number;
            this.distances = new HashMap<>();
            this.casConforme = casConforme;
            this.activities = new ArrayList<>();
        }

        public void ajouterDistance(Variant variant, Integer distance) {
            if (distances.containsKey(variant))
                distances.get(variant).add(distance);
            else
                distances.put(variant, new ArrayList<>(List.of(distance)));
        }

        public ArrayList<String> getCasConforme() {
            return casConforme;
        }

        public void setCasConforme(ArrayList<String> casConforme) {
            this.casConforme = casConforme;
        }

        public ArrayList<ArrayList<String>> getActivities() {
            return activities;
        }

        public void setActivities(ArrayList<String> activity) {
            this.activities.add(activity);
        }

        public Double getMoyenneDistance(Variant variant) {
            return distances.get(variant).stream().mapToDouble(a -> a).average().orElse(0.0);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Variant variant = (Variant) o;
            return number == variant.number;
        }

        @Override
        public int hashCode() {
            return Objects.hash(number);
        }

    }

