package week1.pokerHands;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author noia1
 */
public class Main {

    public static final String BLACK_WINS = "Black wins.";
    public static final String WHITE_WINS = "White wins.";
    public static final String TIE = "Tie.";

    public static final long PAIR = 2000000000000000000l;
    public static final long TWO_PAIR = 3000000000000000000l;
    public final static long THREE_OF_KIND = 4000000000000000000l;
    public final static long STRAIGHT = 5000000000000000000l;
    public final static long FLUSH = 6000000000000000000l;
    public final static long FULL_HOUSE = 7000000000000000000l;
    public final static long FOUR_OF_KIND = 8000000000000000000l;
    public final static long STRAIGHT_FLUSH = 9000000000000000000l;


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            int[] blackHand = new int[5];
            int[] whiteHand = new int[5];

            for (int i = 0; i < 5; i++) {
                blackHand[i] = parseCard(in.next());
            }

            for (int i = 0; i < 5; i++) {
                whiteHand[i] = parseCard(in.next());
            }

            Arrays.sort(blackHand);
            Arrays.sort(whiteHand);

            long blackScore = calculateScore(blackHand);
            long whiteScore = calculateScore(whiteHand);

            if (blackScore > whiteScore) System.out.println(BLACK_WINS);
            else if (whiteScore > blackScore) System.out.println(WHITE_WINS);
            else System.out.println(TIE);
        }
    }

    private static long calculateScore(int[] hand) {
        boolean straight = true;
        boolean flush = true;
        int[] count = new int[13];

        for (int i = 0; i < 4; i++) {
            if (straight) {
                if ((hand[i + 1] / 10 - hand[i] / 10) != 1) straight = false;
            }
            if (flush) {
                if (hand[i + 1] % 10 != hand[i] % 10) flush = false;
            }
            count[(hand[i] / 10) - 2]++;
        }
        count[(hand[4] / 10) - 2]++;

        if (straight && flush) return STRAIGHT_FLUSH + highCard(hand);

        int three = -1, two = -1, otherTwo = -1;

        for (int i = 12; i >= 0; i--) {
            if (count[i] == 4) return FOUR_OF_KIND + i;
            if (count[i] == 3) three = i;
            if (count[i] == 2 && two == -1) two = i;
            else if (count[i] == 2) otherTwo = i;
        }

        if (three != -1 && two != -1) return FULL_HOUSE + three;
        if (flush) return FLUSH + highCard(hand);
        if (straight) return STRAIGHT + highCard(hand);
        if (three != -1) return THREE_OF_KIND + three;
        if (two != -1 && otherTwo != -1) return TWO_PAIR + highCard(hand);
        if (two != -1) return PAIR + highCard(hand);
        return highCard(hand);
    }

    private static int highCard(int[] hand) {
        int mul = 1;
        int score = 0;
        for (int i = 0; i < 5; i++) {
            score += (hand[i]/10) * mul;
            mul *= 100;
        }
        return score;
    }

    private static int parseCard(String card) {
        card = card.replace("J", "11");
        card = card.replace("Q", "12");
        card = card.replace("K", "13");
        card = card.replace("A", "14");
        card = card.replace("S", "1");
        card = card.replace("C", "2");
        card = card.replace("H", "3");
        card = card.replace("D", "4");
        return Integer.parseInt(card);
    }
}
