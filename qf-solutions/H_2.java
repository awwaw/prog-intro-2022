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

        int[] prefSum = new int[n];
        prefSum[0] = transactions[0];
        for (int i = 1; i < n; i++)
            prefSum[i] = prefSum[i - 1] + transactions[i];

        for (int qu : queries) {
            if (qu < mx) {
                System.out.println("Impossible");
                continue;
            }

            int cur = 0;
            int a = 0;
            int curSum = 0;
            while (cur < n) {
                int left = cur - 1;
                int right = n;
                while (right - left > 1) {
                    int mid = (right + left) / 2;
                    if (prefSum[mid] - curSum > qu)
                        right = mid;
                    else
                        left = mid;
                }
                curSum = prefSum[left];
                cur = left + 1;
                a++;
            }
            System.out.println(a);
        }
    }
}
