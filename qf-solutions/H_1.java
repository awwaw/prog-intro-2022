import java.util.*;

public class Highload {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] transactions = new int[n];
        for (int i = 0; i < n; i++)
            transactions[i] = in.nextInt();
        int q = in.nextInt();
        int[] queries = new int[q];
        for (int i = 0; i < q; i++)
            queries[i] = in.nextInt();

        int mx = 0;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            mx = Math.max(mx, transactions[i]);
            sum += transactions[i];
        }
        int[] num = new int[sum];
        int cur = 0;
        int rem = transactions[0];
        for (int i = 0; i < sum; i++) {
            if (i >= rem) {
                rem += transactions[++cur];
            }
            num[i] = cur;
        }

        for (int qu : queries) {
            // System.out.println(qu + " " + mx);
            if (mx > qu) {
                System.out.println("Impossible");
                continue;
            }

            int blocks = 0;
            int curSum = 0;
            for (int i = 0; i < n; i++) {
                curSum += transactions[i];
                if (curSum > qu) {
                    blocks++;
                    curSum = transactions[i];
                }
            }
            System.out.println(blocks + 1);
        }
    }
}
