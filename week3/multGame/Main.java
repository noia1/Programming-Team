package week3.multGame;

import java.util.Scanner;

/**
 * @author noia1
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextLong()) {
            if (calculate(in.nextLong())) {
                System.out.println("Stan wins.");
            } else {
                System.out.println("Ollie wins.");
            }
        }
    }

    static boolean calculate(long n) {
        int i = 1;
        int counter = 0;
        while (i < n) {
            if (counter % 2 == 0) i *= 9;
            else i *= 2;
            counter++;
        }
        return counter % 2 == 1;
    }
}
