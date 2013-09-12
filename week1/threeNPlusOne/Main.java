package week1.threeNPlusOne;

import java.util.Scanner;

/**
 * @author noia1
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        StringBuilder builder = new StringBuilder();

        while (in.hasNext()) {
            int i = in.nextInt();
            int j = in.nextInt();

            builder.append(i);
            builder.append(" ");
            builder.append(j);
            builder.append(" ");

            int longestLength = longestCycleLength(i, j);

            builder.append(longestLength);

            System.out.println(builder.toString());
            builder = new StringBuilder();
        }

        in.close();
    }

    private static int longestCycleLength(int i, int j) {
        int longest = 0;

        int less = i;
        int greater = j;

        if (i > j) {
            less = j;
            greater = i;
        }

        for (int curr = less; curr <= greater; curr++) {
            int cycleLen = 1;
            long val = curr;
            while (val != 1) {
                if (val % 2 == 1) {
                    val = val * 3;
                    ++val;
                } else {
                    val = val / 2;
                }
                ++cycleLen;
            }
            if (cycleLen > longest) {
                longest = cycleLen;
            }
        }

        return longest;
    }
}
