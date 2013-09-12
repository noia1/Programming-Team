package week2.commonPermutation;

import java.util.Scanner;

/**
 * @author noia1
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            String a = in.nextLine();
            String b = in.nextLine();
            System.out.println(permutations(a, b));
        }

        in.close();
    }

    private static String permutations(String a, String b) {
        int[] aList = new int[26];
        int[] bList = new int[26];
        for (int i = 0; i < a.length(); i++) {
            aList[(a.charAt(i) - 'a')]++;
        }
        for (int i = 0; i < b.length(); i++) {
            bList[(b.charAt(i) - 'a')]++;
        }
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            int min = Math.min(aList[i], bList[i]);
            for (int j = 0; j < min; j++) {
                char c = (char) (i + 'a');
                s.append(c);
            }
        }
        return s.toString();
    }
}
