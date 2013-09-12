package week2.fileFragmentation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * @author noia1
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int cases = in.nextInt();
        in.nextLine();
        in.nextLine();

        for (int i = 0; i < cases; i++) {
            HashMap<Integer, HashSet<String>> buckets = new HashMap<Integer, HashSet<String>>();
            int big = 0;
            int small = Integer.MAX_VALUE;
            while (in.hasNextLine()) {
                String piece = in.nextLine();
                if (piece.isEmpty()) break;

                int size = piece.length();
                if (size > big) big = size;
                if (size < small) small = size;

                HashSet<String> bucket = buckets.get(size);
                if (bucket == null) {
                    bucket = new HashSet<String>();
                    buckets.put(size, bucket);
                }
                bucket.add(piece);
            }

            findSequence(big, small, buckets);
            if (i+1 < cases) System.out.println();

        }

    }

    private static void findSequence(int big, int small, HashMap<Integer, HashSet<String>> buckets) {
        if (big == small) {
            HashSet<String> bucket = buckets.get(big);
            Object[] pieces = bucket.toArray();
            if (pieces.length == 1) System.out.println(((String) pieces[0]) + ((String) pieces[0]));
            else System.out.println(((String) pieces[0]) + ((String) pieces[1]));
            return;
        }
        for (Iterator<String> it = buckets.get(small).iterator(); it.hasNext(); ) {
            String smallPart = it.next();
            for (Iterator<String> itB = buckets.get(big).iterator(); itB.hasNext(); ) {
                String bigPart = itB.next();
                if (findSequence(big - 1, small + 1, buckets, smallPart + bigPart)) {
                    System.out.println(smallPart + bigPart);
                    return;
                }
                if (findSequence(big - 1, small + 1, buckets, bigPart + smallPart)) {
                    System.out.println(bigPart + smallPart);
                    return;
                }
            }
        }
    }

    private static boolean findSequence(int big, int small, HashMap<Integer, HashSet<String>> buckets,
                                        String seq) {
        if (big == small) {
            HashSet<String> bucket = buckets.get(big);
            Object[] pieces = bucket.toArray();
            if (pieces.length == 1) {
                return ((String) pieces[0] + ((String) pieces[0])).equals(seq);
            } else {
                if (((String) pieces[0] + ((String) pieces[1])).equals(seq)) return true;
                return (((String) pieces[1] + ((String) pieces[0])).equals(seq));
            }
        }
        for (Iterator<String> it = buckets.get(small).iterator(); it.hasNext(); ) {
            String smallPart = it.next();
            for (Iterator<String> itB = buckets.get(big).iterator(); itB.hasNext(); ) {
                String bigPart = itB.next();
                if ((smallPart + bigPart).equals(seq)) {
                    return ((big - 1) < (small + 1)) || findSequence(big - 1, small + 1, buckets, smallPart + bigPart);
                }
                if ((bigPart + smallPart).equals(seq)) {
                    return ((big - 1) < (small + 1)) || findSequence(big - 1, small + 1, buckets, bigPart + smallPart);
                }
            }
        }
        return false;
    }

}
