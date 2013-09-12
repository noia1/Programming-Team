package week3.reverseAndAdd;

import java.util.Scanner;

/**
 * @author noia1
 */
public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        long lines = Integer.parseInt(in.nextLine());
        for (long i = 0; i < lines; i++) {
            pal(Integer.parseInt(in.nextLine()));
        }
    }

    static void pal(long val) {
        long i = 0;
        while (true) {
            long rev = reverse(val);
            if (rev == val) break;
            val += rev;
            i++;
        }
        System.out.printf("%d %d\n", i, val);
    }

    static long reverse(long val) {
        long temp = val;
        long rev = 0;
        while (temp > 0) {
            rev *= 10;
            rev += temp % 10;
            temp /= 10;
        }
        return rev;
    }
}
