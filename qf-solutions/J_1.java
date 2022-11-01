import java.util.*;

public class J_1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            String row = in.next();
            for (int j = 0; j < n; j++) {
                matrix[i][j] = row.charAt(j) - '0'; // amount of paths from i to j mod 10
            }
        }
        int[][] ans = new int[n][n];
        for (int j = 1; j < n; j++) {
            for (int i = 0; i + j < n; i++) {
                int res = 0;
                for (int k = 1; k < j; k++) {
                    res = (res + (matrix[i][k - 1] * matrix[i + k][j - k - 1]) % 10) % 10;
                }
                ans[i][j - 1] = (res % 10 == matrix[i][j - 1] ? 1 : 0);
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                System.out.print(0);
            }
            for (int d = 0; d + i < n - 1; d++)
                System.out.print(ans[i][d]);
            System.out.println();
        }
    }
}
