package week2.doublets;

import java.util.*;

/**
 * @author noia1
 */
public class Main {

    static HashMap<Integer, ArrayList<String>> sizeLists = new HashMap<Integer, ArrayList<String>>();
    static HashMap<Integer, ArrayList<LinkedList<Integer>>> adjacent = new HashMap<Integer, ArrayList<LinkedList<Integer>>>();
    static int[] prev;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()) {
            String line = in.nextLine();
            if (line.isEmpty()) break;
            int size = line.length();
            if (sizeLists.containsKey(size)) {
                sizeLists.get(size).add(line);
            } else {
                ArrayList<String> list = new ArrayList<String>();
                list.add(line);
                sizeLists.put(size, list);
            }
        }

        while (in.hasNext()) {
            String a = in.next();
            String b = in.next();
            if (a.equals(b)) {
                System.out.println(a);
                if (in.hasNext()) System.out.println();
                continue;
            }
            int lenA = a.length();
            if (lenA != b.length()) {
                System.out.println("No solution.");
                if (in.hasNext()) System.out.println();
                continue;
            }
            ArrayList<String> dictionary = sizeLists.get(lenA);

            if (!dictionary.contains(a) || !dictionary.contains(b)) {
                System.out.println("No solution.");
                if (in.hasNext()) System.out.println();
                continue;
            }

            ArrayList<LinkedList<Integer>> adjList = adjacent(dictionary, lenA);

            dijkstras(dictionary, adjList, a);
            path(dictionary, b);
            if (in.hasNext()) System.out.println();
        }
    }

    private static void path(ArrayList<String> dictionary, String b) {
        int bInd = dictionary.indexOf(b);

        if (prev[bInd] == -1) {
            System.out.println("No solution.");
            return;
        }

        StringBuilder s = new StringBuilder();
        while (bInd != -1) {
            s.insert(0, (dictionary.get(bInd) + '\n'));
            bInd = prev[bInd];
        }
        System.out.print(s.toString());
    }

    private static ArrayList<LinkedList<Integer>> adjacent(ArrayList<String> dictionary, int len) {
        if (adjacent.containsKey(len)) return adjacent.get(len);
        ArrayList<LinkedList<Integer>> adjList = new ArrayList<LinkedList<Integer>>(dictionary.size());
        for (int i = 0; i < dictionary.size(); i++) {
            String a = dictionary.get(i);
            LinkedList<Integer> list = new LinkedList<Integer>();
            for (int j = 0; j < dictionary.size(); j++) {
                if (i == j) continue;
                String b = dictionary.get(j);
                if (difference(a, b) == 1) {
                    list.add(j);
                }
            }
            adjList.add(list);
        }
        adjacent.put(len, adjList);
        return adjList;
    }

    private static void dijkstras(ArrayList<String> dictionary, ArrayList<LinkedList<Integer>> adj, String a) {
        int[] dist = new int[dictionary.size()];
        prev = new int[dictionary.size()];

        for (int i = 0; i < dist.length; i++) {
            dist[i] = Integer.MAX_VALUE;
            prev[i] = -1;
        }

        dist[dictionary.indexOf(a)] = 0;
        PriorityQueue<Pair> q = new PriorityQueue<Pair>();

        for (int i = 0; i < dist.length; i++) {
            q.add(new Pair(i, dist[i]));
        }

        while (!q.isEmpty()) {
            Pair p = q.poll();
            if (p.dist == Integer.MAX_VALUE) break;

            int distance = p.dist + 1;
            for (int i : adj.get(p.id)) {
                if (distance < dist[i]) {
                    q.remove(new Pair(i, 0));
                    q.add(new Pair(i, distance));
                    dist[i] = distance;
                    prev[i] = p.id;
                }
            }
        }

    }

    private static int difference(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) count++;
        }
        return count;
    }

    public static class Pair implements Comparable<Pair> {
        public int id, dist;

        public Pair(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }

        @Override
        public boolean equals(Object o) {
            return id == ((Pair) o).id;
        }

        @Override
        public int compareTo(Pair o) {
            return this.dist - o.dist;
        }
    }

}
