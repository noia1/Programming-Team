import java.util.Scanner;

/**
 * @author noia1
 */
public class Main {

    public static final String JOLLY = "Jolly";
    public static final String NOT_JOLLY = "Not jolly";

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            int lineLen = in.nextInt();

            if (lineLen == 1) {
                System.out.println(JOLLY);
                in.nextInt();
                continue;
            }

            int[] nums = new int[lineLen];
            for (int i = 0; i < lineLen; i++) {
                nums[i] = in.nextInt();
            }

            System.out.println(isJolly(nums));
        }

        in.close();
    }

    private static String isJolly(int[] jolly) {
        boolean[] bools = new boolean[jolly.length-1];
        for (int i = 1; i < jolly.length; i++) {
            int dif = Math.abs(jolly[i-1] - jolly[i]);
            if (dif < 1 || dif > bools.length) {
                return NOT_JOLLY;
            }
            if (bools[dif-1]) {
                return NOT_JOLLY;
            }
            bools[dif-1] = true;
        }
        return JOLLY;
    }
}
