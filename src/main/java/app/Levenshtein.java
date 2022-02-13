package app;

import java.util.ArrayList;
import java.util.Arrays;

public class Levenshtein {

    public static Integer calculate(ArrayList<String> compare, ArrayList<String> to) {
        int[][] dp = new int[compare.size() + 1][to.size() + 1];

        for (int i = 0; i <= compare.size(); i++) {
            for (int j = 0; j <= to.size(); j++) {

                if (i == 0) {
                    dp[i][j] = j;
                }
                else if (j == 0) {
                    dp[i][j] = i;
                }
                else {
                    dp[i][j] = min(dp[i - 1][j - 1]
                                    + NumOfReplacement(compare.get(i - 1), to.get(j - 1)), // replace
                            dp[i - 1][j] + 1, // delete
                            dp[i][j - 1] + 1); // insert
                }
            }
        }
        return  dp[compare.size()][to.size()];
    }

    private static int NumOfReplacement(String c1, String c2) {
        return c1.equals(c2) ? 0 : 1;
    }

    private static int min(int... numbers) {
        return Arrays.stream(numbers)
                .min().orElse(Integer.MAX_VALUE);
    }

}


