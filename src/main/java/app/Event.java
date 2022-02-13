package app;

import java.util.ArrayList;

public record Event(int variant, int caseKey, ArrayList<String> activities) {

    public int getVariant() {
        return variant;
    }

    public int getCaseKey() {
        return caseKey;
    }

    public ArrayList<String> getActivities() {
        return activities;
    }

}

