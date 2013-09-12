package week2.shoemaker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author noia1
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int cases = Integer.parseInt(in.nextLine());

        for (int i = 0; i < cases; i++) {
            in.nextLine();

            int orders = in.nextInt();
            ArrayList<Pair> pairs = new ArrayList<Pair>(orders);
            for (int j = 0; j < orders; j++) {
                int time = in.nextInt();
                int cost = in.nextInt();
                pairs.add(new Pair(time, cost, j + 1));
            }
            Collections.sort(pairs);
            int j = 0;
            for (; j < orders - 1; j++) {
                System.out.printf("%d ", pairs.get(j).id);
            }
            System.out.println(pairs.get(j).id);
            if (cases - 1 != i) System.out.println();
        }
    }

    private static class Pair implements Comparable<Pair> {

        int time, cost, id;

        Pair(int time, int cost, int id) {
            this.time = time;
            this.cost = cost;
            this.id = id;
        }

        @Override
        public int compareTo(Pair o) {
            double me = ((double) this.time) / ((double) this.cost);
            double other = ((double) o.time) / ((double) o.cost);
            if ((me - other) > 0.0) return 1;
            else if ((me - other) < 0.0) return -1;
            else return 0;
        }
    }
}
