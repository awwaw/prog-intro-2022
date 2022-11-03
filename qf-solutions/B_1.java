import java.util.*;

public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int start = -710 * 25_000;
        for (int i = 0; i < n; i++) {
            System.out.println(start + i);
        }
    }
}
